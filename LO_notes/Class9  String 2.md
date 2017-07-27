All PermutationsII:

Given a string with possible duplicate characters, return a list with all permutations of the characters.

Examples

* Set = “abc”, all permutations are [“abc”, “acb”, “bac”, “bca”, “cab”, “cba”]
* Set = "aba", all permutations are ["aab", "aba", "baa"]
* Set = "", all permutations are [""]
* Set = null, all permutations are []

```java
public class PermutationsII {
    public List<String> permutations(String set) {
        List<String> result = new ArrayList<>();
        if (set == null) return result;
        char[] array = set.toCharArray();
        helper(array, 0, result);
        return result;
    }
    // index: at the level of index, we are to determine for the current
    // permutation, which element is positioned at the index
    private void helper(char[] array, int index, List<String> result) {
        if (index == array.length) {
            // base case: when we have determined for all the indices of
            // the current permutation which element to choose
            result.add(new String(array));
            return;
        }
    }
    // Notice: the rule is just for the current level, if a certain element
    // is picked, we can not pick any of its duplicates.
    // so that we use a set to record all the distinct elements.
    Set<Character> used = new HashSet<>();
    for (int i = index; i < array.length; i++) {
        // user.add(array[i]) will return  false if the value of array[i]
        // is already in the Set
        if (used.add(array[i]) {
            swap(array, i, index);
            // go for the next level, index + 1
            helper(array, index + 1, result);
            // don't forget to do the clear operation when backtracking
            swap(array, i, index);
        }
    }
}
```

Reverse Words In A Sentence I:

Reverse the words in a sentence.

Assumptions

* Words are separated by single space
* There are no heading or tailing white spaces

Examples

* “I love Google” → “Google love I”

Corner Cases

* If the given string is null, we do not need to do anything.

```java
public class ReverseWords {
    // Assumptions:
    // 1. the words are separated by one space character.
    // 2. There are no leading or trailing spaces.
    // 3. input is not null
    public String reverseWord(String input) {
        // try to convert it to char array and solve inplace
        char[] array = input.toCharArray();
        // 1. reverse the whole char array
        reverse(array, 0, array.length - 1);
        // 2. reverse each of the words.
        for (int i = 0; i < array.length; i++) {
            // the start index of a word.
            if (array[i] != ' ' && (i == 0 || array[i - 1] == ' ')) {
                start = i;
            }
            // the end index of a word
            if (array[i] != ' ' && (i == array.length - 1 || array[i + 1] == ' ')) {
                reverse(array, start, i);
            }
        }
        return new String(array);
    }
}
```

String Replace:

Given an original string input, and two strings S and T, replace all occurrences of S in input with T.

Assumptions

* input, S and T are not null, S is not empty string

Examples

* input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
* input = "dodododo", S = "dod", T = "a", input becomes "aoao"

