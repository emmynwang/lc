import java.util.Arrays;

public class MaxSum {

	public int[] maxSumArray(int[] nums) {
		int start = 0;
		int sol_start = 0;
		int sol_end = 0;
		// int maxSum = Integer.MIN_VALUE;
		int maxSum = nums[0];
		int n = nums.length;
		int[] dp = new int[n + 1]; // 
		dp[0] = nums[0];
		int[] result = new int[3];

		for (int i = 1; i < n; i++) {
			if (dp[i - 1] < 0) {
				dp[i] = nums[i];
				start = i;
			}
			else {
				dp[i] = dp[i - 1] + nums[i];
			}
			if (maxSum < dp[i]) { // need to update maxSum
				maxSum = dp[i];
				sol_start = start;
				sol_end = i;
			}
		}
		result[0] = sol_start;
		result[1] = sol_end;
		result[2] = maxSum;
		// return sol_end - sol_start + 1;
		return result;

	}
	public static void main(String[] args) {
		MaxSum solution = new MaxSum(); // 建立一个solution object
		int[] nums1 = {-1, -2, -4, -6, -2, -3};
		int[] nums2 = {1, 3, 4, -1, -20, 1};
		System.out.println(Arrays.toString(solution.maxSumArray(nums1))); // 直接print array是地址，
		System.out.println(Arrays.toString(solution.maxSumArray(nums2)));
	}

	
}