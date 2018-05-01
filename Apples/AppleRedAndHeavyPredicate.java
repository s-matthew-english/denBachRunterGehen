public class AppleRedAndHeavyPredicate implements ApplePredicate {
  public boolean test(Apple apple) {
    return "red".equals(apple.getColour()) && apple.getWeight() > 69;
  }
}
