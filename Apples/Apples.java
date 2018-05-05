import java.util.*;

public class Apples {
  public static void main(String[] args) {

    Apple green0 = new Apple("green", "green0", 13);
    Apple green1 = new Apple("green", "green1", 666);
    Apple red0 = new Apple("red", "red0", 13);
    Apple red1 = new Apple("red", "red1", 666);
    Apple yellow0 = new Apple("yellow", "yellow0", 13);

    List<Apple> appleList = new ArrayList<>();

    appleList.add(green0);
    appleList.add(green1);
    appleList.add(red0);
    appleList.add(red1);
    appleList.add(yellow0);

    for(Apple apple : filterGreenApples(appleList)) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    for(Apple apple : filterApplesByColour(appleList, "yellow")) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    for(Apple apple : filterApplesByWeight(appleList, 69)) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    for(Apple apple : filterApples(appleList, "red", 69, true)) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    for(Apple apple : filterApplesBehaviourParameterization(appleList, new AppleGreenColourPredicate())) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    for(Apple apple : filterApplesBehaviourParameterization(appleList, new AppleRedAndHeavyPredicate())) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    prettyPrintApple(appleList, new AppleFancyFormatter());

    System.out.println("--------------");

    prettyPrintApple(appleList, new AppleSimpleFormatter());

    System.out.println("--------------");

    // Parameterizing the behaviour of the method 'filterApples' directly inline!
    List<Apple> redApples = filterApplesBehaviourParameterization(appleList, new ApplePredicate() {
      public boolean test(Apple apple) {
        return "red".equals(apple.getColour());
      }
    });

    for(Apple apple : redApples) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    List<Apple> result0 = filterApplesBehaviourParameterization(appleList, a -> "yellow".equals(a.getColour()));

    for(Apple apple : result0) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    List<Apple> result1 = filterApplesBehaviourParameterization(appleList, (Apple apple) -> "green".equals(apple.getColour()));

    for(Apple apple : result1) {
      System.out.println(apple.getName());
    }

    System.out.println("--------------");

    List<Apple> result2 = filter(appleList, (Apple apple) -> "red".equals(apple.getColour()));

    for(Apple apple : result2) {
      System.out.println(apple.getName());
    }

    System.out.println("**************");

    List<Integer> numbers = new ArrayList<>();

    numbers.add(0);
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    numbers.add(4);
    numbers.add(5);
    numbers.add(6);
    numbers.add(7);
    numbers.add(8);
    numbers.add(9);

    List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);

    for(Integer number : evenNumbers) {
      System.out.println(number.toString());
    }

    System.out.println("**************");

    for(Apple apple : appleList) {
      System.out.println(apple.getName());
    }

    System.out.println("*");
    appleList.sort(new Comparator<Apple>() {
      public int compare(Apple a1, Apple a2) {
        return a1.getWeight().compareTo(a2.getWeight());
      }
    });
    System.out.println("*");

    for(Apple apple : appleList) {
      System.out.println(apple.getName());
    }

    System.out.println("**************");

    appleList.sort(
      (Apple a1, Apple a2) -> a1.getName().compareTo(a2.getName())
    );

    for(Apple apple : appleList) {
      System.out.println(apple.getName());
    }

    System.out.println("**************");

    Thread t0 = new Thread(new Runnable() {
      public void run() {
        System.out.println("Hello world!");
      }
    });

    t0.start();

    System.out.println("**************");

    Thread t1 = new Thread(() -> System.out.println("Hallo world!"));

    t1.start();

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

  public static List<Apple> filterApplesByColour(List<Apple> inventory, String colour) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getColour().equals(colour)) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (apple.getWeight() > weight) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApples(List<Apple> inventory, String colour, int weight, boolean flag) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if ( (flag && apple.getColour().equals(colour)) || (!flag && apple.getWeight() > weight) ) {
        result.add(apple);
      }
    }
    return result;
  }

  public static List<Apple> filterApplesBehaviourParameterization(List<Apple> inventory, ApplePredicate p) {
    List<Apple> result = new ArrayList<>();
    for (Apple apple : inventory) {
      if (p.test(apple)) {
        result.add(apple);
      }
    }
    return result;
  }

  public static void prettyPrintApple(List<Apple> inventory, AppleFormatter f) {
    for (Apple apple : inventory) {
      String output = f.accept(apple);
      System.out.println(output);
    }
  }

  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> result = new ArrayList<>();
    for (T e : list) {
      if (p.test(e)) {
        result.add(e);
      }
    }
    return result;
  }

  interface Predicate<T> {
    boolean test(T t);
  }
}















