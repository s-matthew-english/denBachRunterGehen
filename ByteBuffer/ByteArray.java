import java.util.Arrays;

/**
 * Byte is 8 bits (binary data).
 *
 * Byte array is an array of bytes.
 *
 **/
public class ByteArray {

  public static void main(String[] args) {
    String str = "SME";
    byte[] byteArr = str.getBytes();

    System.out.println(byteArr.toString());
    System.out.println(byteArrayToHex(byteArr));
  }

  public static String byteArrayToHex(byte[] a) {
    StringBuilder sb = new StringBuilder(a.length * 2);
    for(byte b: a)
      sb.append(String.format("%02x", b));
    return sb.toString();
  }
}
