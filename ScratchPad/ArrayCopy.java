public class ArrayCopy {

  private static char[] kBucket;

  public static void main(String[] args) {

    kBucket = new char[]{'M', 'A', 'T', 'T', 'H', 'E', 'W'};

    for (int i = 0; i < kBucket.length; i++)
      System.out.println("Element at index " + i + " : "+ kBucket[i]);

    System.out.println("-----------------");

    moveToHead(Integer.valueOf(args[0]));

    for (int i = 0; i < kBucket.length; i++)
      System.out.println("Element at index " + i + " : "+ kBucket[i]);
  }
    public static void moveToHead(int idx) {
    // Moving the first element to the head should do nothing.
    // Ignore invalid indices.
    if (idx <= 0 || idx >= kBucket.length) {
      return;
    }

    char peer = kBucket[idx];

    // starting:
    // idx = 3
    //       *
    // 0 1 2 3 4 5 6
    // - 0 1 2 4 5 6

    // ending:
    // 3 0 1 2 4 5 6
    System.arraycopy(kBucket, 0, kBucket, 1, idx);
    kBucket[0] = peer;
  }
}
