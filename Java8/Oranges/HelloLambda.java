import java.io.*;
import java.util.*;

public class HelloLambda {
  public static void main(String... args) throws IOException {
    Runnable r1 = () -> System.out.println("Hello World 1");

    Runnable r2 = new Runnable() {
      public void run() {
        System.out.println("Hello World 2");
      }
    };

    process(r1);
    process(r2);
    process(() -> System.out.println("Hello World 3"));

    process(() -> System.out.println("This is awesome!!"));

    System.out.println(processFile());

    String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
    System.out.println(result);

    String oneLine = processFile((BufferedReader br) -> br.readLine());
    System.out.println(oneLine);

    String threeLines = processFile((BufferedReader br) -> br.readLine() + br.readLine() + br.readLine());
    System.out.println(threeLines);

    String green0 = "Green";
    String green1 = "";
    String red0 = "Red";
    List<String> listOfStrings = new ArrayList<>();
    listOfStrings.add(green0);
    listOfStrings.add(green1);
    listOfStrings.add(red0);

    System.out.println("--------------");
    for(String string : listOfStrings) {
      System.out.println(string);
    }
    System.out.println("--------------");

    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
    List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);

    for(String string : nonEmpty) {
      System.out.println(string);
    }
    System.out.println("--------------");

    forEach(
            Arrays.asList(1, 2, 3, 4, 5),
            (Integer i) -> System.out.println(i)
           );

  }

  public static void process(Runnable r) {
    r.run();
  }

  public static String processFile() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
      return br.readLine();
    }
  }

  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
      return p.process(br);
    }
  }

  public static <T> List<T> filter(List<T> list, Predicate<T> p) {
    List<T> results = new ArrayList<>();
    for (T s : list) {
      if (p.test(s)) {
        results.add(s);
      }
    }
    return results;
  }

  public static <T> void forEach(List<T> list, Consumer<T> c) {
    for (T i : list) {
      c.accept(i);
    }
  }

  @FunctionalInterface
  public interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
  }

  @FunctionalInterface
  public interface Predicate<T> {
    boolean test(T t);
  }

  @FunctionalInterface
  public interface Consumer<T> {
    void accept(T t);
  }
}
