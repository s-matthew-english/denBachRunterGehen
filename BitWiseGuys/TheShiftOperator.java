public class TheShiftOperator {
  /**
   * These operators take a single integer primitive and shift (or slide)
   * all of its bits in one direction or another. If you want to dust off
   * your binary math skills, you might realize that shifting bits *left*
   * effectively _multiplies_ a number by a power of two, and shifting 
   * bits *right* effectively _divides_ a number by a power of two.
   */



  public static void main(String[] args) {

    /**
     * Ok, ok, we've been putting this off, here is the world's shortest
     * explanation of storing negative numbers, and *two's compliment*.
     * Remember, the leftmost bit of an integer number is called the _sign bit_.
     * A negative integer number in Java *always* has its sign bit turned _on_ (i.e. set to 1).
     * A positive number always has its sign bit turned _off_ (0). Java uses the *two's compliment*
     * formula to store negative numbers. To change a number's sign using two's compliment, flip
     * all the bits, then add 1 (with a byte, for example, that would mean adding 00000001) to
     * the flipped value.
     */

    int x = -11;        // bits are 11110101

    System.out.println(x);

    /**
     * Right Shift Operator: 
     *
     * This operator shifts all of a number's bits right by a certain number, and fills all of
     * the bits on the left side with whatever the original leftmost bit was.
     * _The sign does *not* change_
     */
    int rightShift = x >> 2;     // bits are 11111101

    System.out.println(rightShift);

   /**
    * Unsigned Right Shift Operator: 
    * 
    * Just like the right shift operator BUT it ALWAYS fills the leftmost bits with zeros.
    * _The sign bit *might* change_
    */
   int unsignedRightShift = x >>> 2;  // bits are 00111101

   System.out.println(unsignedRightShift);

    /**
     * Left Shift Operator: 
     *
     * Just like the right shift operator, but in the other direction; the rightmost bits are
     * filled with zeros.
     * _The sign bit *might* change_
     */
    int lefShtift = x << 2;     // bits are 11010100

    System.out.println(lefShtift);
  }
}
