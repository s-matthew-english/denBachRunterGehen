package com.smatthewenglish;

import java.util.Objects;

public final class Id {
  private static final long serialVersionUID = 1L;

  private final BitString bitString;

  // make sure that whatever you pass in as data is a copy / not-shared.
  private Id(BitString bitString) {

    this.bitString = bitString;
  }

  /**
   * Constructs an {@link Id} from a bit string.
   * @param data id value
   * @return created id
   * @throws NullPointerException if any argument is {@code null}
   */
  public static Id create(BitString data) {

    return new Id(data);
  }

  /**
   * Constructs an {@link Id} from a byte array. Input byte array is read in read-order
   * (see {@link BitString#createReadOrder(byte[], int, int)}).
   * @param data id value
   * @param bitLength number of bits in this id
   * @return created id
   * @throws NullPointerException if any argument is {@code null}
   * @throws IllegalArgumentException if {@code bitLength <= 0}, or if {@code data} is larger than the minimum number of bytes that it
   * takes to retain {@code bitLength} (e.g. if you're retaining 12 bits, you need 2 bytes or less -- {@code 12/8 + (12%8 == 0 ? 0 : 1)})
   */
  public static Id create(byte[] data, int bitLength) {

    return new Id(BitString.createReadOrder(data, 0, bitLength));
  }

  /**
   * Constructs an {@link Id} from a string of 1s and 0s. Equivalent to calling {@code create(BitString.createFromString(data))}.
   * @param data id value
   * @return created id
   * @throws NullPointerException if any argument is {@code null}
   * @throws IllegalArgumentException if number of chars in {@code data} is {@code <= 0}, or if if any chracter other than {@code '0'} or
   * {@code '1'} is encountered in {@code data}
   */
  public static Id create(String data) {
    return create(BitString.createFromString(data));
  }

  /**
   * Constructs an {@link Id} from a long. Input long is read in read-order, meaning that bits are read in from left-to-right. In addition
   * to that, the last bit is always at bit 0 of the long. So for example, creating an id from the long {@code 0xABCDL} with a bitlength
   * of 12 would result in the bit string {@code 10 1011 1100 1101}.
   * <pre>
   * Bit     15 14 13 12   11 10 09 08   07 06 05 04   03 02 01 00
   *         ------------------------------------------------------
   *         1  0  1  0    1  0  1  1    1  1  0  0    1  1  0  1
   *         A             B             C             D
   *               ^                                            ^
   *               |                                            |
   *             start                                         end
   * </pre>
   * @param data id value
   * @param bitLength number of bits in this id
   * @return created id
   * @throws NullPointerException if any argument is {@code null}
   * @throws IllegalArgumentException if {@code bitLength <= 0}, or if {@code data} is larger than the minimum number of bytes that it
   * takes to retain {@code bitLength} (e.g. if you're retaining 12 bits, you need 2 bytes or less -- {@code 12/8 + (12%8 == 0 ? 0 : 1)})
   */
  public static Id createFromLong(long data, int bitLength) {

    data = data << (64 - bitLength);
    return new Id(BitString.createReadOrder(toBytes(data), 0, bitLength));
  }

  private static byte[] toBytes(long data) { // returns in big endian format
    byte[] bytes = new byte[8];
    for (int i = 0; i < 8; i++) {
      int shiftAmount = 56 - (i * 8);
      bytes[i] = (byte) (data >>> shiftAmount);
    }
    return bytes;
  }

  /**
   * Equivalent to {@link BitString#getSharedPrefixLength}.
   * @param other other ID to test against
   * @return number of common prefix bits
   * @throws NullPointerException if any argument is {@code null}
   * @throws IllegalArgumentException if the bitlength from {@code this} doesn't match the bitlength from {@code other}
   */
  public int getSharedPrefixLength(Id other) {

    return bitString.getSharedPrefixLength(other.bitString);
  }

  /**
   * Equivalent to {@link BitString#getSharedSuffixLength}.
   * @param other other ID to test against
   * @return number of common suffix bits
   * @throws NullPointerException if any argument is {@code null}
   * @throws IllegalArgumentException if the bitlength from {@code this} doesn't match the bitlength from {@code other}
   */
  public int getSharedSuffixLength(Id other) {

    return bitString.getSharedSuffixLength(other.bitString);
  }

  /**
   * Equivalent to {@link BitString#flipBit(int) }.
   * @param offset offset of bit
   * @return new id that has bit flipped
   * @throws IllegalArgumentException if {@code offset < 0} or if {@code offset > bitLength}
   */
  public Id flipBit(int offset) {
    return new Id(bitString.flipBit(offset));
  }

  /**
   * Equivalent to {@link BitString#getBitsAsLong(int, int) }.
   * @param offset offset of bit within this bitstring to read from
   * @param len number of bits to get
   * @return bits starting from {@code offset} to {@code offset + len} from this bitstring
   * @throws IllegalArgumentException if {@code offset < 0} or if {@code offset > bitLength} or
   * {@code offset + other.bitLength > bitLength}
   */
  public long getBitsAsLong(int offset, int len) {
    return bitString.getBitsAsLong(offset, len);
  }

  /**
   * Equivalent to {@link BitString#setBits}, but with a long.
   * @param offset offset of bit within this bitstring to write to
   * @param other bits to set
   * @param len number of bits to set
   * @return new id that has bit set
   * @throws IllegalArgumentException if {@code offset < 0} or if {@code offset > bitLength} or
   * {@code offset + other.bitLength > bitLength}
   */
  public Id setBitsAsLong(long other, int offset, int len) {
    BitString modifiedBitString = bitString.setBits(offset, Id.createFromLong(other, len).bitString);
    return new Id(modifiedBitString);
  }

  /**
   * Equivalent to {@link BitString#setBits}.
   * @param offset offset of bit within this bitstring to write to
   * @param bitString bits to set
   * @return new id that has bit set
   * @throws IllegalArgumentException if {@code offset < 0} or if {@code offset > bitLength} or
   * {@code offset + other.bitLength > bitLength}
   */
  public Id setBits(int offset, BitString bitString) {
    BitString modifiedBitString = this.bitString.setBits(offset, bitString);
    return new Id(modifiedBitString);
  }

  /**
   * Gets the maximum bit length for this ID.
   * @return max bit length for ID
   */
  public int getBitLength() {
    return bitString.getBitLength();
  }

  /**
   * Gets a copy of the data for this ID as a bitstring.
   * @return ID as bit string
   */
  public BitString getBitString() {
    return bitString;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 31 * hash + Objects.hashCode(this.bitString);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Id other = (Id) obj;
    if (!Objects.equals(this.bitString, other.bitString)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "Id{" + "bitString=" + bitString + '}';
  }
}
