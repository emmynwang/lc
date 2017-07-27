heap: priority queue 堆

Example 

 0

 / \\

 3 1

 / \\ /

 5 4 7 2 perlocate == swap 

index 0 1 2 3 4 5 

 1 3 2 5 4 7 2

**Heap:** ** **

性质：堆的实现通过构造 binary heap，这种数据结构具有以下性质：

1\. 任意节点nodes小于它的所有后裔，最小元素在堆的root （堆序性）

2\. heap is always a **complete** tree.

3\. 将根节点最大的堆叫做max heap，根节点最小的堆叫做min heap （the root is the min）

4\. index of leftChild = index of parent \* 2 + 1 = 1(3的index is 1) \* 2 +1 = 3 (0\*2+1 = 1)

5\. index of rightChild = index of parent \*2 + 2 = 1 

6\. **unsorted** but follow the rules above. (can be considered as Partially ordered)

支持的基本操作 (Major Operations)： 

1\. **insert**: insert a new element into the heap; 向堆中插入一个新元素 Time Complexity: O(log(n)) **(整个层数是以2为底的n (log\_2(n)), )**

2.** update**: 将新元素提升至使其符合堆的性质 Time Complexity: O(log(n)) (min-heap 和小的child换) 层数

3\. **get/ top**: 获取当前堆顶元素的值； Time Complexity: O(1) （return array[0]）

### 4\. pop: 删除堆顶元素的值：Time Complexity:  O(log(n)) (swap还是update最后一个到root，再调整percolate down)???

5\. heapify: 使得一个unsorted array 变成一个heap. Time Complexity: O(n) ? (heapsort)

Max - Heap: (08722)

A max-heap is a binary tree with the following characteristics:

1\. It’s **complete** (almost). Every level of the tree has the maximum number of nodes except possibly the last level. It’s filled in reading from left to right across each row. 

2\. The **largest data **is at the root of the tree. (最大值在root)

3\. For every node in the max-heap, its children contain smaller keys.

A few analysis:

1\. A heap is NOT a sorted structure and can be considered as partially ordered.

2\. What can we learn from the fact that a heap is a complete binary tree? It should always have the smallest possible \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_which is\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_

3\. It’s a useful data structure when we need to keep getting the object with the highest priority.

**Add (insert) a new data into Max-Heap:**

**
**

**
**

**
**

**
**

**Q1 Find the smallest k elements from an unsorted array of size n.**

**solution 1: **sort it and return first k elements. Time Complexity = O (nlogn) 如果n特别大，时间复杂O大

solution 2: 

**Solution 3: MIN-HEAP of size n**

 Step 1: heapify the whole array to make it a MIN-heap. **heapify O(n)**

 Step 2: keep popping k times. k\*log(n)

 Total time = O(n) + O(klog(n))

**Solution 4: MAX-Heap of size k** 挑选k个数，
=========================================

 xxxxxx Yxxxxxxxxxx 

 --------

 size = k 

进来一个和最大的比

Step 1: insert all first k elements into a max-heap size is k (optimization heapify first k element) **insert: O(k)**

Step 2: from the k+1-th element to the n-th elementl, 

 if the current element Y 

 case2.1: Y \< max-heap.top(), we call **max-heap.pop()**, and then **max-heap.insert(Y) 如果Y比maxheap中最大的小就pop最大的insert**

** **case2.2: Y \> max-heap.top(), do nothing. 

**Time = O(k) + O((n-k) \* log(k))**

讨论一个solution是否好：

1\. 先讨论Time Complexity and Space Complexity. (Systematical way of thinking系统性分析问题)

Let me do the analysis for you. From the perspective of time complexity, which one is better?

=========================================================================

 **MIN-HEAP ** **MAX-HEAP of size k**

 O(n) + O(k \* log(n)) O(k) + O((n-k) \* log(k)) (???)

case 1: k \<\<\<\<\<\<\<\< n 

 O(n) O(n) \* log(k) (k can be seen as a constant, it doesn’t change with the variable x )

  it’s hard to say 

case 2: k ~ n 一个level (k = n/2)

 O(nlog(n)) O(nlog(n))  it’s hard to say 

**Solution** 5: QuickSort principle: Worst case scenario = O (n^2)

average time = O(n)

1st iteration xxxxxxxxxxxx Pivot xxxxxxxxxxxxx quick-partition O(n)

每一轮把搜索的范围减少一半

2nd iteration  xxxxxxxx P2 xxxxxx quick-partition O(n/2)

3rd iteration   xxxxP3xxxx quick-partition O(n/4)

on average = O(n + n/2 + n/4 + n/8 + …) = O(2\*n) = O(n)

worst case = O (n^2) 

**===================================================================**

**Graph**

**===================================================================**

0 - 1

| / | \\ 2

4—3 /

\<01\> \<14\> \<23\> \<13\> \<04\> 

1\. Node / State

