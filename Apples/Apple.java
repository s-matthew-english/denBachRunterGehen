 /**
  * In Java there are two types of nested classes: 'Static nested class' and 'Inner class'. Without the 'static' 
  * keyword it's an inner class and you will need an instance of 'Apple' to access... 
  **/
public class Apple extends Object {

  String colour;
  String name;
  int weight;

  Apple(String colour, String name, int weight) {
    this.colour = colour;
    this.name = name;
    this.weight = weight;
  }

  String getColour() {
    return colour;
  }

  int getWeight() {
    return weight;
  }

  String getName() {
    return name;
  }
}
