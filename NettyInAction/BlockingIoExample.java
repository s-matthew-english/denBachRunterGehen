import java.net.*;
import java.io.*;
import java.util.*;

public class BlockingIoExample {
  public static void main(String... args) throws IOException {
    System.out.println("Testing...");

    ServerSocket serverSocket = new ServerSocket(8080);
    Socket clientSocket = serverSocket.accept();
    BufferedReader in = new BufferedReader(
      new InputStreamReader(clientSocket.getInputStream()));
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    String request, response;
    while ((request = in.readLine()) != null) {
      if ("Done".equals(request)) {
        break;
      }
      response = processRequest(request);
      out.println(response);
    }
  }
  private static String processRequest(String request) {
    return request;
  }
}


