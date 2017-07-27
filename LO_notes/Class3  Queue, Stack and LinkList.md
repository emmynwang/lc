1\. Queue 

1.1 Example: wait in a line, FIFO == First in first out

1.2 Usages: Breadth-First Search related problems

1.3 Classical Problems:

1. Tree printout by level
2. Sliding window problems (Deque: double ends manipulation)

poll() — take the element from head;

peek() — look at the element at the head; 

offer() — insert the element at the tail; // difference with add() — 

2\. Stack

2.1 LIFO (Last in First out) like a box;

All operations available: push(), pop(), top() — look at the element at the top

Implementation: popular data structure: array or vector, linked list

3 \<— top of the stack

2

1 \<— bottom of the stack

int MyArray[10] = { 1 2 3 4 5 } size, capacity

 -1 0, 1, 2, 3, 4

Four popular interview questions:

Question1 : How could we implement a queue by using two stacks?

Solution:

* Stack 1 : responsible for offer() —\> stack1.push() 加
* Stack 2 : responsible for poll() —\> stack2.pop()

poll () — stack 2.pop()

case 1 : if stack 2 is not empty, stack2.pop()

case 2 : if stack2 is empty（stack2没有元素了） —\> pop elements from stack 1 and push them into stack 2

offer() - stack1.offer()

Time Complexity: 

offer() - O(1)

HW

1. Queue By Two Stacks:

Java: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(), poll() and peek() operations. When the queue is empty, poll() and peek() should return null.

C++: Implement a queue by using two stacks. The queue should provide size(), isEmpty(), push(), front() and pop() operations. When the queue is empty, front() should return -1.

Assumptions

* The elements in the queue are all Integers.
* size() should return the number of elements buffered in the queue.
* isEmpty() should return true if there is no element buffered in the queue, false otherwise.

```java
public class QueueByTwoStack {
    // We always insert into the 'in' stack
    private LinkedList<Integer> in;
    // We always remove from the out stack
    private LinkedList<Integer> out;
    
    public QueueByTwoStack() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }
    
    public Integer poll() {
        // if out stack is empty
        // need to move the elements from in stack to out stack
        move();
        return out.isEmpty() ? null : out.pollFirst();
    }
    
    public void offer(int value) {
        // always push into the in stack
        in.offerFirst(value);
    }
    public Integer peek() {
        move();
        return out.isEmpty() ? null : out.peedFirst();
    }
    
    // when out stack is empty, move the elements from
    // in stack to out stack
    private void move() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.offerFirst(in.pullFirst());
            }
        }
    }
    public int size() {
        return in.size() + out.size();
    }
    public boolean isEmpty() {
        return in.size() == 0 && out.size() == 0;
    }
}
```

2\. Stack With min:

Enhance the stack implementation to support min() operation. min() should return the current minimum value in the stack. If the stack is empty, min() should return -1.

pop() - remove and return the top element, if the stack is empty, return -1

* push(int element) - push the element to top
* top() - return the top element without remove it, if the stack is empty, return -1
* min() - return the current min value in the stack.

```java
public class StackWithMin {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;
    
    public StackWithMin() {
        stack = new LinkedList<>();
        minStack = new LinkedList<>();
    }
    public Integer min() {
        if (minStack.isEmpty()) {
            return null;
        }
        return minStack.peekFirst();
    }
    public void push(int value) {
        stack.offerFirst(value);
        // when value <= current min value in stack
        // need to push the value to minStack
        if (minStack.isEmpty() || value <= minStack.peekFirst()) {
            minStack.offerFirst(value);
        }
    }
    public Integer pop() {
        if (stack.isEmpty()) {
            return null;
        }
        Integer result = stack.poolFirst();
        // when the popped value is the same as top value of minStack
        // the value need to be popped from minStack as well
        if (minStack.peekFirst().equals(result)) {
            minStack.pollFirst();
        }
        return result;
    }
    
    public Integer top() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peekFirst();
    }
}
```

Reverse Linked List:

Enhance the stack implementation to support min() operation. min() should return the current minimum value in the stack. If the stack is empty, min() should return -1.

pop() - remove and return the top element, if the stack is empty, return -1

* push(int element) - push the element to top
* top() - return the top element without remove it, if the stack is empty, return -1
* min() - return the current min value in the stack.

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
 
 // recursive method
public class ReverseLinkedList {
  public ListNode reverse(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode next = head.next;
    ListNode newHead = reverse(next);
    next.next = head;
    head.next = null;
    return newHead;
  }
  
