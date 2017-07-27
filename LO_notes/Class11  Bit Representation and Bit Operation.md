Power Of Two:

Determine if a given integer is power of 2.

Examples

* 16 is power of 2 (2 ^ 4)
* 3 is not
* 0 is not
* -1 is not

```java
public class Solution {
    // use the trick of number & (number - 1) will remove the right most 1!
  public boolean isPowerOfTwo(int number) {
    return number > 0 && (number & (number - 1)) == 0;
  }
}
```

Number Of Different Bits:

Determine the number of bits that are different for two given integers.

Examples

* 5(“0101”) and 8(“1000”) has 3 different bits

```java
public class Solution {
  public int diffBits(int a, int b) {
    a ^= b;
    b = 0;
    // we use logical right shift >>>
    // notice terminate condition
    for (int i = 0; i < 32; i++) {
      b += a & 1;
      a = a >>> 1;
    }
    return b;
  }
}
```

All Unique Characters II:

Determine if the characters of a given string are all unique.

Assumptions

* We are using ASCII charset, the value of valid characters are from 0 to 255
* The given string is not null

Examples

* all the characters in "abA+\\8" are unique
* "abA+\\a88" contains duplicate characters

```java
public class Solution {
  public boolean allUnique(String word) {
    // each int value cazn represent 32 bit, so we need 8 ints to represent 256 bits
    if (word == null) {
      return true;
    }
    char[] array = word.toCharArray();
    int[] bitMap = new int[8];
    for (char c : array) {
      int row = c / 32;
      int col = c % 32;
      if (((bitMap[row] >> col) & 1) == 1) {
        return false;
      }
      bitMap[row] |= 1 << col;
    }
    return true;
  }
}
```

Hexadecimal Representation:

Generate the hexadecimal representation for a given non-negative integer number as a string. The hex representation should start with "0x".

There should not be extra zeros on the left side.

Examples

* 0's hex representation is "0x0"
* 255's hex representation is "0xFF"

```java
public class Solution {
  public String hex(int number) {
      // assume: number >= 0
    if (number < 0) {
      return null;
    }
    if (number == 0) {
      return "0x0";
    }
    StringBuilder sb = new StringBuilder();
    // use a stringbuilder so that append operation more efficient
    while (number > 0) {
      sb.append(toChar(number % 16));
      number /= 16;
    }
    sb.append("x0");
    return sb.reverse().toString();
  }
  private char toChar(int num) {
    if (num >= 0 && num <= 9) {
      return (char) ('0' + num);
    } else if (num > 9 && num < 16) {
      return (char) ('A' + num - 10);
    } else {
      return ' ';
    }
  }
}

```

