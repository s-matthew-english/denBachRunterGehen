import java.util.*;

/**
 * Encapsulates the network coordinates of a {@link Peer}.
 */
public class Endpoint {
  private final String host;
  private final int udpPort;
  private final OptionalInt tcpPort;

  public Endpoint(String host, int udpPort, OptionalInt tcpPort) {
    checkArgument(host != null && InetAddresses.isInetAddress(host),
        "host requires a valid IP address");
    checkArgument(NetworkUtility.isValidPort(udpPort),
        "UDP port requires a value between 1 and 65535");
    tcpPort.ifPresent(p -> checkArgument(NetworkUtility.isValidPort(p),
        "TCP port requires a value between 1 and 65535"));

    this.host = host;
    this.udpPort = udpPort;
    this.tcpPort = tcpPort;
  }

  public String host() {
    return host;
  }

  public int udpPort() {
    return udpPort;
  }

  public OptionalInt tcpPort() {
    return tcpPort;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Endpoint)) {
      return false;
    }
    Endpoint other = (Endpoint) obj;
    return host.equals(other.host) && this.udpPort == other.udpPort
        && (this.tcpPort.equals(other.tcpPort));
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, udpPort, tcpPort);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Endpoint{");
    sb.append("host='").append(host).append('\'');
    sb.append(", udpPort=").append(udpPort);
    tcpPort.ifPresent(p -> sb.append(", tcpPort=").append(p));
    sb.append('}');
    return sb.toString();
  }

}
