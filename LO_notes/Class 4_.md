基本知识点1： tree trverse

(1) Pre - order

implementation with recursion:

1\. Pre-order: 我把我自己放在最前面来打印

2\. 

Trick: base case usually refers to the null ChildNode below the leaf node.

基本概念

* Balanced Binary Tree: is commonly defined as a binary tree in which the depth of the left and right subtrees of every node differ by 1 or less.

### Conclusion 1: if a tree has n numbers of nodes. and it’s Balanced, then the height (level) of the tree = O(log\_2(n))

Complete Binary Tree: is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible.

### Conclusion 2: If a tree is a complete tree, then it must be a balanced tree.

### Q3: Let’s assume if we tweak the leftChild with rightChild of an arbitray node in a binary tree. Then, the structure of the tree are not changed. How can we determine whether two binary trees’s structure are identical. 

 8a and 8b or 8

 / \\ / \\ / \\

 4a 5a 4b 5b 5b 4b 

 / / \\

7 7 7

return isIdentical (left.left, right. left) && isIdentical(left.right, right.right) // case 1

|| 

isIdentical(left.left, right.right) && isIdentical(left.right, right.left) // case 2

if the tree were a binary tree, then the total number of nodes depend on the last layer (leaf layer) 12 4 8 16 32…

2^n =\> O(2^n)

The recursion tree is actually a Quadral tree. 1 4 16 64 256…

### Q: How many leaf nodes in the last layer?

A: To answer that question, we must first answer how many levels in this quadral tree?

Because the original input tree is balanced (assumed), it has log\_2(n) level. 

### Total number of nodes in this quadral tree = 4^(log\_2(n)) = 2^(2\* log\_2(n)) = 2 ^ (log\_2(n^2)) = O(n^2)

2\. Binary Search Tree
----------------------

经典例题1: How to determine a binary tree is a BST? (LC 98)

Solution1 (Our way)

 10 (min= -inf, max = +inf) — root

 / \\

 5 (min= -inf, max=10) 15

 / \\ / \\

 2 (min= -inf, max=5)  7 (min= 5, max=10)  12 20

越往left走越小，更新它的max

Discussion

Recursion在Tree题目的基本应用大致分为2类用法

1\. 把Value 从上往下传递（Then 从下往下）

1. 

2\. 

经典例题2: Print BST keys in the given range

Given two values k1 and k2 (where k1 \< k2) and a root pointer to a Binary Search Tree. Printt all the keys of tree in range k1 to k2\. i.e. print all x st k1\<=x\<=k2 and x is a key of given BST. Print all the keys in an increasing order. 

BST: 越往左走越小，越往右走越大

Add to List

### 98\. Validate Binary Search