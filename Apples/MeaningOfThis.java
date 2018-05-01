public class MeaningOfThis {
  public final int value = 4;
  public void doIt() {
    int value = 6;
    Runnable r = new Runnable() {
      // 'this' refers to the enclosing 'Runnable', not the enclosing class 'MeaningOfThis'
      public final int value = 5;
      public void run() {
        int value = 10;
        System.out.println(this.value);
      }
    };
    r.run();
  }
  //public static void main(String[] args) {
  public static void main(String... args) {
    MeaningOfThis m = new MeaningOfThis();
    m.doIt();
  }
}
