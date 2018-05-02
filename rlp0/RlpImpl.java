import java.util.*;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RlpImpl {
  public static void main(String... args) {
    System.out.println("Hi Zac!");

    // [ 00, 0102, [[03, 04],[05]] ]
    //
    // [ 0102, [[03, 04],[05]] ]

    ArrayList<Object> input = new ArrayList<>();
    ArrayList<Object> input1 = new ArrayList<>();
    ArrayList<Object> input2 = new ArrayList<>();
    ArrayList<Object> input3 = new ArrayList<>();


    byte[] input0 = new byte[] {0x01,0x02};
    input1.add(new byte[] {0x03});
    input1.add(new byte[] {0x04});
    input2.add(new byte[] {0x05});

    // input0.add("02");
    // input1.add("03");
    // input1.add("04");
    // input2.add("05");

    input3.add(input1);
    input3.add(input2);

    input.add(input0);
    input.add(input3);

    // System.out.println(input0.toString());


    System.out.println(pr(input));

    // System.out.println(Arrays.toString("1".getBytes()));

    // System.out.println(toBinary(65));
  }


  public static String pr(Object xinput) {

    String output = "";

    if (xinput instanceof ArrayList<?>) {
      output += "[";
      ArrayList<Object> input = cast(xinput);
      for (Object element : input) {
        output +=  pr(element) + ",";

      }
      output = output.endsWith(",") ? output.substring(0, output.length() - 1) : output;
      output += "]";
    }else{
      output = DatatypeConverter.printHexBinary((byte[])xinput);
    }
    return output;
  }


  public static String encode(Object xinput) {

    String output = "";

    if (xinput instanceof ArrayList<?>) {
      
      ArrayList<Object> input = cast(xinput);
      for (Object element : input) {
        output +=  encode(element);
      }
    }

    if(output != ""){
      return output;
    }else{
      return (String)xinput;
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends List<?>> T cast(Object obj) {
      return (T) obj;
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

}


