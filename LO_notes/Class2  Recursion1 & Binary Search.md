Fibonacci number:

Get the Kth number in the Fibonacci Sequence. (K is 0-indexed, the 0th Fibonacci number is 0 and the 1st Fibonacci number is 1).

Examples

* 0th fibonacci number is 0
* 1st fibonacci number is 1
* 2nd fibonacci number is 1
* 3rd fibonacci number is 2
* 6th fibonacci number is 8

Corner Cases

* What if K \< 0? in this case, we should always return 0.
* Is it possible the result fibonacci number is overflowed? We can assume it will not be overflowed when we solve this problem on this online judge, but we should also know that it is possible to get an overflowed number, and sometimes we will need to use something like BigInteger.

```java
class FibonacciNumber {
    public long fibonacci(int K) {
        if (K < 0) {
          return 0;
        } else if (K < 2) {
          return K;
        }
        long one = 0;
        long two = 1;
        long tmp;
        for (int i = 2; i <= K; i++) {
          tmp = one + two;
          one = two;
          two = tmp;
        }
    return two;
    }
}
```

A To The Power Of B:

Evaluate a to the power of b, assuming both a and b are integers and b is non-negative. 

Examples

* power(2, 0) = 1
* power(2, 3) = 8
* power(0, 10) = 0
* power(-2, 5) = -32

Corner Cases

* What if the result is overflowed? We can assume the result will not be overflowed when we solve this problem on this online judge.

```java
class pwer {
    public long power(int a, int b) {
        if (b == 0) {
          return 1;
        } else {
          long half = power(a, b / 2);
          if (b % 2 == 0) {
            return half * half;
          } else {
            return half * half * a;
          }
        }
    }
}
```

Classical Binary Search:

Given a target integer T and an integer array A sorted in ascending order, find the index i such that A[i] == T or return -1 if there is no such index.

Assumptions

* There can be duplicate elements in the array, and you can return any of the indices i such that A[i] == T.

Examples

* A = {1, 2, 3, 4, 5}, T = 3, return 2
* A = {1, 2, 3, 4, 5}, T = 6, return -1
* A = {1, 2, 2, 2, 3, 4}, T = 2, return 1 or 2 or 3

Corner Cases

* What if A is null or A is of zero length? We should return -1 in this case.

```java
class BinarySearch {
    public int binarySearch(int[] array, int target) {
        if (array == null || array.length < 1) {
          return -1;
        }
        return binarySearch(array, 0, array.length - 1, target);
    }
    private int binarySearch(int[] array, int left, int right, int target) {
        if (left > right) {
          return -1;
        }
        int mid = left + (right - left) / 2;
        if (array[mid] == target) {
          return mid;
        } else if (array[mid] > target) {
          return binarySearch(array, left, mid - 1, target);
        } else {
          return binarySearch(array, mid + 1, right, target);
        }
    }
}
```

First Occurrence:

Given a target integer T and an integer array A sorted in ascending order, find the index of the first occurrence of T in A or return -1 if there is no such index.

Assumptions

* There can be duplicate elements in the array.

Examples

* A = {1, 2, 3}, T = 2, return 1
* A = {1, 2, 3}, T = 4, return -1
* A = {1, 2, 2, 2, 3}, T = 2, return 1

Corner Cases

* What if A is null or A of zero length? We should return -1 in this case.

```java
class FirstOccur {
    public int firstOccur(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        // we need to use left < right - 1 here to make sure there is no infinite loop
        // when left == right - 1,
        // then mid == left, it will be possible picking [mid, right] for the next round
        // and it will go into an infinite loop in that case.
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // carefull of 5 possible situations:
        // 1. array has only 1 element
        // 2. array has only 2 element
        // 3. left == right - 1 and left is the result
        // 4. left == right - 1 and right is the result
        // 5. left == right - 1 and none of left, right is the result
        if (array[left] == target) {
            return left;
        } else if (array[right] == target) {
            return right;
        } else {
            return -1;
        }
    }
}
```

Last Occurrence:

Given a target integer T and an integer array A sorted in ascending order, find the index of the last occurrence of T in A or return -1 if there is no such index.

Assumptions

* There can be duplicate elements in the array.

Examples

* A = {1, 2, 3}, T = 2, return 1
* A = {1, 2, 3}, T = 4, return -1
* A = {1, 2, 2, 2, 3}, T = 2, return 3

Corner Cases

* What if A is null or A is array of zero length? We should return -1 in this case.

