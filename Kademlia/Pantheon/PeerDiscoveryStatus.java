/**
 * The status of a {@link DefaultDiscoveryPeer}, in relation to the peer discovery state machine.
 */
public enum PeerDiscoveryStatus {

  /**
   * Represents a newly discovered {@link DefaultDiscoveryPeer}, prior to commencing the bonding
   * exchange.
   */
  KNOWN,

  /**
   * Bonding with this peer is in progress. If we're unable to establish communication and/or
   * complete the bonding exchange, the {@link DefaultDiscoveryPeer} remains in this state, until we
   * ultimately desist.
   */
  BONDING,

  /**
   * We have successfully bonded with this {@link DefaultDiscoveryPeer}, and we are able to exchange
   * messages with them.
   */
  BONDED;

  @Override
  public String toString() {
    return name().toLowerCase();
  }
}
