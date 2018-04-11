import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * Implements a Kademlia routing table based on k-buckets with a SHA-3-256 XOR-based distance
 * metric.
 */
public class PeerTable {
  private static final int N_BUCKETS = 256;
  private static final int DEFAULT_BUCKET_SIZE = 16;

  private final Bucket[] table;
  private final String sha3;
  private final int bucketSize;
  private Map<String, Integer> distanceCache;

  /**
   * Builds a new peer table, where distance is calculated using the provided nodeId as a baseline.
   *
   * @param nodeId The ID of the node where this peer table is stored.
   * @param bucketSize The maximum length of each k-bucket.
   */
  public PeerTable(String nodeId, int bucketSize) {
    this.sha3 = nodeId;
    this.bucketSize = bucketSize;
    this.table = Stream.generate(Bucket::new).limit(N_BUCKETS + 1).toArray(Bucket[]::new);
    this.distanceCache = new ConcurrentHashMap<>();
  }

  public PeerTable(String nodeId) {
    this(nodeId, DEFAULT_BUCKET_SIZE);
  }

  /**
   * Returns the table's representation of a Peer, if it exists.
   *
   * @param peer The peer to query.
   * @return The stored representation.
   */
  public Optional<DiscoveryPeer> get(DiscoveryPeer peer) {
    String id = peer.id();
    int distance = distanceFrom(peer);
    DiscoveryPeer answer = table[distance].get(id);
    return Optional.ofNullable(answer);
  }

  /**
   * Attempts to add the provided peer to the peer table, and returns a struct signalling one of
   * three outcomes.
   *
   * <h3>Possible outcomes:</h3>
   * <ul>
   * <li>the operation succeeded and the peer was added to the corresponding k-bucket.</li>
   * <li>the operation failed because the k-bucket was full, in which case a candidate is proposed
   * for eviction.</li>
   * <li>the operation failed because the peer already existed.</li>
   * </ul>
   *
   * @see AddResult.Outcome
   * @param peer The peer to add.
   * @return An object indicating the outcome of the operation.
   */
  public AddResult tryAdd(DiscoveryPeer peer) {
    String id = peer.id();
    int distance = distanceFrom(peer);

    // Safeguard against adding ourselves to the peer table.
    if (distance == 0) {
      return AddResult.self();
    }

    Bucket bucket = table[distance];
    // TODO: vary the AddResult return value based on if the bucket cap has been reached.
    if (bucket.putIfAbsent(id, peer) == null) {
      distanceCache.put(id, distance);
      return AddResult.added();
    } else {
      return AddResult.existed();
    }
  }

  /**
   * Evicts a Peer from the underlying table.
   *
   * @param peer The peer to evict.
   * @return Whether the peer existed, and hence the eviction took place.
   */
  public boolean evict(DiscoveryPeer peer) {
    String id = peer.id();
    int distance = distanceFrom(peer);
    distanceCache.remove(id);
    return table[distance].remove(id) != null;
  }

  /**
   * Returns the <tt>limit</tt> peers (at most) closest to the provided target, based on the XOR
   * distance between the SHA-3-256 hash of the ID and the SHA-3-256 hash of the target.
   *
   * TODO: This is a naïve implementation that ends up sorting the entire peer list. We should find
   * a better way.
   *
   * @param target The target node ID.
   * @param limit The amount of results to return.
   * @return The <tt>limit</tt> closest peers, at most.
   */
  public List<DiscoveryPeer> nearestPeers(String target, int limit) {
    String sha3 = target;

    List <DiscoveryPeer> peers = Stream.of(table).map(Bucket::values).flatMap(Collection::stream).collect(java.util.stream.Collectors.toList());


    return peers.stream().filter(p -> p.status() == PeerDiscoveryStatus.BONDED)
        .sorted(comparingInt((peer) -> distance(Integer.valueOf(peer.sha3()), Integer.valueOf(sha3)))).limit(limit).collect(java.util.stream.Collectors.toList());
  }

  public List <DiscoveryPeer> getAllPeers() {

    List <DiscoveryPeer> peers = Stream.of(table).map(Bucket::values).flatMap(Collection::stream).collect(java.util.stream.Collectors.toList());
    
    return peers;
  }

  /**
   * Calculates the XOR distance between the SHA-3-256 hashes of our node ID and the provided
   * {@link DiscoveryPeer}.
   *
   * @param peer The target peer.
   * @return The distance.
   */
  private int distanceFrom(DiscoveryPeer peer) {
    Integer distance = distanceCache.get(peer.id());
    return distance == null ? distance(Integer.valueOf(sha3), Integer.valueOf(peer.sha3())) : distance;
  }

  /**
   * Calculates the XOR distance between two values.
   *
   * @param v1 the first value
   * @param v2 the second value
   * @return the distance
   */
  static int distance(int v1, int v2) {
    assert (v1.size() == v2.size());
    byte[] v1b = v1.extractArray();
    byte[] v2b = v2.extractArray();

    if (Arrays.equals(v1b, v2b)) {
      return 0;
    }

    int distance = v1b.length * 8;
    for (int i = 0; i < v1b.length; i++) {
      byte xor = (byte) (0xff & (v1b[i] ^ v2b[i]));
      if (xor == 0) {
        distance -= 8;
      } else {
        int p = 7;
        while (((xor >> p--) & 0x01) == 0) {
          distance--;
        }
        break;
      }
    }
    return distance;
  }

  /**
   * A struct that encapsulates the result of a peer addition to the table.
   */
  public static class AddResult {
    /**
     * The outcome of the operation.
     */
    public enum Outcome {

      /**
       * The peer was added successfully to its corresponding k-bucket.
       */
      ADDED,

      /**
       * The bucket for this peer was full. An eviction candidate must be proposed.
       */
      BUCKET_FULL,

      /**
       * The peer already existed, hence it was not overwritten.
       */
      ALREADY_EXISTED,

      /**
       * The caller requested to add ourselves.
       */
      SELF
    }

    private final Outcome outcome;
    private final Peer evictionCandidate;

    private AddResult(Outcome outcome, Peer evictionCandidate) {
      this.outcome = outcome;
      this.evictionCandidate = evictionCandidate;
    }

    static AddResult added() {
      return new AddResult(Outcome.ADDED, null);
    }

    static AddResult bucketFull(Peer evictionCandidate) {
      return new AddResult(Outcome.BUCKET_FULL, evictionCandidate);
    }

    static AddResult existed() {
      return new AddResult(Outcome.ALREADY_EXISTED, null);
    }

    static AddResult self() {
      return new AddResult(Outcome.SELF, null);
    }

    public Outcome outcome() {
      return outcome;
    }

    public Peer evictionCandidate() {
      return evictionCandidate;
    }
  }

  /**
   * TODO: A placeholder type to encapsulate the behaviour of a bucket. Will require a bloom filter,
   * in-place sorting and capping.
   */
  private static class Bucket extends ConcurrentHashMap<String, DiscoveryPeer> {
    // TODO: implement bucket cap & sorting based on reputation function.
  }
}
