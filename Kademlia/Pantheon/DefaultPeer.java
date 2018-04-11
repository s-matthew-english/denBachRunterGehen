import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The default, basic representation of an Ethereum {@link Peer}.
 */
public class DefaultPeer implements Peer {
  private static final int DEFAULT_PORT = 30303;
  private static final Pattern DISCPORT_QUERY_STRING_REGEX =
      Pattern.compile("discport=([0-9]{1,5})");
  private static final int PEER_ID_SIZE = 64;

  private final String id;
  private final Endpoint endpoint;

  /**
   * Creates a {@link DefaultPeer} instance from a String representation of an enode URL.
   *
   * @param uri A String representation of the enode URI.
   * @return The Peer instance.
   * @see <a href="https://github.com/ethereum/wiki/wiki/enode-url-format">enode URL format</a>
   */
  public static DefaultPeer fromURI(String uri) {
    return fromURI(URI.create(uri));
  }

  /**
   * Creates a {@link DefaultPeer} instance from an URI object that follows the enode URL format.
   *
   * @param uri The enode URI.
   * @return The Peer instance.
   * @see <a href="https://github.com/ethereum/wiki/wiki/enode-url-format">enode URL format</a>
   */
  public static DefaultPeer fromURI(URI uri) {
    checkNotNull(uri);
    checkArgument("enode".equals(uri.getScheme()));
    checkArgument(uri.getUserInfo() != null, "node id cannot be null");

    // Process the peer's public key, in the host portion of the URI.
    //String id = BytesValue.fromHexString(uri.getUserInfo());

    // Process the ports; falling back to the default port in both TCP and UDP.
    int tcpPort = DEFAULT_PORT;
    int udpPort = DEFAULT_PORT;
    if (NetworkUtility.isValidPort(uri.getPort())) {
      tcpPort = udpPort = uri.getPort();
    }

    // If TCP and UDP ports differ, expect a query param 'discport' with the UDP port.
    // See https://github.com/ethereum/wiki/wiki/enode-url-format
    if (uri.getQuery() != null) {
      udpPort = extractUdpPortFromQuery(uri.getQuery()).orElse(tcpPort);
    }

    Endpoint endpoint = new Endpoint(uri.getHost(), udpPort, OptionalInt.of(tcpPort));
    return new DefaultPeer(id, endpoint);
  }

  /**
   * Creates a {@link DefaultPeer} instance from its attributes, with a TCP port.
   *
   * @param id The node ID (public key).
   * @param host Ip address.
   * @param udpPort The UDP port.
   * @param tcpPort The TCP port.
   */
  public DefaultPeer(String id, String host, int udpPort, int tcpPort) {
    this(id, new Endpoint(host, udpPort, OptionalInt.of(tcpPort)));
  }

  /**
   * Creates a {@link DefaultPeer} instance from its attributes, without a TCP port.
   *
   * @param id The node ID (public key).
   * @param host Ip address.
   * @param udpPort UDP port.
   */
  public DefaultPeer(String id, String host, int udpPort) {
    this(id, new Endpoint(host, udpPort, OptionalInt.empty()));
  }

  /**
   * Creates a {@link DefaultPeer} instance from its ID and its {@link Endpoint}.
   *
   * @param id The node ID (public key).
   * @param endpoint The endpoint for this peer.
   */
  public DefaultPeer(String id, Endpoint endpoint) {
    checkArgument(id != null && id.size() == PEER_ID_SIZE,
        "id must be non-null and exactly 64 bytes long");
    checkArgument(endpoint != null, "endpoint cannot be null");
    this.id = id;
    this.endpoint = endpoint;
  }

  private static Optional<Integer> extractUdpPortFromQuery(String query) {
    Matcher matcher = DISCPORT_QUERY_STRING_REGEX.matcher(query);
    Optional<Integer> answer = Optional.empty();
    if (matcher.matches()) {
      answer = Optional.ofNullable(Ints.tryParse(matcher.group(1)));
    }
    return answer.filter(NetworkUtility::isValidPort);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String id() {
    return id;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Endpoint endpoint() {
    return endpoint;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof DefaultPeer)) {
      return false;
    }
    DefaultPeer other = (DefaultPeer) obj;
    return id.equals(other.id) && endpoint.equals(other.endpoint);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, endpoint);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DefaultPeer{");
    sb.append("id=").append(id);
    sb.append(", endpoint=").append(endpoint);
    sb.append('}');
    return sb.toString();
  }
}
