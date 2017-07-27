K Smallest In Unsorted Array:

Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.

Assumptions

* A is not null
* K is \>= 0 and smaller than or equal to size of A

Return

* an array with size K containing the K smallest numbers in ascending order

Examples

* A = {3, 4, 1, 2, 5}, K = 3, the 3 smallest numbers are {1, 2, 3}

```java
public class KSmallest {
    // Method 1: K sized max-heap 
    // Time Complexity: O(K + N*logK)
    // Space: O(K)
    // Where N is the length of array, K is the input argument
    //?? static int[] 
    public int[] kSmallestMaxHeap(int[] array, int k) {
        // handle all possible corner cases at the very beginning
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // do not use "==" here  ??? 
                if (o1.equals(o2)) {
                    return 0;
                }
                return o1 > o2 ? -1 : 1;
            }
        });
        
        // PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < array.length; i++) {
            if (i < k) {
                // offer the first k elements into max heap.
                // NOTICE: if you want to utilize heapify(), the only thing you can do
                // is to call a certain constructor of PriorityQueue.
                maxHeap.offer(array[i]);
            } else if (array[i] < maxHeap.peek()) {
                // for the other elements, only offer it into the max heap if it is
                // smaller than the max value in the max heap
                maxHeap.poll();
                maxHeap.offer(array[i])'' 
            }
        }
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = maxHeap.poll();
        }
        return result;
    }
    
    // Method 2: quick select
    public int[] kSmallestII(int[] array, int k) {
        // handle corner cases at the beginning.
        if (array.length == 0 || k == 0) {
            return new int[0];
        }
        // quickselect to find the kth smallest element.
        // after calling this method, the first k elements in the array are
        // the k smallest ones(but not sorted).
        quickSelect(array, 0, array.length - 1, k - 1);
        // copy out the first k elements and sort them
        int[] result = Arrays.copyOf(array, k);
        Arrays.sort(result);
        return result;
    }
    
    private void quickSelect(int[] array, int left, int right, int target) {
        // like quick sort, we need to do the partition using pivot value.
        int mid = partition(array, left, right);
        // unlike quick sort, we only need to do quickselect on at most one
        // partition
        // if the pivot is already the kth smallest element, we can directly return.
        if (mid == target) {
            return;
        } else if (target < mid) {
            // only need to recursively call quick select on the left partition
            // if the kth smallest one is in the left partition.
            quickSelect(array, left, mid - 1, target);
        } else {
            // only need to recursively call quick select on the right partition
            // if the kth smallest one is in the right partition.
            quickSelect(array, mid + 1, right, target);
        }
    }
    
    private int partition(int[] array, int left, int right) {
        int pivot = array[right];
        int start = left;
        int end = right - 1;
        while (start <= end) {
            if (array[start] < pivot) {
                start++;
            } else if (array[end] >= pivot) {
                end--;
            } else {
                swap(array, start++, end--);
            }
        }
        swap(array, start, right);
        return start;
    }
    
    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }
}
```

Kth Smallest Number In Sorted Matrix:

Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

Assumptions

* the matrix is not null, N \> 0 and M \> 0
* K \> 0 and K \<= N \* M

Examples

{ {1, 3, 5, 7},

 {2, 4, 8, 9},

 {3, 5, 11, 15},

 {6, 8, 13, 18} }

* the 5th smallest number is 4
* the 8th smallest number is 6

```java
public class KthSmallestInSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        // best first search, need a minheap on the value of each cells.
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>() {
            @Override
            public int compare(Cell c1, Cell c2) {
                if (c1.value == c2.value) {
                    return 0;
                }
                return c1.value < c2.value ? -1 : 1;
            }
        });
        // all the generated cells will be marked true,
        // to avoid being generated more than once.
        boolean[][] visited = new boolean[rows][columns];
        minHeap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;
        for (int i = 0; i < k - 1; i++) {
            Cell cur = minHeap.pool();
            // the neighbor cell will be inserted back to the minHeap only if 
            // 1. it is not out of boundary.
            // 2. it has not been generated before.
            // because for each cell it could be generated twice.
            if (cur.row + 1 < rows && !visited[cur.row + 1][cur.column]) {
                minHeap.offer(new Cell(cur.row + 1, cur.column, matrix[cur.row + 1][cur.column]));
                visited[cur.row + 1][cur.column] = true;
            }
            if (cur.column + 1 < columns && !visited[cur.row][cur.column + 1) {
                minHeap.offer(new Cell(cur.row, cur.column + 1, matrix[cur.row][cur.column + 1]));
                visited[cur.row][cur.column + 1] = true;
            }
        }
        return minHeap.peek().value;
    }
    static class Cell {
        int row;
        int column;
        int value;
        Cell(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
}

```

