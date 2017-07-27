**Priority Queue**

* it’t a heap with the same Queue interface with **offer(), peek(), poll() // Heap的实现**
  * But it’s **NOT** FIFO, when poll() or peek() we always look at the smallest / largest element (min heap/ max heap)
* The PriorityQueue will arrange the elements based on the order of the elements (who is smaller/ larger by comparing any two of them) and it is optimized for problems about “who is the smallest/ largest element”.
* Internally it’s implemented using an array.
  * **offer**(E e) - **insert** one element into the heap
  * **peek**() - **peek** the top element in the heap (smallest/ largest based on how to order the elements)
  * **poll**() - **remove** the top element from the heap, and return it (smallest/ largest based on how to order the elemnt)
  * size()
  * isEmpty()
* Time Complexity:
  * offer(E e) - O(logn)
  * peek() - O(1)
  * poll() - O(logn)
  * remove() - O(logn), Queue, From AbstractQueue, remove(Object) - O(n) 给定一个元素删掉 查找到再删
  * size() - O(1)
  * isEmpty - O(1)
  * Note: Heap 只保证smallest / largest,
* **Order** - The PQ need to know how to compare the elements and determine which one is smaller/ larger.
  * There are two ways to do it:
    * The element type implementing Comparable interface () // 实现
      * 优先级越高越先出来，（Integer 已经 class Integer implements Comparable\<Integer\>

```java
Example: 
PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();// 与变量命名无关
minHeap.offer(4);
minHeap.offer(5);
minHeap.offer(2);
minHeap.offer(3);
minHeap.offer(1);
while (! minHeap.isEmpty()){ 
  System.out.println (minHeap.poll())
  
};
// 1
// 2
// 3
// 4
// 5
// 为什么是minHeap，优先级越高越先出来（优先级高index小）Integer已经悄无声息的
```

How does the priorityQueue know how to compare the interger objects?

* The element’s class can implement Comparable interface and thus implement the required method compareTo(), PriorityQueue will use this method to compare any two elements.

```java
interface Comparable<E> {
  int compareTo(E ele); // 只有一个method
}
// part of the integer class implementation...
class integer implements Comparable<Integer>{ //instance
  private int value;
  
  public Integer(int value){
    this.value = value;
  }
  @Override //这里正在override 覆盖的是int compareTo(E ele)??
  public int compareTo(Integer another) {
    if(this.value == another.value){
      return 0;
    }
    return this.value < another.value ? -1 : 1; // -1和1交换就是maxHeap
  }
}
/*
The return value of compareTo(another) mathod determines the order of this and another:
0 - this and another are of the same priority
-1 - this has higher priority than another
1 - this has less priority than another
序号越小，优先级越高（）

*/
```

* Comparable这个interface要实现就必须要override CompareTo这个method。实现一个接口必须重写他的method
* 抽象的method，一定要override
* 没有override 后面的code，不去实现compareTo, 实现一个接口必须重写他的method
* Comparable是interface
* 可以implement多个interface，只能extends一个类

Another Example using custom class:

Suppose you have an integer matrix, each row is sorted by ascending order and each column is also sorted by ascending order, we need a class to representing each cell in the matrix, and we need to compare two cells with their value in the matrix

```java
class Cell{
  public int row;
  public int col;
  public int value;
  public Cell(int row, int col, int value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }
  // we want to have a minHeap by the value of the Cell elements
  PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>();
  Cell c1 = new Cell(0, 0, 0);
  Cell c2 = new Cell(0, 1, 2);
  minHeap.offer(c1);
  minHeap.offer(c2);
  
  class Cell implements Comparable<Cell>{
  public int row;
  public int col;
  public int value;
  public Cell(int row, int col, int value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }
  @Override
  public int compareTo(Cell another) {
    // 只比较value，哪个小返回哪个
    if (this.value == another.value){ 
      return 0;
    }
    return this.value < another.value? -1: 1;
  }
  // 把cell这个类型放到compareTo，matrix格子实现
  // To compare two Cell objects, use the compareTo() method.
  /*
  Cell c1 = new Cell(0, 0, 0);
  Cell c2 = new Cell(0, 1, 2);
  int result = c1.compareTo(c2);
  return -1;
  c1 priority higher than c2
  */
}
```

* provoide an extra Comparator object to compare the elements // 单独创建一个类implement Comparator\<Cell\>
  * There is another interface Comparator, it is used to compare two elements with same type E

```java
Interface Comparator<E> {
  int compare(E o1, E o2);
}

class Cell{
  public int row;
  public int col;
  public int value;
  public Cell(int row, int col, int value) {
    this.row = row;
    this.col = col;
    this.value = value;
  }
  // comparator 附加属性
class MyComparator implements Comparator<Cell> {
  @Override
  public int compare(Cell c1, Cell c2) {
    if (c1.value == c2.value) {
      return 0;
    }
    return c1.value < c2.value ? -1 : 1;
  }
}

/*
The return value of compare(o1, o2) method determines the order of o1 and o2:
0  -  o1 and o2 are of the same priority
-1 -  o1 has higher priority than o2;
1  -  oq has less priority than o2;
*/

// we want to have a minHeap by the value of the Cell elements.
PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(11, new MyComparator());
Cell c1 = new Cell(0, 0, 0);
Cell c2 = new Cell(0, 1, 2);
minHeap.offer(c1);
minHeap.offer(c2);
```

