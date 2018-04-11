public class AddIndex {

  private static Object[] kBucket  = new Object[8];


  public static void main(String[] args) {

    kBucket[0] = 'M';
    kBucket[1] = 'A';
    kBucket[2] = 'T';
    kBucket[3] = 'T';
    kBucket[4] = 'H';
    kBucket[5] = 'E';
    kBucket[6] = 'W';


    for (int i = 0; i < kBucket.length; i++)
      System.out.println("Element at index " + i + " : "+ kBucket[i]);

    System.out.println("-----------------");


    addToArray('9');


    for (int i = 0; i < kBucket.length; i++)
      System.out.println("Element at index " + i + " : "+ kBucket[i]);
  }

  public static void addToArray(Object newValue) {
    for(int i = 0; i < kBucket.length; i++)
    if(kBucket[i] == null)
        kBucket[i] = newValue;
  }
}
