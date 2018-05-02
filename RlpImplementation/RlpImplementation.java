import java.util.*;
import java.lang.*;
import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.List;

public class RlpImplementation {
  public static void main(String[] args) {

    //String example = "Lorem ipsum dolor sit amet, consectetur adipisicing elit";
    // byte[] bytes = example.getBytes();
    // ArrayList<ArrayList<String>> input0 = new ArrayList<>();
    ArrayList<Object> input = new ArrayList<>();

    //01, 02, [[03, 04],[05]]

    // input0.add()

    input.add("cat");
    input.add("dog");

    System.out.println(unpackList(input));
    //System.out.println("dog");

    //RlpImplementation.RlpElement blah = new RlpImplementation().new RlpElement("quite no");
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





@SuppressWarnings("unchecked")
public static <T extends List<?>> T cast(Object obj) {
    return (T) obj;
}


  public static String unpackList(Object xinput) {
    String output = "";

    if (xinput instanceof ArrayList<?>) {
      ArrayList<Object> input = cast(xinput);
      for (Object element : input) {
        output += encodeLength(input.size(), 192);
        output += encodeRlp(element);
        unpackList(input);
      }
    }

    return encodeRlp(xinput);
  }


}




  //  class RlpElement {

  //     Boolean isList = false;
  //     String str = null;

  //     RlpElement(Object inputObject) {


  //       inputObject
  //     }

  //     unpackList(ArrayList<Object> inputList) {
  //       for (Object element : inputList) {
  //         if (inputObject instanceof ArrayList<?>) {
  //           String output = encodeLength(input.size(), 192);
  //           output += encodeRlp((String)element);
  //           unpackList(inputList);
  //         }
  //         encodeRlp((String)element);
  //       }
  //     }

  // }








