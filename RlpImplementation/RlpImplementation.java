import java.util.*;
import java.lang.*;
import javax.xml.bind.DatatypeConverter;

public class RlpImplementation {
  public static void main(String[] args) {

    //String example = "Lorem ipsum dolor sit amet, consectetur adipisicing elit";
    // byte[] bytes = example.getBytes();

    // String hex = DatatypeConverter.printHexBinary(bytes);
    // System.out.println(hex); // prints "7F0F00"

    System.out.println(encodeRlp("S"));

  }




  public static String toBinary(int inputLength) {
    if (inputLength == 0) {
      return String.valueOf(0);
    } else {
      int component0 = Integer.parseInt(toBinary((int)(inputLength / 256)));
      char component1 = (char)(inputLength % 256);
      int  component = component0 + ((int)component1);
      char returnValue = (char)component; // cast from int to char
      return String.valueOf(returnValue);
    }
  }

  public static String encodeLength(int inputLength, int offset) {
    if (inputLength < 56) {
      return String.valueOf(inputLength + offset);
    }
    if (inputLength < java.lang.Math.pow(256,8)) {
      String binaryValue = toBinary(inputLength);
      int binaryLength = binaryValue.length();
      int composite = binaryLength + offset + 55;
      char returnComponent = (char)composite;
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

  public static String encodeRlpList(ArrayList<String> input) {

    String output = encodeLength(input.length(), 192);

    for (String element : input) {
      output += encodeRlp(element);
    }
    return output;
  }

}