  public ListNode reverse(ListNode head) {
      ListNode prev = null;
      while (head != null) {
          ListNode next = head.next;
          head.next = prev;
          prev = head;
          head = next;
      }
      return prev;
  }
}
```

Middle Node Of Linked List:

Find the middle node of a given linked list.

Examples

* L = null, return null
* L = 1 -\> null, return 1
* L = 1 -\> 2 -\> null, return 1
* L = 1 -\> 2 -\> 3 -\> null, return 2
* L = 1 -\> 2 -\> 3 -\> 4 -\> null, return 2

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode middleNode(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }
}

```

Check If Linked List Has A Cycle:

Check if a given linked list has a cycle. Return true if it does, otherwise return false.

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        return true;
      }
    }
    return false;
  }
}

```

Insert In Sorted Linked List:

Insert a value in a sorted linked list.

Examples

* L = null, insert 1, return 1 -\> null
* L = 1 -\> 3 -\> 5 -\> null, insert 2, return 1 -\> 2 -\> 3 -\> 5 -\> null
* L = 1 -\> 3 -\> 5 -\> null, insert 3, return 1 -\> 3 -\> 3 -\> 5 -\> null
* L = 2 -\> 3 -\> null, insert 1, return 1 -\> 2 -\> 3 -\> null

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode insert(ListNode head, int value) {
    ListNode dummy = new ListNode(Integer.MIN_VALUE);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = dummy.next;
    while (curr != null) {
      if (prev.value < value && curr.value >= value) {
        break;
      }
      prev = prev.next;
      curr = curr.next;
    }
    prev.next = new ListNode(value);
    prev.next.next = curr;
    return dummy.next;
  }
}

```

Merge Two Sorted Linked Lists:

Merge two sorted lists into one large sorted list.

Examples

* L1 = 1 -\> 4 -\> 6 -\> null, L2 = 2 -\> 5 -\> null, merge L1 and L2 to 1 -\> 2 -\> 4 -\> 5 -\> 6 -\> null
* L1 = null, L2 = 1 -\> 2 -\> null, merge L1 and L2 to 1 -\> 2 -\> null
* L1 = null, L2 = null, merge L1 and L2 to null

```java
/**
 * class ListNode {
 *   public int value;
 *   public ListNode next;
 *   public ListNode(int value) {
 *     this.value = value;
 *     next = null;
 *   }
 * }
 */
public class Solution {
  public ListNode merge(ListNode one, ListNode two) {
    ListNode dummy = new ListNode(0);
    ListNode prev = dummy;
    while (one != null && two != null) {
      if (one.value < two.value) {
        prev.next = one;
        one = one.next;
      } else {
        prev.next = two;
        two = two.next;
      }
      prev = prev.next;
    }
    if (one == null) {
      prev.next = two;
    } else {
      prev.next = one;
    }
    return dummy.next;
  }
}

```

ReOrder Linked List:

Reorder the given singly-linked list N1 -\> N2 -\> N3 -\> N4 -\> … -\> Nn -\> null to be N1- \> Nn -\> N2 -\> Nn-1 -\> N3 -\> Nn-2 -\> … -\> null

Examples

* L = null, is reordered to null
* L = 1 -\> null, is reordered to 1 -\> null
* L = 1 -\> 2 -\> 3 -\> 4 -\> null, is reordered to 1 -\> 4 -\> 2 -\> 3 -\> null
* L = 1 -\> 2 -\> 3 -\> null, is reordred to 1 -\> 3 -\> 2 -\> null

```java
public class ReorderList {
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 1. find the middle node
        ListNode mid = middleNode(head);
        ListNode one = head;
        ListNode two = mid.next;
        // de-link the second half
        mid.next = null;
        // 2. reverse the second half
        // 3. merge the two halves
        return merge(one, reverse(two));
    }
    private ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (one != null && two != null) {
            cur.next = one;
            one = one.next;
            cur.next.next = two;
            two = two.next;
            cur = cur.next.next;
        }
        if (one != null) {
            cur.next = one;
        } else {
            cur.next = two;
        }
        return dummy.next;
    }
}
```

Partition Linked List:

Given a linked list and a target value T, partition it such that all nodes less than T are listed before the nodes larger than or equal to target value T. The original relative order of the nodes in each of the two partitions should be preserved.

Examples

* L = 2 -\> 4 -\> 3 -\> 5 -\> 1 -\> null, T = 3, is partitioned to 2 -\> 1 -\> 4 -\> 3 -\> 5 -\> null

```java
public class PartitionList {
    public ListNode partition(ListNode head, int target) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode curSmall = small;
        ListNode curLarge = large;
        while (head != null) {
            if (head.value < target) {
                curSmall.next = head;
                curSmall = curSmall.next;
            } else {
                curLarge.next = head;
                curLarge = curLarge.next;
            }
            head = head.next;
        }
        // connect the two partitions
        curSmall.next = large.next;
        // un-link the last node in large partition
        curLarge.next = null;
        return small.next;
    }
}
```

