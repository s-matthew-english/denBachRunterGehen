import java.util.*;
import java.util.function.BiPredicate;

/**
 * We can use methods as if they were objects, or primitive values:
 *
 * If we only use a method of an object in another method, 
 * we still have to pass the full object as an argument. 
 * Wouldn't it be more practical to just pass the method as 
 * an argument? For example:
 */
class MethodReference {

  public static void main(String[] args) {
    List<Integer> list = Arrays.asList(12, 5, 45, 18, 33, 24, 40);

    // Using an anonymous class.
    findNumbers(list, new BiPredicate<Integer, Integer>() {
      public boolean test(Integer i1, Integer i2) {
        return Numbers.isMoreThanFifty(i1, i2);
      }
    });

    // Using a lambda expression.
    findNumbers(list, (i1, i2) -> Numbers.isMoreThanFifty(i1, i2));

    // Using a method reference.
    findNumbers(list, Numbers::isMoreThanFifty);
  }

  class Numbers {
    public static boolean isMoreThanFifty(int n1, int n2) {
      return (n1 + n2) > 50;
    }

    public static List<Integer> findNumbers(List<Integer> l, BiPredicate<Integer, Integer> p) {
      List<Integer> newList = new ArrayList<>();
      for(Integer i : l) {
        if(p.test(i, i + 10)) {
          newList.add(i);
        }
      }
      return newList;
    }
  }
}

