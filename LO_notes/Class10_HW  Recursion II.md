Reverse Linked List In Pairs:
=============================

Reverse pairs of elements in a singly-linked list.

Examples

* L = null, after reverse is null
* L = 1 -\> null, after reverse is 1 -\> null
* L = 1 -\> 2 -\> null, after reverse is 2 -\> 1 -\> null
* L = 1 -\> 2 -\> 3 -\> null, after reverse is 2 -\> 1 -\> 3 -\> null

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode reverseInPairs(ListNode head) {
    // Write your solution here.
    if (head == null || head.next == null) {
      return head;
    }
    ListNode cur = head;
    ListNode next = head.next;
    ListNode newHead = reverseInPairs(next.next);
    cur.next = newHead;
    next.next = cur;
    return next;
  }
}

```

Spiral Order Traverse I:
========================

Traverse an N \* N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

* The 2D array is not null and has size of N \* N where N \>= 0

Examples

{ {1, 2, 3},

 {4, 5, 6},

 {7, 8, 9} }

the traversal sequence is [1, 2, 3, 6, 9, 8, 7, 4, 5]

```java
public class Solution {
  public List<Integer> spiral(int[][] matrix) {
    // Write your solution here.
    List<Integer> result = new ArrayList<>();
    spiral(matrix, result, 0);
    return result;
  }
  
  private void spiral(int[][] matrix, List<Integer> result, int offset) {
    int size = matrix.length - 2 * offset;
    if (size == 0) return;
    if (size == 1) {
      result.add(matrix[offset][offset]);
      return;
    }
    for (int i = 0; i < size - 1; i++) {
      result.add(matrix[offset][offset + i]);
    }
    for (int i = 0; i < size - 1; i++) {
      result.add(matrix[offset + i][offset + size - 1]);
    }
    for (int i = size - 1; i >= 1; i--) {
      result.add(matrix[offset + size - 1][offset + i]);
    }
    for (int i = size - 1; i >= 1; i--) {
      result.add(matrix[offset + i][offset]);
    }
    spiral(matrix, result, offset + 1);
  }
}

```

Spiral Order Traverse II:
=========================

Traverse an M \* N 2D array in spiral order clock-wise starting from the top left corner. Return the list of traversal sequence.

Assumptions

* The 2D array is not null and has size of M \* N where M, N \>= 0

Examples

{ {1, 2, 3, 4},

 {5, 6, 7, 8},

 {9, 10, 11, 12} }

the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]

```java
public class Solution {
  public List<Integer> spiral(int[][] matrix) {
    // Write your solution here.
    int m = matrix.length; // row 
    List<Integer> result = new ArrayList<>();
    if (m == 0) {
      return result;
    }
    int n = matrix[0].length; // col 
    int top = 0;
    int bot = m - 1;
    int left = 0;
    int right = n - 1;
    while (top < bot && left < right) {
      for (int i = left; i < right; i++) {
        result.add(matrix[top][i]);
      }
      for (int i = top; i < bot; i++) {
        result.add(matrix[i][right]);
      }
      for (int i = right; i > left; i--) {
        result.add(matrix[bot][i]);
      }
      for (int i = bot; i > top; i--) {
        result.add(matrix[i][left]);
      }
      left++;
      right--;
      top++;
      bot--;
    }
    if (left > right || top > bot) {
      return result;
    }
    if (left == right) {
      for (int i = top; i <= bot; i++) {
        result.add(matrix[i][right]);
      }
      return result;
    }
    if (top == bot) {
      for (int i = left; i <= right; i++) {
        result.add(matrix[top][i]);
      }
      return result;
    }
    return result;
  }
}

```

Spiral Order Generate II:
=========================

Generate an M \* N 2D array in spiral order clock-wise starting from the top left corner, using the numbers of 1, 2, 3, …, M \* N in increasing order.

Assumptions

* M \>= 0, N \>= 0

Examples

M = 3, N = 4, the generated matrix is

{ {1, 2, 3, 4}

 {10, 11, 12, 5},

 {9, 8, 7, 6} }

```java
public class Solution {
  public int[][] spiralGenerate(int m, int n) {
    // Write your solution here.
    int[][] matrix = new int[m][n];
    int left = 0;
    int right = n - 1;
    int top = 0;
    int bot = m - 1;
    int count = 1;
    while (left < right && top < bot) {
      for (int i = left; i < right; i++) {
        matrix[top][i] = count++;
      }
      for (int i = top; i < bot; i++) {
        matrix[i][right] = count++;
      }
      for (int i = right; i > left; i--) {
        matrix[bot][i] = count++;
      }
      for (int i = bot; i > top; i--) {
        matrix[i][left] = count++;
      }
      left++;
      right--;
      top++;
      bot--;
    }
    if (left > right || top > bot) {
      return matrix;
    }
    if (left == right) {
      for (int i = top; i <= bot; i++) {
        matrix[i][left] = count++;
      }
      return matrix;
    }
    if (top == bot) {
      for (int i = left; i <= right; i++) {
        matrix[top][i] = count++;
      }
      return matrix;
    }
    return null;
  }
}