2\. Edge / action

3\. Directed vs undirected graph

4\. Representation of the graph

 ** 4.1 Adjacency Matrix **

 0 1 2 3 4 

0 0 1 0 0 1

1 1 0 1 1 1

2

3

4

**Pros**: Representation is easy to implement (n^2 matrix). Edge removal takes O(1) time. 

 Queries like whether there is an edge from vertex “u” to vertice “v” are efficient and can be done O(1) 

**Cons**: Consumes more space O(V^2) (V is the number of vertices/ nodes) 

 Even if the graph is sparse (contains less number of edges) = waste of space

 4.2a **Adjacency List **(Linked List)

0 -\> 1 -\> 4

1 -\> 0 -\> 4-\> 3 -\> 2

2 -\> 1 -\> 3

3 -\> 1 -\> 4 -\> 2

4 -\> 3 -\> 0 -\> 1 

Vertices / Nodes |V|

Edges: |E|

**Pros**: Space Complexity = O(|V| + |E|) Adding a vertice / node to the graph is easy 节省空间

**Cons**: Time Complexitity is O(V) to check whether there is an edge from a node to the node (Compared to O(1) in adjacent matrix)

 4.2a **Use a Hash Table**

**
**

\<Key = node, Value = set of successors \<Node\>\>

e.g. Time Complexity: O(1), 

 \< 0, \<1, 4\> \> , \< 1, \<0, 2, 3, 4\> \>,  \< 2, \<1, 3\> \> , \< 3, \<1, 4, 2\> \>,  \< 4, \<3, 0, 1\> \>

** **

**==================== 图里常用的Search 算法 ======================**

**
**

**1\. Breadth - First - Search (BFS-1)**

** ** 1 **expand** (1) ——\> **generate** (3) and generate(2)

 / \\

 3 2 size = 2

 / \\ / 

 5 4 7 size = 3

 / \\

 9 11

print/ visit = 1 32 547 9 11

genral graph, 一环 二环 三环…

分层打印 

32

547

9 11

how to change line? (记录当前queue size)

1\. how to describe a relatively complexity algorithm: 

1. what kind of **data structure** that this algorithm uses
  1. queue (why? BFS 穷尽每一层才进入下一层，比较靠前的nodes and their children nodes will be recored)
2. what are the **actions** of this algorithm step by step
  1. describe : expand parents’ nodes 1 and generate its left children 3 and right children 2
  2. **initial state**: insert the start node(root) into the queue, queue = {1}
  3. **process**: while the queue is not empty
    1. pop the left-most element out of the queue and expand it.
      1. generate all its successors and insert all of them into the tail of the queue
  4. **termination condition**: when the queue is empty

BFS的操作过程 & How to describe a BFS’s action during an interview?

* **Definition 1:** expand a nodes: (延展一个node) e.g. visit / print its value
* **Definition 2:** generates neighbor node: reach out to its beighboring node (First to generate Node 3, and then to generate Node 2)
* **Data Structure**: Maintain a FIFO queue, put all generated nodes in the queue. e.g. 3 and 2 into the queue(FIFO) {3, 2}
* **Termination condition**: do a loop until the queue is empty.

**经典例题1： 分层打印一个Binary Tree. (Leetcode 102 Binary Tree Level Order Traversal)**

// param: root - the root of the tree

```java
public void BFS(Node root) {  // 传入root
  if (root == null)
      return;
  Queue<Node> q = new LinkedList<Node>();
  q.offer(root);  // put root into the queue
  while(!q.empty())
  {
    int size = q.size(); // *** 会变!!! 记录当前层size = # of generated nodes in the next layer. 维护一个variable（定住）
    // print 1 layer in each iteration 
    for (int i=0; i < size; i++){ 
      Node n = q.remove();
      if(n.left != null)
        q.offer(n.left);  // why offer rahter than .add? 
      if(n.right != null)
        q.offer(n.right);
      System.out.print(n.val+"");
    }
    System.out.println();// change line here
  }
}
```

**经典例题2：Bipartite : （Yahoo onsite final round）(LInkedin)**

whether a graph’s node can be divided into two groups, such that the nodes in each group do not have direct edges between the nodes that belong to the same 

group. 

<http://www.geeksforgeeks.org/bipartite-graph/>

key points: 

1. bfs每一层在不同的set
2. odd closed sets are not （奇数闭环不是）
3. 距离是1的都不能在同一个set

example: triangle 三角形 queue = {2, 3} queue = {3} induction 

 1(u) —— 2(v)

 \\ /

 3(v!=u)

**经典例题3: **Determine whether a binary tree is a **complete binary tree**

**
**

** ** 1 **expand** (1) ——\> **generate** (3) and generate(2)

 / \\

 3 2 = is the first node that misses one child node, 同层后面的node 不能出现miss child nodes了

 / \\ / \\ if和2同层 4 6

 5 4 7 null / \\ 

 3 null

 ** **

