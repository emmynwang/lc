Longest Ascending Subarray:
===========================

Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.

Assumptions

* The given array is not null

Examples

* {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
* {1, 2, 3, 3, 4, 4, 5}, longest ascending subarray is {1, 2, 3}, length is 3.

```java
public class Solution {
  public int longest(int[] array) {
    if (array.length < 1) return 0;
    int curr = 1; // stores longest acsending containing current
    int max = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] > array[i - 1]) {
        curr++;
        if (curr > max) {
          max = curr;
        }
      } else {
        curr = 1; // do we need to set up this case ???
      }
    }
    return max;
  }
}

```

Max Product Of Cutting Rope:
============================

Given a rope with positive integer-length *n*, how to cut the rope into *m* integer-length parts with length *p*[0], *p*[1], ...,*p*[*m*-1], in order to get the maximal product of *p*[0]\**p*[1]\* ... \**p*[*m*-1]? *m *is determined by you and must be greater than 0 (at least one cut must be made). Return the max product you can have.

Assumptions

* n \>= 2

Examples

* n = 12, the max product is 3 \* 3 \* 3 \* 3 = 81(cut the rope into 4 pieces with length of each is 3).

The right most

```java
public class Solution {
  public int maxProduct(int length) {
    int[] maxProduct = new int[length + 1];
    maxProduct[0] = 0;
    maxProduct[1] = 1;
    for (int i = 2; i < maxProduct.length; i++) {
      int max = 0;
      for (int j = 1; j < i; j++) {
        int cur = Math.max(maxProduct[j], j) * (i -j);
        if (cur > max) {
          max = cur;
        }
      }
      maxProduct[i] = max;
    }
    return maxProduct[length];
  }
}

```

Array Hopper I:
===============

Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from that position (you can only jump towards the end of the array).Determine if you are able to reach the last index.

\#55

Assumptions

* The given array is not null and has length of at least 1.

Examples

* {1, 3, 2, 0, 3}, we are able to reach the end of array(jump to index 1 then reach the end of the array)
* {2, 1, 1, 0, 2}, we are not able to reach the end of array

```java
public class Solution {
  public boolean canJump(int[] array) {
    if (array == null || array.length < 2) {
      return true;
    }
    array[array.length - 1] = 0; // 0 if can jump to the end
    for (int i = array.length - 2; i >= 0; i--) {
      if (array[i] == 0) {
        array[i] = 1;
        continue;
      }
      for (int j = 0; j <= array[i]; j++) {
        if (i + j < array.length && array[i + j] == 0) {
          array[i] = 0;
          break;
        }
      }
    }
    return array[0] == 0;
  }
  // greedy solution
    public boolean canJump(int[] array) {
        if (array.length == 1) return true;
        // the max index jumping current steps can reach
        int cur = 0;
        // the max index jumping current + 1 steps can reach
        int next = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > cur) {
                // if i > cur, we can not use current steps to jump to i
                if (cur == next) {
                    // cur == next means there is no progress for using
                    // current + 1 jump steps
                    return false;
                }
                cur = next;
            }
            next = Math.max(next, i + array[i]);
        }
        return true;
    }
}

```

