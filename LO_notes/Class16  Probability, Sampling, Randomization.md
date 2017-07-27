Perfect Shuffle:

Given an array of integers (without any duplicates), shuffle the array such that all permutations are equally likely to be generated.

Assumptions

* The given array is not null

```java
public class Solution {
  public void shuffle(int[] array) {
    // Write your solution here.
    Random rand = new Random();
    for (int i = array.length - 1; i >= 0; i--) {
      int index = rand.nextInt(i + 1);
      swap(array, index, i);
    }
  }
  private void swap(int[] array, int one, int two) {
    int tmp = array[one];
    array[one] = array[two];
    array[two] = tmp;
  }
}

```

Reservoir Sampling:

Consider an unlimited flow of data elements. How do you sample one element from this flow, such that at any point during the processing of the flow, you can return a random element from the n elements read so far.

You will implement two methods for a sampling class:

* read(int value) - read one number from the flow
* sample() - return at any time the sample, if n values have been read, the probability of returning any one of the n values is 1/n, return null(Java)/INT\_MIN(C++) if there is no value read so far

You may need to add more fields for the class.

```java
public class Solution {
  Integer result;
  int count = 0;
  Random rand;
  public Solution() {
    result = null;
    rand = new Random();
  }
  
  public void read(int value) {
    count++;
    if (rand.nextInt(count) == 0) {
      result = value;
    }
  }
  
  public Integer sample() {
    return result;
  }
}

```

Random7 Using random5:

Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random7().

```java
public class Solution {
  public int random7() {
    // write your solution here
    // you can use RandomFive.random5() for generating
    // 0 - 4 with equal probability.
    int num = RandomFive.random5() * 5 + RandomFive.random5();
    while (num >= 21) {
      num = RandomFive.random5() * 5 + RandomFive.random5();
    }
    return num % 7;
  }
}

```

Random 1000 using random 5:

Given a random generator random5(), the return value of random5() is 0 - 4 with equal probability. Use random5() to implement random1000()

```java
public class Solution {
  public int random1000() {
    // Write your solution here.
    // you can use RandomFive.random5() for generating
    // 0 - 4 with equal probability.
    int num = RandomFive.random5() * 625 + RandomFive.random5() * 125 +
    RandomFive.random5() * 25 + RandomFive.random5() * 5 + RandomFive.random5();
    while (num >= 3000) {
      num = RandomFive.random5() * 625 + RandomFive.random5() * 125 +
    RandomFive.random5() * 25 + RandomFive.random5() * 5 + RandomFive.random5();
    }
    return num % 1000;
  }
}

```

Median Tracker:

Given an unlimited flow of numbers, keep track of the median of all elements seen so far.

You will have to implement the following two methods for the class

* read(int value) - read one value from the flow
* median() - return the median at any time, return null if there is no value read so far

Examples

* read(1), median is 1
* read(2), median is 1.5
* read(3), median is 2
* read(10), median is 2.5

```java
public class Solution {
  PriorityQueue<Integer> maxHeap;
  PriorityQueue<Integer> minHeap;
  public Solution() {
    // add new fields and complete the constructor if necessary.
    maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    minHeap = new PriorityQueue<>();
  }
  
  public void read(int value) {
    // write your implementation here.
    if (maxHeap.size() == 0) {
      maxHeap.offer(value);
      return;
    } else if (minHeap.size() == 0) {
      if (value < maxHeap.peek()) {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(value);
      } else {
        minHeap.offer(value);
      }
      return;
    }
    if (maxHeap.size() == minHeap.size()) {
      if (value <= minHeap.peek()) {
        maxHeap.offer(value);
      } else {
        maxHeap.offer(minHeap.poll());
        minHeap.offer(value);
      }
    } else {
      if (value <= maxHeap.peek()) {
        minHeap.offer(maxHeap.poll());
        maxHeap.offer(value);
      } else {
        minHeap.offer(value);
      }
    }
  }
  
  public Double median() {
    // write your implementation here.
    if (maxHeap.size() == minHeap.size()) {
      if (maxHeap.size() == 0) {
        return null;
      }
      return ((double) maxHeap.peek() + minHeap.peek()) / 2;
    }
    return (double) maxHeap.peek();
  }
}

```

95 percentile:

Given a list of integers representing the lengths of urls, find the 95 percentile of all lengths (95% of the urls have lengths \<= returned length).

Assumptions

* The maximum length of valid url is 4096
* The list is not null and is not empty and does not contain null

Examples

* [1, 2, 3, ..., 95, 96, 97, 98, 99, 100], 95 percentile of all lengths is 95.

```java
public class Solution {
  public int percentile95(List<Integer> lengths) {
    // Write your solution here.
    int[] array = new int[4097];
    for (Integer i : lengths) {
      array[i]++;
    }
    int count = 0;
    int sum = 0;
    int len = 4097;
    int percentile = (int) (0.05 * lengths.size()); 
    while (sum <= percentile) {
      sum += array[--len];
    }
    return len;
  }
}

```

