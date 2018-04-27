import java.util.*;
import java.lang.*;
import javax.xml.bind.DatatypeConverter;


public class RlpImpl {
  public static void main(String[] args) {
    System.out.println("oh yeah...");


    //<[ 00 01, 02, [[03, 04],[05]] ]>
    ArrayList<Object> data0 = new ArrayList<>();
    data0.add("00");
    data0.add("01");
    data0.add("02");

    ArrayList<Object> data1 = new ArrayList<>();
    data1.add("03");
    data1.add("04");

    ArrayList<Object> data2 = new ArrayList<>();
    data2.add("05");


    data1.add(data2);
    data0.add(data1);

    String input = "";

    System.out.println(recursiveParse(data0, input));
  }



  public static String toBinary(int inputLength) {
    if (inputLength == 0) {
      return String.valueOf(0);
    } else {
      int component0 = Integer.parseInt(toBinary((int)(inputLength / 256)));
      int component1 = inputLength % 256;
      int  component = component0 + component1;

      String returnValue = Integer.toHexString(component);

      return returnValue;
    }
  }

  public static String encodeLength(int inputLength, int offset) {
    if (inputLength < 56) {
      String returnValue = Integer.toHexString(inputLength + offset);
      return returnValue;
    }
    if (inputLength < java.lang.Math.pow(256,8)) {
      String binaryValue = toBinary(inputLength);
      int binaryLength = binaryValue.length();
      int composite = binaryLength + offset + 55;

      String returnComponent = Integer.toHexString(composite);
      String returnFinal = returnComponent + binaryValue;
      return returnFinal;
    } else {
      System.out.println("input too long");
      return "";
    }
  }

  public static String encodeRlp(String input) {
    if (input.length() == 1 && (int)input.charAt(0) < 128) {
      return input;
    }
    return encodeLength(input.length(), 128) + input;
  }

  public static String recursiveParse(ArrayList<Object> input, String output) {
    for(Object curInstance : input) {

      if (curInstance instanceof ArrayList<?>) {
        output += encodeLength(input.size(), 192);
        output += recursiveParse(cast(curInstance), output);
        break;
      }

      output += encodeRlp(curInstance.toString());
    }
    return output;
  }

  @SuppressWarnings("unchecked")
  public static <T extends List<?>> T cast(Object obj) {
      return (T) obj;
  }
}
