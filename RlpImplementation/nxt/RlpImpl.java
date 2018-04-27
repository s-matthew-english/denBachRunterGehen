import java.util.*;

public class RlpImpl {
  public static void main(String[] args) {
    System.out.println("oh yeah...");

    ArrayList<Object> data0 = new ArrayList<>();
    data0.add("a");
    data0.add("b");
    data0.add("c");
    data0.add("d");

    ArrayList<Object> data1 = new ArrayList<>();
    data1.add("1");
    data1.add("2");
    data1.add("3");
    data1.add("4");

    data1.add(data0);

    recursiveParse(data1);
  }



  public static void recursiveParse(ArrayList<Object> input) {

    for(Object curInstance : input) {

      if (curInstance instanceof ArrayList<?>) {
        recursiveParse(cast(curInstance));
        break;
      }

      System.out.println(curInstance.toString());
    }
  }

  @SuppressWarnings("unchecked")
  public static <T extends List<?>> T cast(Object obj) {
      return (T) obj;
  }
}
