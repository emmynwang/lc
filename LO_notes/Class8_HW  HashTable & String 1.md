Top K Frequent Words:

Given a composition with different kinds of words, return a list of the top K most frequent words in the composition.

Assumptions

* the composition is not null and is not guaranteed to be sorted
* K \>= 1 and K could be larger than the number of distinct words in the composition, in this case, just return all the distinct words

Return

* a list of words ordered from most frequent one to least frequent one (the list could be of size K or smaller than K)

Examples

* Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 2 frequent words are [“b”, “c”]
* Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 4 frequent words are [“b”, “c”, "a", "d"]
* Composition = ["a", "a", "b", "b", "b", "b", "c", "c", "c", "d"], top 5 frequent words are [“b”, “c”, "a", "d"]

```java
public class TopKFrequent {
    // Assumptions: combo is not null, and k >= 1
    public String[] topKFrequent(String[] combo, k) {
        // handle the special case of empty combo at the very beginning
        if (combo.length == 0) return new String[0];
        // get all the distinct strings as keys and their frequencies as values 
        // NOTICE: the freq map has at least size 1
        Map<String, Integer> freqMap = getFreqMap(combo);
        // minHeap on the frequencies of the strings.
        // NOTICE: using Map.Entry as the element type directly so that all the
        // operations are mostly efficient.
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(k,
            new Comparator<Map.Entry<String, Integer>>(){
                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    // compare the frequencies, directly call the compareTo method since
                    // the frequencies are represented by Integer?????
                    return e1.getValue().compareTo(e2.getValue());
                }
            });
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        // Since the returned array requires the strings sorted by their
        // frequencies, use a separate helper method to do this operation
        return freqArray(minHeap);
    }
    
    private Map<String, Integer> getFreqMap(String[] combo) {
        Map<String, Integer> freqMap = new HashMap<>();
        // NOTICE: when possible, choose the most efficient way for hashmap operation
        for (String s : combo) {
            Integer freq = freqMap.get(s);
            if (freq == null) freqMap.put(s, 1);
            else freqMap.put(s, freq + 1);
        }
        return freqMap;
    }
    
    private String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minHeap) {
        String[] result = new String[minHeap.size()];
        for (int i = minHeap.size() - 1; i >= 0; i--) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }
}
```

347\. Top K Frequent Elements

Given a non-empty array of integers, return the *k* most frequent elements.

For example,
Given `[1,1,1,2,2,3]` and k = 2, return `[1,2]`.

Note: 

* You may assume *k* is always valid, 1 ≤ *k* ≤ number of unique elements.
* Your algorithm's time complexity must be better than O(*n* log *n*), where *n* is the array's size.

```java
/*
使用桶排序，在建立好数字和其出现次数的映射后，
我们按照其出现次数将数字放到对应的位置中去，这样我们从桶的后面向前面遍历，最先得到的就是出现次数最多的数字，我们找到k个后返回即可

用堆排序来做，使用一个最大堆来按照映射次数从大到小排列，使用priority_queue来实现，默认是最大堆
*/

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;  
        }
}



// use maxHeap. Put entry into maxHeap so we can always poll a number with largest frequency
class Solution2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }  
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
                         new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}



// use treeMap. Use frequency as the key so we can get all frequencies in order
class Solution3 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
        
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
           int freq = map.get(num);
           if(!freqMap.containsKey(freq)){
               freqMap.put(freq, new LinkedList<>());
           }
           freqMap.get(freq).add(num);
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
}
```

**Missing Number 1:**
---------------------

Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number.

Assumptions

* The given array is not null, and N \>= 1

Examples

* A = {2, 1, 4}, the missing number is 3
* A = {1, 2, 3}, the missing number is 4
* A = {}, the missing number is 1

```java
public class MissingNumberI {
    // Method 1: use HashSet.
    // Assumptions: array is not null.
    public int missingI(int[] array) {
        int n = array.length + 1;
        HashSet<Integer> set = new HashSet<>();
        for (int number : array) {
            set.add(number);
        }
        for (int i = 1; i < n; i++) {
            if (!set.contains(i)) return i;
        }
        return n;
    }
    
    // Method 2: use sum.
    public int missintII(int[] array) {
        int n = array.length + 1;
        long targetSum = (n + 0L) * (n + 1) / 2;
        long actualSum = 0L;
        for (int num : array) {
            actualSum += num;
        }
        return (int) (targetSum - actualSum);
    }
    
    // Method 3: bit operation
    public int missingIII(int[] array) {
        int n = array.length + 1;
        int xor = 0;
        // xor 1 to n
        for (int i = 1; i <= n; i++) xor ^= i;
        // after this operation, all the numbers from 1 to n
        // are pair xor'ed except for the missing number.
        // since x ^ x = 0, the remaining number is the result.
        for (int num : array) {
            xor ^= num;
        }
        return xor;
    }
    
    // Method 4: swap to the origianl position
    public int missingIV(int[] array) {
        // try to swap the numbers to its corresponding position.
        // for the number x, the corresponding position is x -1.
        for (int i = 0; i < array.length; i++) {
            // while array[i] is not i + 1, s3wap array[i] to its correct
            // position if possible
            while (array[i] != i + 1 && array[i] != array.length + 1) {
                swap(array, i, array[i] - 1);
            }
        }
        // if the missing number is in range of 1 - n - 1,
        // then we can find it by checking the index i where array[i] != i + 1
        for (int i = 0; i < array.length; i++) {
            if (array[i] != i + 1) {
                return i + 1;
            }
        }
        // if all the numbers of 1 - n - 1 are in position
        // the missing number is n
        return array.length + 1;
    }
}
```

