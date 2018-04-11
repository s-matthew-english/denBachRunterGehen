/**
 * The representation of a {@link Peer} in the peer-to-peer discovery layer. It enhances the core
 * <tt>Peer</tt> object with data pertaining to the devp2p discovery layer.
 */
public interface DiscoveryPeer extends Peer {

  /**
   * The status of this peer in the discovery layer.
   *
   * @see PeerDiscoveryStatus
   * @return The status.
   */
  PeerDiscoveryStatus status();

  /**
   * The SHA-3 hash value of the peer's ID. The value may be memoized to avoid recomputation
   * overhead.
   *
   * @return The SHA-3 hash of the peer's ID.
   */
  int sha3();

  /**
   * The timestamp when this peer was first discovered, in millis after epoch.
   *
   * @return The timestamp.
   */
  long firstDiscovered();

  /**
   * The timestamp of the last time we successfully sent a message to this discovery peer, in millis
   * after epoch.
   *
   * @return The timestamp.
   */
  long lastContacted();

  /**
   * The timestamp of the last time we received a message from this discovery peer, in millis after
   * epoch.
   *
   * @return The timestamp.
   */
  long lastSeen();

}
