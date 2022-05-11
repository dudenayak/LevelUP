package Questions_Sheet;

import Level2.Trees.BST.TreeNode;

public class Trees {

    // LEETCODE 543
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

    // LEETCODE 226
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

    // LEETCODE 572
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
}
