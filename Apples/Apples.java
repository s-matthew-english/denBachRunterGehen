import java.util.*;

public class Apples {
  public static void main(String[] args) {

    Apple green0 = new Apple("green", "0");
    Apple green1 = new Apple("green", "1");
    Apple red0 = new Apple("red", "0");
    Apple red1 = new Apple("red", "1");
    Apple yellow0 = new Apple("yellow", "0");

    List<Apple> appleList = new ArrayList<Apple>();

    appleList.add(green0);
    appleList.add(green1);
    appleList.add(red0);
    appleList.add(red1);
    appleList.add(yellow0);

    for(Apple apple : filterGreenApples(appleList)) {
      System.out.println(apple.getName());
    }

  }

  public static List<Apple> filterGreenApples(List<Apple> inventory) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if ("green".equals(apple.getColour())) {
        result.add(apple);
      }
    }
    return result;
  }

 /**
  * In Java there are two types of nested classes: 'Static nested class' and 'Inner class'. Without the 'static' 
  * keyword it's an inner class and you will need an instance of 'Apple' to access... 
  **/
  private static class Apple {
    String colour;
    String name;
    Apple(String colour, String name) {
      this.colour = colour;
      this.name = name;
    }
    String getColour() {
      return colour;
    }
    String getName() {
      return name;
    }
  }
}
