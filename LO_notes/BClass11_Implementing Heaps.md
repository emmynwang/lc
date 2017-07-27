Implement PriorityQueue (Heap)

Definition

* Heap is a (binary) tree based data structure
* Across the entire tree, the relation between a parent node and a child node stays consistent.
  * Example: min heap
    * the parent node is always \<= its two child nodes (parent node is the smallest node in the subtree rooted at itself). 只在乎parent node和child nodes关系
    * the relation between the two child nodes can differ
* The common implementation of a heap is using a **complete binary tree**
  * A “**complete binary tree**” is a binary tree in which every level, except possibly the last level, is completely filled, and all nodes are as far left as possible. (从上往下，从左往右
  * Min Heap Example

 1

 / \\

 3 10

 / \\ / \\

 5 4 13 19

 / \\ /

11 8 6 

{1, 3, 10, 5, 4, 13, 19, 11, 8, 6}

 0 1 2 3 4 5 6 7 8 9 

 19

 / \\

 13 6

 / \\ / \\

 8 11 5 4

 / \\ /

3 7 1 

{19, 13, 6, 8, 11, 5, 4, 3, 7, 1}

 0 1 2 3 4 5 6 7 8 9 

0 —\> left: 1; right : 2;

1 —\> left: 3; right : 4;

4 —\> left: 9; right : 10;

Representation

* it can also be represented as an array (by the level order traversal of the binary tree), since it’s a complete binary tree.
  * Why? - if it is a complete binary tree, the matching between the nodes and the index of the array is determined, and the relation of parent and child nodes can be well transferred to the relation between two indices.
* Example:
  * index of parent = i, what is the index of the two child nodes?
    * left child of index left = 2 \* i + 1
    * right child of index righti = 2 \* i + 2
    * parent of index i = (i - 1) / 2
    * the room of the tree is at index 0.
  * 树往上找很难，LinkedList往回找难

Basic Heap Internal Operations

* **percolateUp()**

========================

  * when to use?
    * the element need to be moved up to maintain the heap’s property, for example, when offering a new element into the heap.
  * how to use?
    * compare the element with its parent, move it up when necessary, do this until the element does not need to be moved.
  * Note: 只和parents有关系，和sibling没有关系。（类似杜拉拉升职记）
* offer(2)

 1

 / \\

 32  10 

 / \\ / \\

 5 42 3 13 19

 / \\ / \\

11 8 6  24  

* **percolateDown()**

==========================

  * when to use?
    * the element need to be moved down to maintain the heap’s property, for example, when poll the root element from the heap
  * how?
    * compare the element with its two children, if the smallest one of the two children is smaller than the element, swap the element with that child, do this until the element does not need to be moved. (和两个children比
* poll()

 163

 / \\

 364 10

 / \\ / \\

 5 46 13 19

 / \\ /

11 8 6 

* poll 1, move the last element to the top position

只影响该影响的那条，一把手刘立春书记被带走了，把最小的提起来，然后往下降

* **Heapify()**

====================

  * convert an array into a heap in O(n) time.
  * how?
    * for each node that has at least one child, we perform percolateDown() action, in the order of from the nodes on the deepest level to the root.
    * the time complexity of heapify is** O(n)**
    * note: index 从大到小来
    * if the heap has n elements
      * n = 9: range[0, 3]
      * n = 8: range[0, 3]
      * n = 7: range[0, 2]
      * the range of indices need to perform percolateDown() is : [0, n/2 - 1]
      * last index’ parent = ((n - 1) - 1) / 2 = (n-2)/2 = n/2 - 1
* 

0 10

 / \\

1 11 7

 / \\ / \\

2 2 8 4 6

 / \\ /

3 13 3 

proof :

k level full tree : (k = 4)

n total number of nodes: 2^k - 1

k: 层数， 2^k: number of nodes 节点数 O(2^k) = O(n)

* Heapify via percolateUp: S = (0 \* 2^0 ) 1\*2^1 + 2\*2^2 +