```java
public class LastOccurrence {
    public int lastOccur(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        if (array[right] == target) {
            return right;
        } else if (array[left] == target) {
            return left;
        } else {
            return -1;
        }
    }
}
```

Closest in Sorted Array:

Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to T.

Assumptions

* There can be duplicate elements in the array, and we can return any of the indices with same value.

Examples

* A = {1, 2, 3}, T = 2, return 1
* A = {1, 4, 6}, T = 3, return 1
* A = {1, 4, 6}, T = 5, return 1 or 2
* A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2

Corner Cases

* What if A is null or A is of zero length? We should return -1 in this case.

```java
public class Closest {
    public int closest(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                left = mid; 
            } else {
                right = mid;
            }
        }
        if (Math.abs(array[left] - target) <= Math.abs(array[right] - target)) {
            return left;
        }
        return right;
    }
}
```

K Closest In Sorted Array:

Given a target integer T, a non-negative integer K and an integer array A sorted in ascending order, find the K closest numbers to T in A.

Assumptions

* A is not null
* K is guranteed to be \>= 0 and K is guranteed to be \<= A.length

Return

* A size K integer array containing the K closest numbers(not indices) in A, sorted in ascending order by the difference between the number and T.

Examples

* A = {1, 2, 3}, T = 2, K = 3, return {2, 1, 3} or {2, 3, 1}
* A = {1, 4, 6, 8}, T = 3, K = 3, return {4, 1, 6}

```java
public class kClosest {
    public static int[] kClosest(int[] array, int target, int k) {
        if (array == null || array.length == 0) {
            return null;
        }
        if (k == 0) {
            return new int[0];
        }
        int left = 0;
        int right = array.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] == target) {
                break;
            } else if (array[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (right == left) {
            right++;
        }
        System.out.println(left + " " + right);
        int[] ret = new int[k];
        for (int i = 0; i < k; i++) {
            // we can advance the left pointer when:
            // 1. right pointer is already out of bound
            // 2. right pointer is not out of bound, left pointer is not out of
            //    bound, and array[left] is closer to target!!!
            // devastated in this if statement!
            if (right >= array.length || left >= 0 && target - array[left] <= array[right] - target) {
                ret[i] = array[left--];
            } else {
                ret[i] = array[right++];
            }
        }
        return ret;
    }
}
```

Search In Unknown Sized Sorted Array:

Given a integer dictionary A of unknown size, where the numbers in the dictionary are sorted in ascending order, determine if a given target integer T is in the dictionary. Return the index of T in A, return -1 if T is not in A.

Assumptions

* dictionary A is not null
* dictionary.get(i) will return null if index i is out of bounds

Examples

* A = {1, 2, 5, 9, ......}, T = 5, return 2
* A = {1, 2, 5, 9, 12, ......}, T = 7, return -1

```java
/*
*  interface Dictionary {
*    public Integer get(int index);
*  }
*/

// You do not need to implement the Dictionary interface.
// You can use it directly, the implementation is provided when testing your solution.
public class UnknownSizeBinarySearch {
  public int search(Dictionary dict, int target) {
    if (dict == null) {
      return -1;
    }
    int left = 0;
    int right = 1;
    // find the right boundary for binary search
    // extends until we are sure the target is within the range
    while (dict.get(right) != null) {
        left = right;
      right *= 10;
    }
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (dict.get(mid) == null || target < dict.get(mid)) {
        right = mid - 1;
      } else if (dict.get(mid) == target){
        return mid;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}
```

Binary Search In Sorted 2D Array1:

Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. The first element of next row is larger than (or equal to) the last element of previous row.

Given a target number, returning the position that the target locates within the matrix. If the target number does not exist in the matrix, return {-1, -1}.

Assumptions:

* The given matrix is not null, and has size of N \* M, where N \>= 0 and M \>= 0.

Examples:

matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} }

target = 7, return {1, 2}

target = 6, return {-1, -1} to represent the target number does not exist in the matrix.

```java
public class Solution {
  public int[] search(int[][] matrix, int target) {
    if (matrix == null) {
      return new int[] {-1, -1};
    }
    int m = matrix.length;
    int n = matrix[0].length;
    int left = 0;
    // convert the 2D array to 1D array with m * n elements
    int right = m * n - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      // convert the position in 1D array back to row and col in 2D
      int r = mid / n;
      int c = mid % n;
      if (matrix[r][c] == target) {
        return new int[]{r, c};
      }
      if (matrix[r][c] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return new int[] {-1, -1};
  }
}
```