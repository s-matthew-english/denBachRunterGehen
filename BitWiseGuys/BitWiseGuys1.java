public class BitWiseGuys1 {
  public static void main(String[] args) {

    int x = 10;                   // bits are 00001010
    System.out.println("x: " + x);
    int y = 6;                    // bits are 00000110
    System.out.println("y: " + y);
    int a = x | y;                // bits are 00001110
    System.out.println("x | y: " + a);
  }
}
