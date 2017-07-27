What is recursion? 

Review: What is recursion (agian)?

需要掌握的知识点：

1. 表象上：function calls itself
2. 实质上：Boil down a big problem to smaller problems
3. Implementation上：
  1. 1.** Base Case **(停下来)：smallest problem to solve
  2. 2.** Recursive Rule**: How to make the problem smaller (if we can resolve the same problem with a smaller size, then what’s left to do for the current problem size n)

Problem 1\. Recursion 与计算的结合

Q1.1 pow(x, n)

Math Problems: 

1. 0 as the denominator
2. 1/2 as an integer?? or float
3. 0 ^ 0

```java
public double power(double a, double b) {
  if (a == 0 && b == 0) {
    return 0;
  } else if (b < 0) {
    return 1 / (double) helper(a, b);
  } else 
  
}
public int helper(int a, int b) {
  if (b == 0) return 1;
  int half_res = helper(a, b / 2);
  if (b % 2 == 0) {
    return half_res * half_res;
  } else {
    return a * half_res * half_res;
  }
}
```

2\. Recursion 与 1D or 2D Array 结合

1. . 1D Array: 二分法比较常见
  1. 1.1 MergeSort
  2. 1.2 QuickSort
2. 2D Array

Q.2.1 2D Array

 逐层(row by row) recursive: 8 queen — \> n queen

Recursive rule:

For the i-th queen on the i-th row, we must make sure the Qi doesn’t conflict with all previous queens that have been placed on the board.

 col 

int Position[N] = { 1 3 4d 5 1 ….}

if (