```java
public class StringReplace {
    // Method 1: Not using any String/StringBuilder utility
    // using char[] to do it in place
    public String replaceI(String input, String s, String t) {
        // Assumptions: input, s, t are not null, s is not empty
        char[] array = input.toCharArray();
        if (s.length() >= t.length()) return replaceShorter(array, s, t);
        return replaceLonger(array, s, t);
    }
    
    public String replaceShorter(char[] input, String s, String t) {
        // We reuse the input char array since the number of characters needed is less.
        // fast and slow pointers both from left to right direction
        int slow = 0;
        int fast = 0;
        while (fast < input.length) {
            // when we find a match of s on the substring starting from the fast pointer
            // we copy the t at slow pointer.
            if (fast <= input.length - s.length() && equalSubstring(input, fast, s)) {
                copySubstring(input, slow, t);
                slow += t.length();
                fast += s.length();
            } else {
                input[slow++] = input[fast++];
            }
        }
        return new String(input, 0, slow);
    }
    public String replaceLonger(char[] input, String s, String t) {
        // Notice: we will need a longer array in the case
        // In this solution, we actually allocate a larger array on demand, and the 
        // purpose of the solution is to demonstrate how to do it "in place"
        // get all the matches end positions in the input char array of string s
        ArrayList<Integer> matches = getAllMatches(input, s);
        // calculate the new length needed
        char[] result = new char[input.length + matches.size() * (t.length() - s.length())];
        // fast and slow pointers both from right to left direction.
        // fast: the position when traversing the new length
        // slow: the position when traversing the old length
        // lastIndex: the rightMost matching end position's index
        int lastIndex = matches.size() - 1;
        int slow = input.length - 1;
        int fast = result.length - 1;
        while (slow >= 0) {
            // only if we still have some match and slow is in the position of 
            // rightmost matching end position, we should copy t
            if (lastIndex >= 0 && slow == mztches.get(lastIndex)) {
                copySubstring(result, fast - t.length() + 1, t);
                fast -= t.length();
                slow -= s.length();
                lastIndex--;
            } else {
                result[fast--] = input[slow--];
            }
        }
        return new String(result);
    }
    // check if the substring from fromIndex is the same as s.
    private boolean equalSubstring(char[] input, int fromIndex, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (input[fromIndex + i] != s.charAt(i)) return false;
        }
        return true;
    }
    
    // copy the string t to result at fromIndex
    private void copySubstring(char[] result, int fromIndex, String t) {
        for (int i = 0; i < t.length(); i++) {
            result[fromIndex + i] = t.charAt(i);
        }
    }

    // get all the matches of s end position in input
    private ArrayList<Integer> getAllMatches(char[] input, String s) {
        ArrayList<Integer> matches = new ArrayList<>();
        int i = 0;
        while (i <= input.length - s.length()) {
            if (equalSubstring(input, i, s)) {
                // we record the match substring's end index instead of start index,
                // for later convenience.
                matches.add(i + s.length() - 1);
                i += s.length();
            } else {
                i++;
            }
        }
        return matches;
    }
    
    // Method 2: Using Java's StringBuilder utility and String's indexof(),
    // not using String's replace()
    public String replaceII(String input, String s, String t) {
        // Assumptions: input, s, t are not null, s is not empty
        StringBuilder sb = new StringBuilder();
        // we check if there exists a substring same as s
        // in the substring of input starting at fromIndex.
        int fromIndex = 0;
        int matchIndex = input.indexOf(s, fromIndex);
        while (matchIndex != -1) {
            sb.append(input, fromIndex, matchIndex).append(t);
            // Next time we need to start from matchIndex + s.length() 
            // to find if we have later matches.
            fromIndex = matchIndex + s.length();
            matchIndex = input.indexOf(s, fromIndex);
        }
        sb.append(input, fromIndex, input.length());
        return sb.toString();
    }
}
```

Decompress String II:

Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.

Assumptions

* The string is not null
* The characters used in the original string are guaranteed to be ‘a’ - ‘z’
* There are no adjacent repeated characters with length \> 9

Examples

* “a1c0b2c4” → “abbcccc”

```java
public class DecompressStringII {
    // Method 1: "in place"
    public String decompressI(String input) {
        if (input.isEmpty()) return input;
        char[] array = input.toCharArray();
        // we need to handle the
        // "a0", "a1", "a2" case
        // "a3", "a4", ... case seperately
        return decodeLong(array, decodeShort(array));
    }
    
    // return the length of the decoded string,
    // only cares about shorter decoded string
    private int decodeShort(char[] input) {
        // since the decoded string is shorter, we should
        // do the decoding work from left to right direction
        int end = 0;
        for (int i = 0; i < input.length; i += 2) {
            int digit = getDigit(input[i + 1]);
            if (digit >= 0 && digit <= 2) {
                for (int j = 0; j < digit; j++) input[end++] = input[i];
            }
        } else {
            // we don't care the longer decoded string
            input[end++] = input[i];
            input[end++] = input[i + 1];
        }
        return end;
    }
    
    // take care of longer decoding string
    private String decodeLong(char[] input, int length) {
        // we need to calculate the new required length
        int newLength = length;
        for (int i = 0; i < length; i++) {
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                newLength += digit - 2;
            }
        }
        // Notice: if it is required to do this in place, usually the input
        // array is a sufficient large one, you will not need to 
        // allocate a new array. this solution is only for demonstration.
        char[] result = new char[newLength];
        int end = newLength - 1;
        for (int i = length - 1; i >= 0; i--) {
            int digit = getDigit(input[i]);
            if (digit > 2 && digit <= 9) {
                i--;
                for (int j = 0; j < digit; j++) result[end--] = input[i];
            } else {
                // we already took care the shorter cases
                result[end--] = input[i];
            }
        }
        return new String(result);
    }
    
    private int getDigit(char digit) {
        return digit - '0';
    }
    
    
    // Method 2: using StringBuilder to help
    public String decompressII(String input) {
        // Assumptions: input is not null
        char[] array = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            char ch = array[i++];
            int count = array[i] - '0';
            for (int c = 0; c < count; c++) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
```

Reorder Array:

Given an array of elements, reorder it as follow:

* { N1, N2, N3, …, N2k } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k }
* { N1, N2, N3, …, N2k+1 } → { N1, Nk+1, N2, Nk+2, N3, Nk+3, … , Nk, N2k, N2k+1 }

Try to do it in place.

Assumptions

* The given array is not null

Examples