**Common Numbers Of Two Sorted Arrays:**
========================================

Find all numbers that appear in both of two sorted arrays (the two arrays are all sorted in ascending order).

Assumptions

* In each of the two sorted arrays, there could be duplicate numbers.
* Both two arrays are not null.

Examples

* A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2]

```java
// Assumptions: there could be duplicated elements in the given arrays.
public class CommonNumbersII {
    // Method 1: two pointers/
    public List<Integer> commonI(int[] a, int[] b) {
        // Assumption: a, b is not null
        List<Integer> common = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[k]) {
                common.add(a[i]);
                i++;
                j++;
            } else if (a[i] < b[j]) {
                i++;
            } else {
                j++;
            }
        }
        return common;
    }
    
    // Method 2: use HashMap
    public List<Integer> commonII(int[] a, int[] b) {
        List<Integer> common = new ArrayList<>();
        HashMap<Integer, Integer> contA = new HashMap<>();
        for (int num : a) {
            Integer ct = countA.get(num);
            if (ct != null) countA.put(num, ct + 1);
            else countA.put(num, 1);
        }
        HashMap<Integer, Integer> contB = new HashMap<>();
        for (int num : b) {
            Integer ct = countB.get(num);
            if (ct != null) countB.put(num, ct + 1);
            else countB.put(num, 1);
        }
        for (Map.Entry<Integer, Integer> entry : countA.entrySet()) {
            Integer ctInB = countB.get(entry.getKey());
            if (ctInB != null) {
                int appear = Math.min(entry.getValue(), ctInB);
                for (int i = 0; i < appear; i++) common.add(entry.getKey());
            }
        }
        return common;
    }
}
```

**Remove Adjacent Repeated Characters I:**
==========================================

Remove adjacent, repeated characters in a given string, leaving only one character for each group of such characters.

Assumptions

* Try to do it in place.

Examples

* “aaaabbbc” is transferred to “abc”

Corner Cases

* If the given string is null, we do not need to do anything.

```java
public class RemoveDuplicateI {
    // try to convert the input to char[]
    // and do it in place
    public String deDup(String input) {
        if (input == null) return null;
        char[] array = input.toCharArray();
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            // repeated characters will be ignored except
            // for the first character in the sequence.
            if (i == 0 || array[i] != array[end - 1]) {
                array[end++] = array[i];
            }
        }
        return new String(array, 0, end);
    }
}
```

**Remove Adjacent Repeated Characters IV:**
===========================================

Repeatedly remove all adjacent, repeated characters in a given string from left to right.

No adjacent characters should be identified in the final string.

Examples

* "abbbaaccz" → "aaaccz" → "ccz" → "z"
* "aabccdc" → "bccdc" → "bdc"

```java
public class RemoveDuplicateIV {
    public String deDup(String input) {
        if (input == null || input.length() <= 1) return input;
        // try to convert the string to char[], and do it in-place
        char[] array = input.toCharArray();
        // instead of using an extra stack explicitly, we can actually
        // reuse the left side of the original char[] as the "stazck"
        // end: is where the top of the stack is.
        int end = 0;
        for (int i = 1; i < array.length; i++) {
            // if the stack is empty(when end == -1) or there is no duplicate chars,
            // we are able to push the character into the stack.
            if (end == -1 || array[i] != array[end]) {
                array[++end] = array[i];
            } else {
                // otherwise, we need pop the top element by end--,
                // and ignore all the consecutive duplicate chars.
                end--;
                while (i + 1 < array.length && array[i+1] == array[end+1]) i++;
            }
        }
    }
}
```

Determine If One String Is Another’s Substring:

Determine if a small string is a substring of another large string.

Return the index of the first occurrence of the small string in the large string.

Return -1 if the small string is not a substring of the large string.

Assumptions

* Both large and small are not null
* If small is empty string, return 0

Examples

* “ab” is a substring of “bcabc”, return 2
* “bcd” is not a substring of “bcabc”, return -1
* "" is substring of "abc", return 0

