public class AppleFancyFormatter implements AppleFormatter {
  public String accept(Apple apple) {
    String characteristic = apple.getWeight() > 69 ? "heavy" : "light";
    return "A " + characteristic + " " + apple.getColour() + " apple";
  }
}
