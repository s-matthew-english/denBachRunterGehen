package s.matthew.english;

/**
 * Hello world!
 **/
public class NettyServer {

  private int port;

  public NettyServer(int port) {
    this.port = port;
  }

    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );

    int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

    new NettyServer(port).run();
    }

  public void run() throws Exception {
    System.out.println("running...");
  }
}
