In-order Traversal Of Binary Tree:

Implement an iterative, in-order traversal of a given binary tree, return the list of keys of each node in the tree as it is in-order traversed.

Examples

 5

 / \\

 3 8

 / \\ \\

1 4 11

In-order traversal is [1, 3, 4, 5, 8, 11]

Corner Cases

* What if the given binary tree is null? Return an empty list in this case.

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
public class Inorder {
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        Deque<TreeNode> staz//ck = new LinkedList<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // always try go to the left side to see if there is any node
            // should be traversed before the curr node, curr node is stored
            // on stack since it has not been traversed yet.
            if (curr != null) {
                stack.offerFirst(curr);
                curr = curr.left;
            } else {
                // if can not go left, meaning all the nodes on the left side of 
                // the top node on stack have been traversed, the next traversing
                // node should be the top node on stack.
                curr = stack.pollFirst();
                inorder.add(curr.key);
                curr = curr.right;
            }
        }
        return inorder;
    }
}
```

Pre-order Traversal Of Binary Tree:

Implement an iterative, pre-order traversal of a given binary tree, return the list of keys of each node in the tree as it is pre-order traversed.

Examples

 5

 / \\

 3 8

 / \\ \\

1 4 11

Pre-order traversal is [5, 3, 1, 4, 8, 11]

Corner Cases

* What if the given binary tree is null? Return an empty list in this case.

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
public class PreOrder {
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        if (root == null) {
            return preorder;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pollFirst();
            // the left subtree should be traversed before right subtree,
            // since stack is LIFO, we should push right into the stack first,
            // so for the next step the top element of the stack is the left
            // subtree
            if (curr.right != null) {
                stack.offerFirst(curr.right);
            }
            if (curr.left != null) {
                stack.offerFirst(curr.left);
            }
            preorder.add(curr.key);
        }
        return preorder;
    }
}
```

Post-order Traversal Of Binary Tree:

Implement an iterative, post-order traversal of a given binary tree, return the list of keys of each node in the tree as it is post-order traversed.

Examples

 5

 / \\

 3 8

 / \\ \\

1 4 11

Post-order traversal is [1, 4, 3, 11, 8, 5]

Corner Cases

* What if the given binary tree is null? Return an empty list in this case.

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
public class PostOrder {
    // Method 1: post-order is the reverse order of pre-order with traversing
    // right subtree before traversing left subtree.
    public List<Integer> postOrderI(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> preOrder = new LinkedList<>();
        preOrder.offerFirst(root);
        while (!preOrder.isEmpty()) {
            TreeNode current = preOrder.pollFirst();
            // conduct the result for the special pre-order traversal.
            result.add(current.key);
            // in pre-order the right subtree will be traversed before the
            // left subtree so pushing left child first.
            if (current.left != null) {
                preOrder.offerFirst(current.left);
            }
            if (current.right != null) {
                preOrder.offerFirst(current.right);
            }
        }
        // reverse the pre-order traversal sequence to get the post-order result.
        Collections.reverse(result);
        return result;
    }
    
    // Method 2: check the relation between the current node and the previous node
    // to determine which direction should go next.
    public List<Integer> postOrderII(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.offerFirst(root);
        // to record the previous node on the way of DFS so that
        // we can determine the direction.
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peekFirst();
            // if we are going down, either left / right direction.
            if (prev == null || cur == prev.left || cur == prev.right) {
                // if we can still go down, try go left first.
                if (cur.left != null) {
                    stack.offerFirst(cur.left);
                } else if (cur.right != null) {
                    stack.offerFirst(cur.right);
                } else {
                    // if we can not go either way, meaning cur is a leaf node.
                    stack.pollFirst();
                    result.add(cur.key);
                }
            } else if (prev == cur.right || prev == cur.left && cur.right == null) {
                // if we are going up from the right side or going up from the left side
                // but we can not go right afterwards.
                stack.pollFirst();
                result.add(cur.key);
            } else {
                // otherwise, we are going up from the left side and we can go down
                // right side.
                stack.offerFirst(cur.right);
            }
            prev = cur;
        }
        return result;
    }
}
```

Check If Binary Tree Is Balanced:

Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node’s left and right subtree differ by at most 1.

Examples

 5

 / \\

 3 8

 / \\ \\

1 4 11

is balanced binary tree,

 5

 /

 3

 / \\

1 4

is not balanced binary tree.

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
public class CheckBalanced {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // use -1 to denote the tree is not balanced.
        // >= 0 value means the tree is balanced and it is the height of the tree.
        return height(root) != -1;
    }
    
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        // if left subtree is already not balanced, we do not need to continue
        // and we can return 01 directly.
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = height(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        // if not balanced, return -1.
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
```

Symmetric Binary Tree:

Check if a given binary tree is symmetric.

Examples

 5

 / \\

 3 3

 / \\ / \\

1 4 4 1

is symmetric.

 5

 / \\

 3 3

 / \\ / \\

1 4 1 4

is not symmetric.

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
public class CheckSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right)''
    }
    private boolean isSymmetric(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.key != two.key) {
            return false;
        }
        return isSymmetric(one.left, two.right)
        && isSymmetric(one.right, two.left);
    }
}
```

Tweaked Identical Binary Trees:

Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. A tweak is defined as a swap of the children of one node in the tree.

Examples

 5

 / \\

 3 8

 / \\

1 4

and

 5

 / \\

 8 3

 / \\

 1 4

the two binary trees are tweaked identical.

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
public class CheckTweakedIdentical {
    public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.key != two.key) {
            return false;
        }
        return
        isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right) 
        ||
        isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
    }
}
```

Is Binary Search Tree Or Not:

Determine if a given binary tree is binary search tree.

Assumptions

* There are no duplicate keys in binary search tree.
* You can assume the keys stored in the binary search tree can not be Integer.MIN\_VALUE or Integer.MAX\_VALUE.

Corner Cases

* What if the binary tree is null? Return true in this case.

```java
public class CheckBST {
    public boolean isBST(TreeNode root) {
        return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.key < min || root.key > max) {
            return false;
        }
        return isBST(root.left, min, root.key - 1)
        && isBST(root.right, root.key + 1, max);
    }
}
```

Get Keys In Binary Search Tree In Given Range:

Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.

Examples

 5

 / \\

 3 8

 / \\ \\

 1 4 11

get the keys in [2, 5] in ascending order, result is [3, 4, 5]

Corner Cases

* What if there are no keys in the given range? Return an empty list in this case.

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
public class GetRange {
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> list = new ArrayList<>();
        getRange(root, min, max, list);
        return list;
    }
    private void getRange(TreeNode root, int min, int max, List<Integer> list) {
        if (root == null) {
            return;
        }
        // 1. determine if left subtree should be traversed, on,y when root.key >
        // min, we should traverse the left subtree.
        if (root.key > min) {
            getRange(root.left, min, max, list);
        }
        // 2. determine if root should be traversed.
        if (root.key >= min && root.key <= max) {
            list.add(root.key);
        }
        // 3. determine if right subtree should be traversed, only when
        // root.key < max, we should traverse the right subtree.
        if (root.key < max) {
            getRange(root.right, min, max, list);
        }
    }
}
```