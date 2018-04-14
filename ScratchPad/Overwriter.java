public class Overwriter {

  private static Object[] kBucket  = new Object[8];

  public static void main(String[] args) {

    Object obj0 = 'M';
    Object obj1 = 'A';
    Object obj2 = 'T';
    Object obj3 = 'T';
    Object obj4 = 'H';
    Object obj5 = 'E';
    Object obj6 = 'W';

    kBucket[0] = obj0;
    kBucket[1] = obj1;
    kBucket[2] = obj2;
    kBucket[3] = obj3;
    kBucket[4] = obj4;
    kBucket[5] = obj5;
    kBucket[6] = obj6;

    for (int i = 0; i < kBucket.length; i++)
      System.out.println("Element at index " + i + " : "+ kBucket[i]);

    System.out.println("-----------------");

    ausschlagen(obj1);

    for (int i = 0; i < kBucket.length; i++)
      System.out.println("Element at index " + i + " : "+ kBucket[i]);

  }

  public static void ausschlagen(Object bandit) {
    for (int index = 0; index < kBucket.length; index++) {
      if (kBucket[index].equals(bandit)) {
        System.out.println(kBucket[index]);
        System.arraycopy(kBucket, index + 1, kBucket, index, kBucket.length - index - 1);
        return;
      }
    }
  }
}

      // if (kBucket[index].equals(bandit)) {
      //   System.arraycopy(kBucket, index + 1, kBucket, index, 0);
      // }
