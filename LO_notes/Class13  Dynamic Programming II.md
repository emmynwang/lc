Array Hopper II: \#45
=====================

Given an array A of non-negative integers, you are initially positioned at index 0 of the array. A[i] means the maximum jump distance from index i (you can only jump towards the end of the array). Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.

Assumptions

* The given array is not null and has length of at least 1.

Examples

* {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
* {2, 1, 1, 0, 2}, you are not able to reach the end of array, return -1 in this case.

```java
public class Solution {
  public int minJump(int[] array) {
    if (array == null || array.length == 0) return 0;
    int[] jumps = new int[array.length];
    jumps[jumps.length - 1] = 0;
    for (int i = jumps.length - 2; i >= 0; i--) {
      int cur = Integer.MAX_VALUE;
      for (int j = 1; j <= array[i] && i + j < array.length; j++) {
        if (jumps[i + j] >= 0 && cur > jumps[i + j]) {
          cur = jumps[i + j];
        }
      }
      if (cur == Integer.MAX_VALUE) {
          jumps[i] = -1;
        } else {
          jumps[i] = cur + 1;
        }
    }
    return jumps[0];
  }
    
    // greedy solution
    public int minJump(int[] array) {
        if (array.length == 1) return 0;
        // $ of jumps currently
        int jump = 0;
        // max index by current # of jumps
        int cur = 0;
        // max index by current + 1 # of jumps
        int next = 0;
        for (int i = 0; i < array.length; i++) {
            if (i > cur) {
                // if index i can not be reached by current # of jumps,
                // we need jump one more step.
                jump++;
                // if there is no progress by jumping one more step,
                // means it is unreachable
                if (cur == next) {
                    return -1;
                }
                cur = next;
            }
            next = Math.max(next, array[i] + i);
        }
        return jump;
    }
}

```

Dictionary World:
=================

Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.

Assumptions

* The given word is not null and is not empty
* The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty

Examples

Dictionary: {“bob”, “cat”, “rob”}

* Word: “robob” return false
* Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"

```java
public class Solution {
  public boolean canBreak(String input, String[] dict) {
    boolean[] result = new boolean[input.length() + 1];
    result[0] = true;
    for (int i = 1; i < result.length; i++) {
      for (int j = 1; j <= i; j++) {
        // 0 ~ i-j: left part already known
        // i-j ~ j: right part, unknown
        String cur = input.substring(i - j, i);
        if (contains(dict, cur) && result[i - j] == true) {
          result[i] = true;
          break;
        }
      }
    }
    return result[input.length()];
  }
  private boolean contains(String[] dict, String cur) {
    for (String s : dict) {
      if (cur.equals(s)) {
        return true;
      }
    }
    return false;
  }
}

```

Edit Distance:
==============

Given two strings of alphanumeric characters, determine the minimum number of Replace, Delete, and Insertoperations needed to transform one string into the other.

Assumptions

* Both strings are not null

Examples

string one: “sigh”, string two : “asith”

the edit distance between one and two is 2 (one insert “a” at front then replace “g” with “t”).

```java
public class Solution {
  public int editDistance(String one, String two) {
    int m = one.length();
    int n = two.length();
    // base case;
    int[][] M = new int[m + 1][n + 1];
    for (int i = 0; i < m + 1; i++) {
      M[i][0] = i;
    }
    for (int i = 0; i < n + 1; i++) {
      M[0][i] = i;
    }
    // loop over to find the min distance
    for (int i = 1; i < m + 1; i++) {
      for (int j = 1; j < n + 1; j++) {
        if (one.charAt(i - 1) == two.charAt(j - 1)) {
            // if first char equals, do nothing
          M[i][j] = M[i - 1][j - 1];
        } else {
          M[i][j] = Math.min(Math.min(M[i - 1][j], M[i][j - 1]), M[i - 1][j - 1]) + 1;
        }
      }
    }
    return M[m][n];
  }
}

```

Largest Square Of 1s:
=====================

Determine the largest square of 1s in a binary matrix (a binary matrix only contains 0 and 1), return the length of the largest square.

Assumptions

* The given matrix is not null and guaranteed to be of size N \* N, N \>= 0

Examples

{ {0, 0, 0, 0},

 {1, 1, 1, 1},

 {0, 1, 1, 1},

 {1, 0, 1, 1}}

the largest square of 1s has length of 2

```java
public class Solution {
  public int largest(int[][] matrix) {
    int[][] ones = new int[matrix.length][matrix[0].length];
    int max = 0;
    for (int i = 0; i < ones.length; i++) {
      for (int j = 0; j < ones[0].length; j++) {
        if (matrix[i][j] == 0) {
          ones[i][j] = 0;
        } else {
          if (i - 1 < 0 || j - 1 < 0) { //
            ones[i][j] = 1;
          } else {
            ones[i][j] = Math.min(Math.min(ones[i - 1][j - 1], ones[i][j - 1]), ones[i - 1][j]) + 1;
          }
        }
        if (ones[i][j] > max) {
          max = ones[i][j];
        }
      }
    }
    return max;
  }
}

```

