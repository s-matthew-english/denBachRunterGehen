// Simple program to demonstrate the use of streams in Java.
import java.util.*;
import java.util.stream.*;

/**
 * A stream consists of source followed by zero or more intermediate methods combined together (pipelined) and a 
 * terminal method to process the objects obtained from the source as per the methods described below.
 * 
 * Stream is used to compute elements as per the pipelined methods without altering the original value of the object.
 */
class Demo {

  public static void main(String[] args) {

    // Create a list of integers.
    List<Integer> number = Arrays.asList(2, 3, 4, 5);
    System.out.println("0: " + number);

    // Demonstration of map method.
    List<Integer> square = number.stream().map(x -> x * x).collect(Collectors.toList());
    System.out.println("0: " + square + "\n");

    // Create a list of String.
    List<String> names = Arrays.asList("Reflection", "Collection", "Stream");
    System.out.println("1: " + names);

    // Demonstration of filter method.
    List<String> result = names.stream().filter(s -> s.startsWith("S")).collect(Collectors.toList());
    System.out.println("1: " + result);

    // Demonstration of sorted method. 
    List<String> show = names.stream().sorted().collect(Collectors.toList());
    System.out.println("1: " + show + "\n");

    // Create a list of integers.
    List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 2);
    System.out.println("2: " + numbers);

    // Collect method returns a set.
    Set<Integer> squareSet = numbers.stream().map(x -> x * x).collect(Collectors.toSet());
    System.out.println("2: " + squareSet + "\n");

    // Demonstration of forEach method.
    number.stream().map(x -> x * x).forEach(y -> System.out.println("For Each: " + y));

    // Demonstration of reduce method.
    int even = number.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
    System.out.println("Reduce: " + even);
  }
}
