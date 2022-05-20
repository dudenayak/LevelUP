package Questions_Sheet;

import java.util.*;
import java.util.LinkedList;

import org.w3c.dom.Node;

import Level2.Trees.BST.TreeNode;

public class Trees {

    // LEETCODE 543 Diameter of Binary Tree
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null)
            return 0;
        int dl = diameterOfBinaryTree(root.left);
        int dr = diameterOfBinaryTree(root.right);
        int ans = height(root.left) + height(root.right);
        return Math.max(ans, Math.max(dl, dr));
    }

    public int height(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    // LEETCODE 226 Invert Binary Tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        // swap
        root.right = left;
        root.left = right;
        return root;

    }

    // LEETCODE 572 Subtree of Another Tree
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return root == null && subRoot == null;
        } else if (isSametree(root, subRoot)) {
            return true;
        } else {
            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }
    }

    public boolean isSametree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return root == null && subRoot == null;
    } else if(root.val==subRoot.val){
        return isSametree(root.left, subRoot.left) && isSametree(root.right, subRoot.right);
    } else {
        return false;
    }

    // LEETCODE 938 Range Sum of BST

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null)
            return 0;

        if (root.val >= low && root.val <= high) {
            return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        } else {
            return rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
        }
    }

    // LEETCODE 101 Symmetric Tree

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    public boolean isSymmetricHelp(TreeNode left, TreeNode right) {
        if (left == null || right == null) {
            return left == right;
        }
        if (left.val != right.val) {
            return false;
        }

        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.right);
    }

    // LEETCODE 108 Convert Sorted Array to Binary Search Tree

    public TreeNode sortedArrayToBST(int[] nums) {
        return Helper(nums, 0, nums.length - 1);
    }

    public TreeNode Helper(int[] nums, int start, int end) {
        if (start > end)
            return null;

        int mid = (start + end) / 2;

        TreeNode root = new TreeNode(nums[mid]);

        root.left = Helper(nums, start, mid - 1);
        root.right = Helper(nums, mid + 1, end);
        return root;
    }

    // LEETCODE 617 Merge Two Binary Trees

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null)
            return null;
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;

        int val1 = root1.val;
        int val2 = root2.val;

        TreeNode root3 = new TreeNode(val1 + val2);

        root3.left = mergeTrees(root1.left, root2.left);
        root3.right = mergeTrees(root1.right, root2.right);

        return root3;

    }

    // LEETCODE 104 Maximum Depth of Binary Tree

    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));

    }

    // LEETCODE 257 Binary Tree Paths
    List<String> list = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        solution(root, "");
        return list;
    }

    public void solution(TreeNode root, String str) {
        if (root.left == null && root.right == null) {
            str = str + Integer.toString(root.val);
            list.add(str);
            return;
        }

        str = str + Integer.toString(root.val) + "->";
        if (root.left != null)
            solution(root.left, str);
        if (root.right != null)
            solution(root.right, str);
    }

    // LEETCODE 100 Same Tree

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    // LEETCODE 235 Lowest Common Ancestor of a Binary Search Tree

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    // LEETCODE 112 Path Sum

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        } else if (root.left == null && root.right == null && targetSum - root.val == 0) {
            return true;
        } else
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // LEETCODE 530 Minimum Absolute Difference in BST
    private int prevVal = Integer.MAX_VALUE;
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        if (root == null)
            return 0;

        getMinimumDifference(root.left);
        minDiff = Math.min(minDiff, Math.abs(prevVal - root.val));
        prevVal = root.val;
        getMinimumDifference(root.right);
        return minDiff;

    }

    // LEETCODE 404 Sum of Left Leaves

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        } else {
            return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
        }
    }

    // LEETCODE 110 Balanced Binary Tree

    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;

        boolean balance = true;
        if (Math.abs(getHeight(root.left) - getHeight(root.right)) <= 1) {
            balance = true;
        } else {
            balance = false;
        }
        if (!balance)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int getHeight(TreeNode root) {
        if (root == null)
            return 0;

        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    // GFG Predecessor and Successor

    public static void findPreSuc(Node root, Res p, Res s, int key) {
        if (root == null)
            return;
        if (root.key == key) {
            if (root.left)
                p = inpre(root);
            if (root.right)
                s = inpre(root);
            return;
        }
        if (key > root.key) {
            p = root;
            findPreSuc(root.right, p, s, key);
        } else if (key < root.key) {
            s = root;
            findPreSuc(root.left, p, s, key);
        }

    }

    // LEETCODE 94 Binary Tree Inorder Traversal

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    public void inorder(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);

    }

    // GFG Check Whether BST Contains Dead End

    public static boolean solve(Node root, int min, int max) {
        if (root == null)
            return false;
        if (min == max)
            return true;
        return solve(root.left, min, root.data - 1) || solve(root.right, root.data + 1, max);
    }

    public static boolean isDeadEnd(Node root) {
        // Add your code here.
        return solve(root, 1, Integer.MAX_VALUE);

    }

    // LEETCODE 173. Binary Search Tree Iterator
    private Stack<TreeNode> stack = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        pushAll(root);
    }

    public int next() {
        TreeNode temp = stack.pop();
        pushAll(temp.right);
        return temp.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushAll(TreeNode root) {
        for (; root != null; stack.push(root), root = root.left)
            ;
    }

    // LEETCODE 236. Lowest Common Ancestor of a Binary Tree

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null)
            return root;
        if (left != null)
            return left;
        return right;
    }

    // LEETCODE 95. Unique Binary Search Trees II

    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }

    public List<TreeNode> helper(int start, int end) {
        if (start > end) {
            List<TreeNode> base = new ArrayList<>();
            base.add(null);
            return base;
        }

        List<TreeNode> ans = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    ans.add(root);
                }
            }
        }
        return ans;
    }

    // LEETCODE 863. All Nodes Distance K in Binary Tree

    boolean nodeToRootPath_(TreeNode root, int data, ArrayList<TreeNode> ans) {

        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodeToRootPath_(root.left, data, ans) || nodeToRootPath_(root.right, data, ans);

        if (res)
            ans.add(root);
        return res;
    }

    public void kdown(TreeNode root, int k, TreeNode block, List<Integer> ans) {
        if (root == null || k < 0 || root == block)
            return;

        if (k == 0) {
            ans.add(root.val);
            return;
        }

        kdown(root.left, k - 1, block, ans);
        kdown(root.right, k - 1, block, ans);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath_(root, target.val, path);
        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kdown(path.get(i), k - i, block, ans);
            block = path.get(i);
        }
        return ans;
    }

    // LEETCODE 98. Validate Binary Search Tree

    public boolean isValidBST(TreeNode root) {
        return Validate(root, null, null);
    }

    public boolean Validate(TreeNode root, Integer max, Integer min) {
        if (root == null) {
            return true;
        } else if (max != null && root.val >= max || min != null && root.val <= min) {
            return false;
        } else {
            return Validate(root.left, root.val, min) && Validate(root.right, max, root.val);
        }
    }

    // LEETCODE 199. Binary Tree Right Side View

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> visibleValue = new ArrayList<>();
        if (root == null)
            return visibleValue;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                if (i == size - 1) {
                    visibleValue.add(current.val);
                }
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }
        return visibleValue;
    }

    // LEETCODE 684. Redundant Connection

    public int[] findRedundantConnection(int[][] edges) {
        int[] dSet = new int[1001];

        for (int i = 0; i < dSet.length; i++) {
            dSet[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            while (u != dSet[u]) {
                u = dSet[u];
            }

            while (v != dSet[v]) {
                v = dSet[v];
            }

            if (v == u) {
                return edges[i];
            }

            else {
                dSet[v] = u;
            }
        }
        return new int[] { -1, -1 };
    }

    // LEETCODE 102. Binary Tree Level Order Traversal

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.remove();
                currentLevel.add(current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            res.add(currentLevel);
        }
        return res;
    }
}