Check If Binary Tree Is Completed:

Check if a given binary tree is completed. A complete binary tree is one in which every level of the binary tree is completely filled except possibly the last level. Furthermore, all nodes are as far left as possible.

Examples

 5

 / \\

 3 8

 / \\

1 4

is completed.

 5

 / \\

 3 8

 / \\ \\

1 4 11

is not completed.

Corner Cases

* What if the binary tree is null? Return true in this case.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "\#" denoting the null node.

For Example:

The sequence [1, 2, 3, \#, \#, 4] represents the following binary tree:

 1

 / \\

 2 3

 /

 4

```java
public class CheckCompleted {
    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // if the flag is set true, there should not be any child nodes afterwards.
        boolean flag = false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            // if any of the child is not present, set the falg to true.
            if (cur.left == null) {
                flag = true;
            } else if (flag) {
                // if flag is set but we still see cur has a left child,
                // the binary tree is not a completed one.
                return false;
            } else {
                // if flag is not set and left child is present.
                queue.offer(cur.left);
            }
            // same logic applied to the right child.
            if (cur.right == null) {
                flag = true;
            } else if (flag) {
                return false;
            } else {
                queue.offer(cur.right);
            }
        }
        return true;
    }
}

```

Bipartite:

Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two groups such that no nodes have direct edges to other nodes in the same group.

Examples

1 -- 2

 / 

3 -- 4

is bipartite (1, 3 in group 1 and 2, 4 in group 2).

1 -- 2

 / |

3 -- 4

is not bipartite.

Assumptions

* The graph is represented by a list of nodes, and the list of nodes is not null.

```java
public class Bipartite {
    public boolean isBipartite(List<GraphNode> graph) {
        // use 0 and 1 to denote two different groups.
        // the map maintains for each node which group it belongs to.
        HashMap<GraphNode, Integer> visited = new HashMap<>();
        // the graph can be represented by a list of nodes (if it is not guaranteed)
        // to be connected). we have to do BFS from each of the nodes.
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
        // if this node has been traversed, no need to do BFS again
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<>();
        queue.offer(node);
        // stazrt BFS from the node, since the node has not been visited
        // we can assign it to any of the groups, for example, 
        // group 0.
        visited.put(node, 0);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            // the group assigned for cur node.
            int curGroup = visited.get(cur);
            // the group assigned for any neighbor of cur node.
            int neiGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode nei : cur.neighbors) {
                // if the neighbor has not been visited, just put it in the queue
                // and choose the correct group.
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup) {
                    // only if the neighbor has been traversed and the group does not
                    // match to the desired one, return false.
                    return false;
                }
                // if the neighbor has been traversed and the group matches the
                // desired one, we do not need to do anything.
            }
        }
        return true;
    }
}

```

Get Keys In Binary Tree Layer By Layer:

Get the list of list of keys in a given binary tree layer by layer. Each layer is represented by a list of keys and the keys are traversed from left to right.

Examples

 5

 / \\

 3 8

 / \\ \\

 1 4 11

the result is [ [5], [3, 8], [1, 4, 11] ]

Corner Cases

* What if the binary tree is null? Return an empty list of list in this case.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "\#" denoting the null node.

For Example:

The sequence [1, 2, 3, \#, \#, 4] represents the following binary tree:

 1

 / \\

 2 3

 /

 4

```java
public class LayerByLayer {
    public List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (root == null) {
            return list;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // the list storing all the nodes on the current level.
            List<Integer> curLayer = new ArrayList<>();
            // the size of current level.
            int size = queue.size();
            // traverse all the nodes on the current level and
            // prepare for the next level.
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                curLayer.add(cur.key);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            list.add(curLayer);
        }
        return list;
    }
}
```