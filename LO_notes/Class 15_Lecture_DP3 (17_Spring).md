Q0 (most popular DP question) Largest sum of a subarray

Base case: 1 element, dp[0] = input[0]

induction rule:

dp[i] represents the largest sum of subarray including the i-th element from 0-th element

State transfer function:

dp[i] = dp[i - 1] + input[i] if (dp[i - 1] \> 0 )

 input[i] ,otherwise

```java
public int largeSum (int[] nums) {
  if (nums == null || nums.length == 0) return 0;
  if (nums.length == 1) return nums[0];
  int n = nums.length;
  int start = 0;
  int sol_start = 0;
  int end = 0;
  int sol_end = 0;
  int maxSum = Integer.MIN_VALUE; // not 0!
  
  int[] dp = new int[n];
  dp[0] = nums[0];
  for (int i = 1; i < n; i++) {
    if (dp[i - 1] < 0) {
      dp[i] = nums[i];
      start = i;
    } else {
      dp[i] = dp[i- 1] + nums[i];
      // maxSum = Math.max(dp[i], maxSum); //
     } // 在外面检查, if nums[]都是负数,global max 如果再else里面 就永远更新不了了
     if (dp[i] > maxSum) {
       maxSum = dp[i];
       sol_start = start;
       sol_end = i;
      }
  }
  return maxSum;
}
```