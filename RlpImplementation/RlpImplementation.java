import java.util.*;
import java.lang.*;
import javax.xml.bind.DatatypeConverter;

public class RlpImplementation {
  public static void main(String[] args) {

    //String example = "Lorem ipsum dolor sit amet, consectetur adipisicing elit";
    // byte[] bytes = example.getBytes();

    // String hex = DatatypeConverter.printHexBinary(bytes);
    // System.out.println(hex); // prints "7F0F00"

    // 131SME
    // ^maybe that's correct, but not Hex encoded...
    System.out.println(encodeRlp("dog"));
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
      //System.out.println(returnValue);
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
}

// public static String encodeRlpList(ArrayList<String> input) {
//   String output = encodeLength(input.length(), 192);
//   for (String element : input) {
//     output += encodeRlp(element);
//   }
//   return output;
// }




