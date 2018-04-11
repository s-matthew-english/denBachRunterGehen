/**
 * Represents an Ethereum node that we interacting with through the discovery and wire protocols.
 */
public class DefaultDiscoveryPeer extends DefaultPeer implements DiscoveryPeer {
  private PeerDiscoveryStatus status = PeerDiscoveryStatus.KNOWN;
  private int sha3;

  // Timestamps.
  private long firstDiscovered = 0;
  private long lastContacted = 0;
  private long lastSeen = 0;

  public DefaultDiscoveryPeer(String id, String host, int udpPort, int tcpPort) {
    super(id, host, udpPort, tcpPort);
  }

  public DefaultDiscoveryPeer(String id, String host, int udpPort) {
    super(id, host, udpPort);
  }

  public DefaultDiscoveryPeer(String id, Endpoint endpoint) {
    super(id, endpoint);
  }

  public DefaultDiscoveryPeer(Peer peer) {
    super(peer.id(), peer.endpoint());
  }

  @Override
  public int sha3() {
    return sha3;
  }

  @Override
  public PeerDiscoveryStatus status() {
    return status;
  }

  public void status(PeerDiscoveryStatus status) {
    this.status = status;
  }

  @Override
  public long firstDiscovered() {
    return firstDiscovered;
  }

  public Peer firstDiscovered(long firstDiscovered) {
    this.firstDiscovered = firstDiscovered;
    return this;
  }

  @Override
  public long lastContacted() {
    return lastContacted;
  }

  public void lastContacted(long lastContacted) {
    this.lastContacted = lastContacted;
  }

  @Override
  public long lastSeen() {
    return lastSeen;
  }

  public void lastSeen(long lastSeen) {
    this.lastSeen = lastSeen;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DefaultDiscoveryPeer{");
    sb.append("id=").append(id());
    sb.append(", endpoint=").append(endpoint());
    sb.append(", status=").append(status);
    sb.append('}');
    return sb.toString();
  }
}
