public class BitWiseGuys {
  public static void main(String[] args) {

    int x = 10;                   // bits are 00001010
    System.out.println("x: " + x);
    x = ~x;                       // bits are 11110101
    System.out.println("x: " + x);
  }
}
