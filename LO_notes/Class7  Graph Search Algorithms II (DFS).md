All Subsets 1:

Given a set of characters represented by a String, return a list containing all subsets of the characters.

Assumptions

* There are no duplicate characters in the original set.

​Examples

* Set = "abc", all the subsets are [“”, “a”, “ab”, “abc”, “ac”, “b”, “bc”, “c”]
* Set = "", all the subsets are [""]
* Set = null, all the subsets are []

```java
// assumptions:
// there is no duplicate characters in the given string
public class SubSetsI {
    // method 1: DFS solution
    public List<String> subSets(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) return result;
        char[] arraySet = set.toCharArray();
        // record the current subset.
        StringBuilder sb = new StringBuilder();
        heaper(arraySet, sb, 0, result);
        return result;
    }
    // at each level, determine the character at the position "index" to be picked or not.
    // StringBuilder 
    private void helper(char[] set, StringBuilder sb, int index, List<String> result) {
        // terminate condition:
        // when we finishes determining for all the characters pick or not,
        // we have a complete subset.
        if (index == set.length) {
            result.add(sb.toString());
            return;
        }
        // 1. not pick the character at index 往下走不加元素进stringbuilder
        helper(set, sb, index + 1, result); 
        // 2. pick the character at index
        helper(set, sb.append(set[index]), index + 1, result);
        // remove the added character when back tracking to the previous level
        sb.deleteCharAt(sb.length() - 1);
    }
    
    // method 2: another DFS solution.
    public List<String> subSetsII(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) return result;
        char[] arraySet = set.toCharArray(); // convert String(set) to char array
        StringBuilder sb = new StringBuilder();
        helperII(arraySet, sb, 0, result);
        return result;
    }
    private void helperII(char[] set, StringBuilder sb, int index, List<String> result) {
        result.add(sb.toString());
        // maintains ascending order of the indices of picked characters.
        // choose the next picked index (the smallest one can be picked is index)
        for (int i = index; i < set.length; i++) {
            sb.append(set[i]);
            helper(set, sb, index + 1, result); // combination of the remaining char
            sb.deleteCharAt(sb.length() - 1);
        }
        
    }
}
```

```java
public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> list = new ArrayList<>();
    Arrays.sort(nums);
    backtrack(list, new ArrayList<>(), nums, 0);
    return list;
}

private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
    list.add(new ArrayList<>(tempList));
    for(int i = start; i < nums.length; i++){
        tempList.add(nums[i]);
        backtrack(list, tempList, nums, i + 1);
        tempList.remove(tempList.size() - 1);
    }
}
```

All Permutations I: (Characters)

Given a string with no duplicate characters, return a list with all permutations of the characters.

Examples

* Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
* Set = "", all permutations are [""]
* Set = null, all permutations are []

```java
public class PermutationsI {
    // 1. DFS solution with swapping
    public List<String> permutations(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) return result;
        char[] array = set.toCharArray();// string to char array
        helper(array, 0, result);
    }
    // choose the character to be at the position of index
    // all the already chosen positions are (0, index - 1)
    // all the candidate characters can be at position index
    // are in the subarray of (index, array.length - 1)
    private helper(char[] array, int index, List<String> result) {
        // terminate condition:
        // only when we have already chosen the characters for all the position,
        // we can have a complete permutation
        if (index == array.length) {
            result.add(new String(array));
            return;
        }
        // all the possible characters could be placed at index are
        // the characters in the subarray (index, array.length - 1)
        for (int i = index; i < array.length; i++) {
            swap(array, index, i); 
            helper(array, index + 1, result);//the remaining elements' permutation
            // remember to swap back when back track to previous level
            swap(array, index, i);// backtrack/ retrace to abc
        }
    }
    private void swap(char[] array, int left, int right) {
        char tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
    
    // 2. Solution to maintain the order of all the permutations.
    public List<String> permutationsWithOrder(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) return result;
        char[] array = set.toCharArray();
        Arrays.sort(array);
        // record which index has been used in the original array
        boolean[] used = new boolean[array.length];
        StringBuilder sb = new StringBuilder();
        helperWithOrder(array, used, sb, result);
        return result;
    }
    
    private void helperWithOrder(char[] array, boolean[] used, StringBuilder sb, List<String> result) {
        // terminate condition:
        // when the permutation contains all the characters in the original array.
        if (sb.length() == array.length) {
            result.add(cur.toString());
            return;
        }
        // when picking the next char, always according to the order.
        for (int i = 0; i < array.length; i++) {
            // if a character is already been used, we can not pick it
            // for the second time.
            if (!used[i]) {
                used[i] = true;
                sb.append(array[i]);
                helperWithOrder(array, used, sb, result);
                used[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
```

Leetcode 46: Permutations (Distinct Integers )

Given a collection of distinct numbers, return all possible permutations.

For example,
`[1,2,3]` have the following permutations:

    [
      [1,2,3],
      [1,3,2],
      [2,1,3],
      [2,3,1],
      [3,1,2],
      [3,2,1]
    ]

```java
// Leetcode 46
public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) return result;
        //helper(result, new ArrayList<>(), nums, 0);
        helper(result, nums, 0);
        return result;
    }
    
    private void helper(List<List<Integer>> result,int[] arrayInt, int start) {
        if (start == arrayInt.length) {
            List<Integer> sublist = new ArrayList<Integer>();
            // convert int[] array of integer to list of integer, create a new list of string for each time.
            for (int i : arrayInt) {
                sublist.add(i);
            }
            result.add(sublist); 
        } else {
            for (int i = start; i < arrayInt.length; i++) {
                swap(arrayInt, start, i);
                helper(result, arrayInt, start+1);
                swap(arrayInt, start, i);
            }
        }
    }
        private void swap(int[] arrayInt, int i, int j) {
            int temp = arrayInt[i];
            arrayInt[i] = arrayInt[j];
            arrayInt[j] = temp;
        }
}

```