* { 1, 2, 3, 4, 5, 6} → { 1, 4, 2, 5, 3, 6 }
* { 1, 2, 3, 4, 5, 6, 7, 8 } → { 1, 5, 2, 6, 3, 7, 4, 8 }
* { 1, 2, 3, 4, 5, 6, 7 } → { 1, 4, 2, 5, 3, 6, 7 }

```java
public class ReorderArray {
        public int[] reorder(int[] array) {
            if (array.length % 2 == 0) reorder(array, 0, array.length - 1);
            else reorder(array, 0, array.length - 2);
            return array;
        }
        private void reorder(int[] array, int left, int right) {
            int length = right - left + 1;
            if (length <= 2) return;
            int mid = left + length / 2;
            // make sure trunk1 and trunk 3 are in same size
            int leftMid = left + length / 4;
            int rightMid = mid + length / 4;
            reverse(array, leftMid, rightMid - 1);
            reverse(array, leftMid, leftMid + length / 4 - 1);
            reverse(array, leftMid + length / 4, rightMid - 1);
            reorder(array, left, leftMid + length / 4 - 1);
            reorder(array, leftMid + length / 4, right);
        }
        private void reverse(int[] array, int left, int right) {
            while (right > left) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }
    }
```

Longest Substring Without Repeating Characters:

Given a string, find the longest substring without any repeating characters and return the length of it. The input string is guaranteed to be not null.

For example, the longest substring without repeating letters for "bcdfbd" is "bcdf", we should return 4 in this case.

```java
// use a sliding window, maintain a HashMap data structure
public class Solution {
  public int longest(String input) {
    char[] array = input.toCharArray();
    int slow = 0;
    int ret = 0;
    Set<Character> set = new HashSet<>();
    for (int fast = 0; fast < array.length; fast++) {
      if (!set.contains(array[fast])) {
        set.add(array[fast]);
        int length = fast - slow + 1;
        if (length > ret) ret = length;
      } else {
        while (set.contains(array[fast])) {
          set.remove(array[slow++]);
        }
        set.add(array[fast]);
      }
    }
    return ret;
  }
}

```

Reverse String:

Reverse a given string.

Assumptions

* The given string is not null.

```java
public class Solution {
  public String reverse(String input) {
    char[] array = input.toCharArray();
    int i = 0;
    int j = array.length - 1;
    while (i < j) {
      char tmp = array[i];
      array[i] = array[j];
      array[j] = tmp;
      i++;
      j--;
    }
    return new String(array);
  }
}
```

Right Shift By N Characters:

Right shift a given string by n characters.

Assumptions

* The given string is not null.
* n \>= 0.

```java
// right shift n means swap right most n elements with others on left
public class Solution {
  public String rightShift(String input, int n) {
    if (input.length() <= 1) return input;
    char[] array = input.toCharArray();
    int mid = array.length - n % array.length;
    reverse(array, 0, mid - 1);
    reverse(array, mid, array.length - 1);
    reverse(array, 0, array.length - 1);
    return new String(array);
  }
  private void reverse(char[] array, int left, int right) {
    while (left < right) {
      char tmp = array[left];
      array[left] = array[right];
      array[right] = tmp;
      left++;
      right--;
    }
  }
}
```

All Anagrams:

Find all occurrence of anagrams of a given string s in a given string l. Return the list of starting indices.

Assumptions

* s is not null or empty.
* l is not null.

Examples

* l = "abcbac", s = "ab", return [0, 3] since the substring with length 2 starting from index 0/3 are all anagrams of "ab" ("ab", "ba").

```java
// use a fixed size sliding window
// use a hashMap<char, int> pair to store freq of those chars
public class Solution {
  List<Integer> allAnagrams(String s, String l) {
    List<Integer> ret = new ArrayList<>();
    if (l.length() < s.length()) return ret;
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      Integer freq = map.get(s.charAt(i));
      if (map.containsKey(s.charAt(i))) {
        map.put(s.charAt(i), freq + 1);
      } else {
        map.put(s.charAt(i), 1);
      }
    }
    int toMatch = map.size();
    for (int fast = 0; fast < l.length(); fast++) {
      char cur = l.charAt(fast);
      Integer freq = map.get(cur);
      if (freq != null) {
        map.put(cur, freq - 1);
        if (freq == 1) toMatch--;
      }
      if (fast >= s.length()) {
        char tmp = l.charAt(fast - s.length());
        freq = map.get(tmp);
        if (freq != null) {
          map.put(tmp, freq + 1);
          if (freq == 0) toMatch++;
        }
      }
      if (toMatch == 0) ret.add(fast - s.length() + 1);
    }
    return ret;
  }
}
```