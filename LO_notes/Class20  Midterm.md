Maximum Path Sum Binary Tree I: 
================================

Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN\_VALUE(Java)/INT\_MIN (C++).

Examples

 -15

 / \\

2 11

 / \\

 6 14

The maximum path sum is 6 + 11 + 14 = 31.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "\#" denoting the null node.

For Example:

The sequence [1, 2, 3, \#, \#, 4] represents the following binary tree:

 1

 / \\

 2 3

 /

 4

Similar Problems: LCA

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
  private int max = Integer.MIN_VALUE;
  public int maxPathSum(TreeNode root) {
    helper(root);
    return max;
  }
  
  private Integer helper(TreeNode root) {
    if (root == null) {
      return null;
    }
    Integer left = helper(root.left);
    Integer right = helper(root.right);
    if (left != null && right != null) {
      max = Math.max(max, left + right + root.key);
      return Math.max(left, right) + root.key;
    } else if (left != null || right != null) {
      return left == null ? right + root.key : left + root.key;
    }
    return root.key;
  }
}

```

Minumum Cuts For Palindromes:

Given a string, a partitioning of the string is a *palindrome partitioning* if every substring of the partition is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.

Assumptions

* The given string is not null

Examples

“a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.

The minimum number of cuts needed is 3.

```java
public class Solution {
  public int minCuts(String input) {
    if (input == null || input.length() < 2) {
      return 0;
    }
    char[] array = input.toCharArray();
    int[] minCut = new int[array.length + 1];
    minCut[0] = -1;
    for (int i = 1; i < minCut.length; i++) {
      int min = Integer.MAX_VALUE;
      // total to be calculated: [0, i]
      // left section: [0, j)
      // right section: [j, i]
      for (int j = i; j > 0; j--) {
        if (isPalindrome(array, j - 1, i - 1)) {
          min = Math.min(minCut[j - 1] + 1, min);
        }
      }
      minCut[i] = min;
    }
    return minCut[minCut.length - 1];
  }
  private boolean isPalindrome(char[] array, int i, int j) {
    while (i < j) {
      if (array[i] != array[j]) return false;
      i++;
      j--;
    }
    return true;
  }
}

```

