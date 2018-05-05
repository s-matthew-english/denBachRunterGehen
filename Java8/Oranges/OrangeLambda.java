import java.util.*;

public class OrangeLambda {
  public static void main(String... args) {

    Orange green0 = new Orange("green", "green0", 13);
    Orange green1 = new Orange("green", "green1", 666);
    Orange red0 = new Orange("red", "red0", 13);
    Orange red1 = new Orange("red", "red1", 666);
    Orange yellow0 = new Orange("yellow", "yellow0", 13);

    List<Orange> orangeList = new ArrayList<>();
    orangeList.add(green0);
    orangeList.add(green1);
    orangeList.add(red0);
    orangeList.add(red1);
    orangeList.add(yellow0);

    printListContents(orangeList);

    Comparator<Orange> byWeight = new Comparator<Orange>() {
      public int compare(Orange o1, Orange o2) {
        return o1.getWeight().compareTo(o2.getWeight());
      }
    };

    Collections.sort(orangeList, byWeight);

    printListContents(orangeList);

    Comparator<Orange> byName = (Orange o1, Orange o2) -> o1.getName().compareTo(o2.getName());

    Collections.sort(orangeList, byName);

    printListContents(orangeList);
  }

  public static void printListContents(List<Orange> orangeList) {
    for(Orange orange : orangeList) {
      System.out.println(orange.getName());
    }
    System.out.println("--------------");
  }

}