```

Lowest Common Ancestor I:
=========================

Given two nodes in a binary tree, find their lowest common ancestor.

Assumptions

* There is no parent pointer for the nodes in the binary tree
* The given two nodes are guaranteed to be in the binary tree

Examples

 5

 / \\

 9 12

 / \\ \\

 2 3 14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

```java
/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    // Write your solution here.
    if (root == null) {
      return null;
    }
    TreeNode left = lowestCommonAncestor(root.left, one, two);
    TreeNode right = lowestCommonAncestor(root.right, one, two);
    if (root == one) {
      return one;
    } else if (root == two) {
      return two;
    }
    if (left != null && right != null) return root;
    return left == null ? right : left;
  }
}

```

N Queens:
=========

Get all valid ways of putting N Queens on an N \* N chessboard so that no two Queens threaten each other.

Assumptions

* N \> 0

Return

* A list of ways of putting the N Queens
* Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)

Example

N = 4, there are two ways of putting 4 queens:

[1, 3, 0, 2] --\> the Queen on the first row is at y index 1, the Queen on the second row is at y index 3, the Queen on the third row is at y index 0 and the Queen on the fourth row is at y index 2.

[2, 0, 3, 1] --\> the Queen on the first row is at y index 2, the Queen on the second row is at y index 0, the Queen on the third row is at y index 3 and the Queen on the fourth row is at y index 1.

```java
public class Solution {
  public List<List<Integer>> nqueens(int n) {
    // Write your solution here.
    List<List<Integer>> result = new ArrayList<>();
		int[] cur = new int[n]; // array of int, not list, 
		if (n < 0) return result;
		nQueens(result, 0, n, cur);
		return result;
  }
  // cur[] current res, it may be not valid, index: row # 内存只能读行信息; n: given size
  private void nQueens(List<List<Integer>> result, int index, int n, int[] cur) {
    // 如果触底，返回res
    if (index == n) { 
      List<Integer> list = new ArrayList<>();
      // from array to list
      for (int i : cur) {
        list.add(i);
      }
      result.add(list);
      return;
    }
    // for this row, 这一横行，如果valid, recursive next row. why not cur[index] = 0
    for (int i = 0; i < n; i++) {
      cur[index] = i;   //array, 如果index = 4 isNotValid, return 
      if (isValid(cur, index)) {
        nQueens(result, index + 1, n, cur);
      }
    }
  }
  private boolean isValid(int[] cur, int index) {
    int col = cur[index]; //
    for (int i = 0; i < index; i++) {
      if (cur[i] == col || Math.abs(cur[index] - cur[i]) == index - i) {
        return false;
      }
    }
    return true;
  }
}
```

String Abbreviation Matching:
=============================

Word “book” can be abbreviated to 4, b3, b2k, etc. Given a string and an abbreviation, return if the string matches the abbreviation.

Assumptions:

* The original string only contains alphabetic characters.
* Both input and pattern are not null.

Examples:

* pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.

```java
public class Solution {
  public boolean match(String input, String pattern) {
    // Write your solution here.
    int i = 0; // index of input string
    int j = 0; // index of pattern
    while (i < input.length() && j < pattern.length()) {
      
      if (pattern.charAt(j) < '0' || pattern.charAt(j) > '9') {
        // case1: when pattern is not a number
        if (pattern.charAt(j) != input.charAt(i)) {
          return false;
        }
        i++;
        j++;
      } else {
        // case2: when pattern is a number
        int count = 0;
        while (j < pattern.length() && 
        pattern.charAt(j) >= '0' && pattern.charAt(j) <= '9') {
          count = count * 10 + pattern.charAt(j) - '0';
          j++;
        }
        i += count;
      }
    }
    return i == input.length() && j == pattern.length();
  }
}

```