public class A {
  int i;

  A(int i) {
    this.i = i;
  }

  public void method() {
    System.out.println("this works!");
  }

  @Override
  public String toString() {
    return String.valueOf(i);
  }
}