* Comparable在一个类自带的属性，object中实现只能定义一个 natural order，
* Comparator 可以提供一个额外的， 如果comparable不够使,一个类可以加好多好多个comparator

There is a utility method Collections.reverseOrder(), it will return a comparator that reverses the natural order.

PriorityQueue\<Integer\> maxHeap = new PriorityQueue\<Integer\> (16, Collections.reverseOrder());

equals() method of a comparator: if the incoming Comparator equals the current comparator, not about orders!

Interface : 

**Conclusion:**
===============

To make PriorityQueue work, you have to:

Either - The element class E implements Comparable interface

Or - Provide a Comparator\<E\> class to compare elements, and pass an comparator object to the PriorityQueue

Most Frequently used **constructors** of PriorityQueue

1. **PriorityQueue\<Cell\> heap = new PriorityQueue\<Cell\>(); **// 一个parameter都不传
  1. initialize the internal array with default capacity(11)
  2. class Cell must implements Comparable\<Cell\>!
2. **PriorityQueue\<Cell\> heap = new PriorityQueue\<Cell\>(16);**
  1. initialize the internal array with specified capacity(16);
  2. class Cell implements Comparable\<Cell\>!
3. **PriorityQueue\<Cell\> heap = new PriorityQueue\<Cell\>(16, new MyComparator()); // 全手动模式，自己控制顺序**
  1. initialize the internal array with specified capacity(16);
  2. class MyComparator implements Comparator\<Cell\>!

Be careful - **the initial capacity has to be \> 0;**

// if k \<= 0, illegalArgumentException will be thrown in the constructor.

PriorityQueue\<Cell\> minHeap = new PriorityQueue\<Cell\> (k, new MyComparator());

**Method 1 and 2, the object inside the PQ should be comparable!**

Otherwise, Java will throw runtime exception (it will cast anything inside into a Comparable to do the comparison)

**
**

**heapify() - convert an array to heap in O(n) time**

 5\. ArrayList\<Cell\> list = new ArrayList\<Cell\>();

 …

 PriorityQueue\<Cell\> heap = new PriorityQueue\<Cell\>(list);

  1. initialize the internal array with heapify() internally
  2. class Cell implements Comparable\<Cell\>!

* it’s not exposed to outside from PQ class, it is a private method.
* **the only way you can utilize the heapify() with O(n), is to use the constructor**
  * **PriorityQueue(Collection\<? extends E\> c) // 只有通过这一个constructor构建的**
  *

```java
// part of the PriorityQueue implemantation...
class PriorityQueue<E> {
  private E[] heapArray;
  private int size;
  
  public PriorityQueue(Collection<? extends E> c)
    heapArray = c.toArray();
    size = heapArray.length;
    heapify(); // the only place heapify is called
    
}
  private void heapify(){
    // heapify the heapArray;
    for (int i= size/2 -1; i>=0; i--){
      percolateDown(i);
    }
  }
  
}
```

Nested Class: define a class within a class

Static nested class vs. non-static nested class: belong to clss, or belong to instance. 

Anonymous class: nested class with no name. Often replaceable by lambda expressions in Java 8

lambda expressions.

Smallest k elements in unsorted array

Find the K smallest numbers in an unsorted integer array A. The returned numbers ascending order.

Assumptions

* A is not null
* K is \>= 0 and smaller than or equal to size of A

Return

* an array with size K containing the K smallest numbers

```java
public class KSmallest {
  
    public static int[] smallestKElementsKHeap(int[] array, int k) {
      int[] res = new int[k];
      if (k == 0) {
        return res;
      }
      
      private static class MyComparator implements Comparator<Integer>{
        @Override 
        public int compare(Integer i1, Integer i2){
          if (i1.equals(i2)) return 0;
          return i1>i2? -1: 1;
        }
      }
      // assumption k>=0;
      PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder();
      // maxHeap size is k
      for (int i=0; i<k; i++) { 
        maxHeap.offer(array[i]);
      }
      for (i > k) {
        res[i] = pq.poll()
      }
      
    }
} 
```

```java
public static int[] smallestKElementNHeap(int[] array, int k){
  Queue<Integer> minHeap = new PriorityQueue<Integer>();
  for (int i = 0; i < array.length; i++) {
    minHeap.offer(array[i]);
  }
  int[] result = new int[k];
  for (int i = 0; i < k; i++) {
    result[i] = minHeap.poll();
  }
  return result;
}
```



```java
public 
```