Solution: After detecting the first node that misses one child, then check whether all following nodes expanded to see

whether they have any node generated (if any —\> then false)

Discussion: **when to consider to use BFS1**??? 什么时候首选BFS

1. When we want to deal with the node relationship in the same level. (level related)
2. (Common mistakes) BFS 1 is NOT the right algorithm to find the shortest path from point A to point B in an arbitrary graph (cost might not be the same)
  1. if the edge costs in the graph are all the same(= uniform) then BFS1 can find the shortest

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

2\. **Best First Search (BFS-2) **

**key points:****用的data structure: Priority Queue(PQ 每轮可以pop出一个最小值/)**

AI: 无人车 3种，博大精深，

经典算法： Dijkstra’s algorithm (runtime efficiency improvement: A\* algorithm)

1. **Usages**: Find the shortest path cost from a single node(source node) to any other nodes in that graph (点到面(==所有点)的最短距离算法)
2. **Example problem**: 从北京到中国其他所有主要城市的最短距离是多少。
3. Data Structure: Priority Queue (Min\_heap)
4. 解题思路：
  1. Initial State (Start Node): put into the PQ
  2. Node expansion/ Generation rule: 从PQ pop出最小值
  3. Termination condition: 所有点都计算完毕才停止，也就是priority\_queue 变空
5. Example：
  1. Start Point is 4
  2. cost(node) = cost(parent of node) + c(parent of node, node)
    1. e.g. cost(2) = cost(5) + c(5, 2) = 1+1=2
  3. Cost[4, 5] = 10;
  4. Cost for all the rest edges = 1;

 (start)

6 ———4—-(10)—— 5 ——— 1(goal)

 | (1) | (1) /

 3 ——(1) — - 2 — (1)

**Tie - Breaking Strategy: Random**

1.1 Step 0 (Intial State): No nodes have been expanded, Node(4, 0)

1.2 Step 1: pop node (4, 0) out of the pq, expand node(4,0) generate three successors (Key1 == 0)

1. Node(5, 10)
2. Node(3, 1)
3. Node(6, 1)

1.3 Step 2: pop node (6, 1) generate nothing pq = {Node(5, 10), Node(3, 1)} Key 2 = 1

1.4 Step 3: pop node(3, 1) generate node (2, cost == parent cost + 1) = (2, 2).

1. p\_queue = {node(5, 10), node(2,2)} Key 3 = 1

1.5 Step 4: pop node(2, 2) generate what???? 

1. re-generate node (5, 10) —\> node(**5**, 2+1) —\> node(5, 3)
2. generate node (**1**, 3)

1.6\. Step 5…. pop node (5, 3) generate node (1, 4); pop node (2, 2) generate node (1, 3)

1.7\. Until the p\_queue is empty, then we terminate.

**Properties:**

1. one node can be expanded once and only once
2. one node can be generated more than once. (cost can be reduced over time) 后一次generate一定比前一次低
3. all the cost of nodes that are expanded are monotonically non-decreasing (所有从pq里面pop出来的元素的值是单调非递减的—\> 单调递增的)
4. Time Complexity, for a graph with n nodes and the connectivity of the node is O(nlogn)
5. when a node is popped out for expansion, its value is fixed which is equal to the shortest distance from the start node.
6. 

经典考题 （运用 Dijkstra’s Algorithm 的性质）

Given a *n* x *n* matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element. 

e.g

1 2 3 4 5

2 3 4 5 6

3 4 5 6 7

4 5 6 7 8

5 6 7 8 9

1. . initial state (start node) = [0][0] = 1
2. Node expansion/ Generation rule:
  1. expansion[0][0]
    1. generate[0][1]
    2. generate[1][0]
  2. expansion[i][j]
    1. generate[i][j+1]
    2. generate[i+1][j]
3. Termination condition:
  1. when the k-th element is popped out of the pq (based on the propery 3: all the cost of nodes that are expanded are monotonically non-decreasing (所有从pq里面pop出来的元素的值是担掉飞递减的—\> 单调递增的)
4. Analyisis
  1. Time Complexity: There are totally k nodes to be popped out of the q-queue (=k iterations)
  * ** for each iteration**
      1. pop 1 element out of the p-queue (pq size \< 2k, each expand a node will generate 2 nodes) time = log(2k)
      2. generate 2 elements and insert them into the pq time = 2\*log(2k)???
        1. total time for each iteration = 3\*log(2k)
        2. since there are k iterations, the total time = k \* 3 log(2k) = 3k(log2k) = **k log(k)**

**
**

1 2a 2b 3a 3a 3b 3c 4

k = 7, your solution return 3c, correct answer should be 4.

the reason is due to the fact that 3a was generated twice!!!

Our question is how to de-duplicate 3a 3a?

1. method1: we can use a hash\_set\<pair\<x, y\>\>
2. method2: use another 2D matrix
3.