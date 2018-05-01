public class AppleGreenColourPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return "green".equals(apple.getColour());
  }
}
