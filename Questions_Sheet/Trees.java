package Questions_Sheet;

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
}
