import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentSkipListMapExample {

  public static void main(String[] args) {

    ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<String, String>();
    concurrentSkipListMap.put("1111", "Tom Smith");
    concurrentSkipListMap.put("2222", "David Jones");
    concurrentSkipListMap.put("3333", "Jim Anderson");
    concurrentSkipListMap.put("4444", "John Abraham");
    concurrentSkipListMap.put("5555", "Brad Pitt");

    System.out.println("The name associated with id 1111 is " + concurrentSkipListMap.get("1111"));

    NavigableSet navigableKeySet = concurrentSkipListMap.keySet();

    System.out.println("The keys associated with this map are ");
    Iterator iterator = navigableKeySet.iterator();
    while(iterator.hasNext()) {
      System.out.println(iterator.next());
    }

    ConcurrentNavigableMap<String, String> subMap = concurrentSkipListMap.subMap("1111", "3333");
    NavigableSet navigableSubKeySet = subMap.keySet();

    System.out.println("The keys associated with the submap keys from 1111 to 3333 are");
    Iterator subMapIterator = navigableSubKeySet.iterator();
    while(subMapIterator.hasNext()) {
      System.out.println(subMapIterator.next());
    }

    ConcurrentNavigableMap<String, String> headerMap = concurrentSkipListMap.headMap("2222");
    System.out.println("The keys associated with the headerMap less than 2222");

    NavigableSet navigableHeadMapKeySet = headerMap.keySet();
    Iterator headerMapIterator = navigableHeadMapKeySet.iterator();
    while(headerMapIterator.hasNext()) {
      System.out.println(headerMapIterator.next());
    }

    ConcurrentNavigableMap<String, String> tailMap = concurrentSkipListMap.tailMap("1111");
    System.out.println("The keys associated with the tailMap less than 1111");
    NavigableSet navigableTailMapKeySet = tailMap.keySet();
    Iterator tailMapIterator = navigableHeadMapKeySet.iterator();
    while(tailMapIterator.hasNext()) {
      System.out.println(tailMapIterator.next());
    }
  }
}