```java
// Notice:
// 1. There is no assumption about the charset used in the String,
//    so that we can not assume we are using 26 lower case characters.
// 2. This is the most correct version of RobinKarp in computer programming,
//    we need to handle 1. we could use arbitrary charset, 2. the overflow case,
//    by taking the module operation on a very large number.
// 3. You probably do not need to write this kind of solution to handle above two cases,
//    if you are in an interview. But it is still necessary to 
//    understand the reason behind it.
public class Strstr {
    // Method 1: Naive solution
    public int strstrI(String large, String small) {
        if (large.length() < small.length()) {
            return -1;
        }
        // return 0 if small is empty
        if (small.length() == 0) return 0;
        for (int i = 0; i <= large.length() - small.length(); i++) {
            if (equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }
    public boolean equals(String large, int start, String small) {
        for (int i = 0; i < small.length(); i++) {
            if (large.charAt(i + start) != small.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    // Method 2: RobinKarp
    public int strstrII(String large, String small) {
        if (large.length() < small.length()) return -1;
        if (small.length() == 0) return 0;
        // we need a large prime as module end
        int largePrime = 101;
        // we also need a small prime to calculate the hash value
        // since the charset would be very large, e.g. 1,112,064 for using UTF,
        // we can not use that number
        int prime = 31;
        int seed = 1;
        // hash value is calculated using the smallPrime and taken the module
        // operation on largePrime.
        // hash = (s0*smallP^k + s1*smallP^(k-1) + ... + sk*smallP^0) % largeP
        int targetHash = small.charAt(0) % largePrime;
        for (int i = 1; i < small.length(); i++) {
            hash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
        }
        int hash = 0;
        for (int i = 0; i < smal.length(); i++) {
            hash = moduleHash(hash, large.charAt(i), prime, largePrime);
        }
        if (hash == targetHash && equals(large, 0, small)) return 0;
        for (int i = 1; i <= large.lenth() - small.length(); i++) {
            // we need to make sure the module number is non-negative.
            hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
            hash = moduleHash(hash, large.charAt(i + small.length() - 1), prime, largePrime);
            // Notice: if the hash matches, it does not mean we really find a substring to match
            // because there is collision, we need to check if the substring really matches the small string
            // on average, this will not increase the time complexity, the probability
            // of collision if O(1) so we still have O(N + M)
            if (hash == targetHash && equals(large, i, small)) {
                return i;
            }
        }
        return -1;
    }
    public int moduleHash(int hash, int addition, int prime, int largePrime) {
        return (hash * prime % largePrime + addition) % largePrime;
    }
    public int nonNegative(int hash, int largePrime) {
        if (hash < 0) {
            hash += largePrime;
        }
        return hash;
    }
}
```

Remove Spaces:

Given a string, remove all leading/trailing/duplicated empty spaces.

Assumptions:

* The given string is not null.

Examples:

* “ a” --\> “a”
* “ I love MTV ” --\> “I love MTV”

```java
public class RemoveSpaces {
    // Assumption: input is not null.
    public String removeSpaces(String input) {
        if (input.isEmpty()) return input;
        char[] array = input.toCharArray();
        int end = 0;
        for (int i = 0; i < array.length; i++) {
            // when we would ignore the current space character:
            // 1. we ignore all the space characters followed by
            // another space character, so that if there are several
            // consecutive space characters, only the first one will
            // be remained.
            // 2. we ignore also the space character if it is the 
            // first character of the input string.
            if (array[i] == ' ' && (i == 0 || array[i - 1] == ' ')) continue;
            array[end++] = array[i];
        }
        // Post-processing: it is possible we still have one trailing space
        if (end > 0 && array[end - 1] == ' ') return new String(array, 0, end - 1);
        return new String(array, 0, end);
    }
}
```

Remove Certain Characters:

Remove given characters in input string, the relative order of other characters should be remained. Return the new string after deletion.

Assumptions

* The given input string is not null.
* The characters to be removed is given by another string, it is guranteed to be not null.

Examples

* input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".

```java
public class RemoveCertainCharacters {
    // Assumption: input and t are not null.
    public String remove(String input, String t) {
        // we need to know how to solve this problem with inplace way.
        // usually we can convert the immutable string to char[]
        char[] array = input.toCharArray();
        // get set of all distinct characters in t so that lookup
        // operation will be efficient
        Set<Character> set = buildSet(t);
        // slow = [0, slow) contains the valid result.
        // fast: [fast, array.length) is the area to explore.
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            // the exploring character can only be put into valid area
            // if it is not in the set.
            if (!set.contains(array[fast])) {
                array[slow++] = array[fast];
            }
        }
        // convert the char[] subarray back to string object
        // refer to java API doc for applicable constructors
        return new String(array, 0, slow);
    }
    
    private Set<Character> buildSet(String t) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            set.add(t.charAt(i));
        }
        return set;
    }
}
```