Leetcode 47\. Permutation(Duplicate Integers)

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

For example,
`[1,1,2]` have the following unique permutations:

    [
      [1,1,2],
      [1,2,1],
      [2,1,1]
    ]

Leetcode 31\. Next Permutation

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
`1,2,3` → `1,3,2`
`3,2,1` → `1,2,3`
`1,1,5` → `1,5,1`

```

```

```java
public class Solution {
    public void nextPermutation(int[] nums) {
        int k = -1;
        int n = nums.length;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i+1]) {
                k = i;
                break;
            }
        }
        // if it's in descending order (decreasing), just reverse it to ascending order
        if (k == -1) {
            reverse(nums, 0, n-1);
            return;
        }
        
        // find the largest index l greater than k s.t. nums[k] < nums[l]
        int l = -1;
        for (int i = nums.length-1; i > k; i--) {
            if (nums[i] > nums[k]) {
                l = i;
                break;
            }
        }
        // 3. swap nums[k] and nums[l]
        swap(nums, k, l);
        // reverse the sequence from nums[k+1] to the last element nums[nums.size() - 1]
        reverse(nums, k+1, n-1);
    }
    
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int[] nums, int start, int end) {
        if (start > end) return;
        for (int i = start; i <= (start + end)/2; i++) {
            swap(nums, i, start + end - i);
        }
    }
}
```

Generate Parentheses

All Valid Permutations Of Parentheses I:

Given N pairs of parentheses “()”, return a list with all the valid permutations.

Assumptions

* N \>= 0

Examples

* N = 1, all valid permutations are ["()"]
* N = 3, all valid permutations are ["((()))", "(()())", "(())()", "()(())", "()()()"]
* N = 0, all valid permutations are [""]

```java
public class ValidParentheses(int k) {
    public List<String> validParentheses(int k) {
        List<String> result = new ArrayList<>();
        // the final string contains 2k characters.
        char[] cur = new char[k * 2];
        helper(cur, k, k, 0, result);
        return result;
    }
    // left : how many '(' we still have
    // right : how many ')' we still have
    // index: the current position in cur we want to fill in with
    private void helper(char[] cur, int left, int right, int index, List<String> result) {
        // terminate condition:
        // when we do not have any parentheses left.
        if (left == 0 && right == 0) {
            result.add(new String(cur));
            return;
        }
        if (left > 0) {
            cur[index] = '(';
            helper(cur, left - 1, right, index + 1, result);
            // do not need to de-append because we set character at index every time
        }
        if (right > left) {
            cur[index] = ')';
            helper(cur, left, right - 1, index + 1, result);
        }
    }
}
```

Combinations Of Coins:

Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the possible ways to pay a target number of cents.

Arguments

* coins - an array of positive integers representing the different denominations of coins, there are no duplicate numbers and the numbers are sorted by descending order, eg. {25, 10, 5, 2, 1}
* target - a non-negative integer representing the target number of cents, eg. 99

Assumptions

* coins is not null and is not empty, all the numbers in coins are positive
* target \>= 0
* You have infinite number of coins for each of the denominations, you can pick any number of the coins.

Return

* a list of ways of combinations of coins to sum up to be target.
* each way of combinations is represented by list of integer, the number at each index means the number of coins used for the denomination at corresponding index.

Examples

coins = {2, 1}, target = 4, the return should be

[

 [0, 4], (4 cents can be conducted by 0 \* 2 cents + 4 \* 1 cents)

 [1, 2], (4 cents can be conducted by 1 \* 2 cents + 2 \* 1 cents)

 [2, 0] (4 cents can be conducted by 2 \* 2 cents + 0 \* 1 cents)

]

```java
public class CombinationsOfCoins {
    public List<List<Integer>> Combinations(int target, int[] coins) {
        // each combination is represented as a List<Integer> cur,
        // and cur.get(i) = the number of coins of coins[i]
        // all the combinations are stored in the result as List of integer
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        helper(target, coins, 0, cur, result);
        return result;
    }
    
    private void helper(int target, int[] coins, int index, List<Integer> cur, List<List<Integer>> result) {
        // terminate condition:
        // we can stop at second last step, where we can use smallest coin to implement target to 0
        if (index == coins.length - 1) {
            if (target % coins[coins.length - 1] == 0) {
                cur.add(target / coins[coins.length - 1]);
                result.add(new ArrayList<Integer>(cur));
                cur.remove(cur.size() - 1);//
            }
            return;
        }
        // for coins[index], we can pick 0, 1, 2 ... target / coins[index] coins.
        int max = target / coins[index];
        for (int i = 0; i <= max; i++) {
            cur.add(i);
            // remember to modify the remaining cents for the next level.
            helper(target - i * coins[index], coins, index + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }
}
```

```java
// problem 39
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return ans;
        backtrack(ans, new ArrayList<Integer>(), 0, candidates, target, 0);
        return ans;
    }
    
    private void backtrack(List<List<Integer>> ans, ArrayList<Integer> list, int sum, int[] candidates, int target, int start) {
        if (sum == target) {
            ans.add(new ArrayList<Integer>(list));
            return;
        }
        
        if (sum > target) return; // backtrack when reach unvalid node
        
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            backtrack(ans, list, sum + candidates[i], candidates, target, i);
            list.remove(list.size() - 1);
        }
    }
}
```

