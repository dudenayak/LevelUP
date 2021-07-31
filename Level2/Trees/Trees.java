package Level2.Trees;

import java.util.ArrayList;
import java.util.List;

public class Trees {
    public static class TreeNode {

        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static int size(TreeNode root) {
        return root == null ? 0 : size(root.left) + size(root.right) + 1;
    }

    public static int height(TreeNode root) {
        return root == null ? -1 : Math.max(height(root.left), height(root.right)) + 1;
        // -1 beacuse null ki koi height nahi hoti and we return in base case jo ans me
        // possible na ho...height
        // can be 0(if root case ke right ya left koi aur edge na ho) but not -1
        // 1 edge = height
    }

    public static int maximum(TreeNode root) {
        return root == null ? -(int) 1e9 : Math.max(root.val, Math.max(maximum(root.left), maximum(root.right)));
    }

    public static int minimum(TreeNode root) {
        return root == null ? (int) 1e9 : Math.min(root.val, Math.min(minimum(root.left), minimum(root.right)));
    }

    public static boolean find(TreeNode root, int data) {
        if (root == null)
            return false;

        if (root.val == data)
            return true;

        return find(root.left, data) || find(root.right, data);
    }

    // Pep Ques-----------------------Node To Root Path Binary Tree

    // Method1.....direct....without helper functionnnn

    public static ArrayList<TreeNode> nodeToRootPath(TreeNode root, int data) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.val == data) {
            ArrayList<TreeNode> ans = new ArrayList<>();
            ans.add(root);
            return ans;
        }

        ArrayList<TreeNode> left = nodeToRootPath(root.left, data);
        if (left.size() != 0) {
            left.add(root);
            return left;
        }
        ArrayList<TreeNode> right = nodeToRootPath(root.right, data);
        if (right.size() != 0) {
            right.add(root);
            return right;
        }
        return new ArrayList<>();
    }
    // -----------------------------end of method1-------------------------

    // Method2.....indirect....with helper functionnnn
    public static boolean nodetorootpath(TreeNode root, int data, ArrayList<TreeNode> ans) {
        if (root == null)
            return false;

        if (root.val == data) {
            ans.add(root);
            return true;
        }

        boolean res = nodetorootpath(root.left, data, ans) || nodetorootpath(root.right, data, ans);
        if (res)
            ans.add(root);
        return res;
    }

    public static ArrayList<TreeNode> nodeToRootPath1(TreeNode root, int data) {
        ArrayList<TreeNode> ans = new ArrayList<>();
        nodetorootpath(root, data, ans);
        return ans;
    }
    // ---------------------------------end of method2--------------------------

    // Pep Ques----------------Root To All Leaf Path In Binary Tree
    public static void rootToAllLeafPath(TreeNode root, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> myAns) // function
                                                                                                                     // called
    {
        if (root == null)
            return;
        if (root.left == null && root.right == null) // if both left and right are null then it is a leaf
        {
            ArrayList<Integer> base = new ArrayList<>(myAns); // deep copy(create new arraylist)
            base.add(root.val);// before deep copying add to base
            ans.add(base);// deep copied and stored in ans {myAns->base(root.val)->ans}
            return;
        }

        myAns.add(root.val);
        rootToAllLeafPath(root.left, ans, myAns); // to traverse in tree
        rootToAllLeafPath(root.right, ans, myAns);
        myAns.remove(myAns.size() - 1); // removing last element while backtracking
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        ArrayList<Integer> myAns = new ArrayList<>();

        rootToAllLeafPath(root, ans, myAns);
        return ans;
    }

    // Pep Ques----------------All Single Child Parent In Binary Tree
    public static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) // if base case is true then we move ahead and if
                                                                       // false then we go to next loop
            return;

        if (root.left == null || root.right == null) // if left==null is false then we check for right and add ans in
                                                     // root.val and vice versa
        {
            ans.add(root.val);
        }

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root, ans);// function is called in line126 with the datatype treenode
        return ans;
    }

    // Pep Ques---------------- Count All Single Child Parent In Binary Tree
    public static int countExactlyOneChild(TreeNode node) {
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int leftSinglechildCount = countExactlyOneChild(node.left);
        int rightSinglechildCount = countExactlyOneChild(node.right);

        int ans = leftSinglechildCount + rightSinglechildCount;
        if (node.left == null || node.right == null)
            ans++;

        return ans;
    }

    public static void burningTreeNode(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null || root == blockNode)
            return;
        if (time == ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);

        burningTreeNode(root.left, time + 1, blockNode, ans);
        burningTreeNode(root.right, time + 1, blockNode, ans);

    }

    public static int burningTree(TreeNode root, int fireNode, ArrayList<ArrayList<Integer>> ans) {
        if (root == null)
            return -1;
        if (root.val == fireNode) // val is representing the currennt root
        {
            burningTreeNode(root, 0, null, ans);
            return 1;
        }

        int lt = burningTree(root.left, fireNode, ans);
        if (lt != -1) {
            burningTreeNode(root, lt, root.left, ans);
            return lt + 1;
        }

        int rt = burningTree(root.right, fireNode, ans);
        if (rt != -1) {
            burningTreeNode(root, rt, root.right, ans);
            return rt + 1;
        }

        return -1;
    }

    // LEETCODE Q863. All Nodes Distance K in Binary Tree

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
        nodeToRootPath_(root, target.val, path); // give path till target
        List<Integer> ans = new ArrayList<>();
        TreeNode block = null;
        for (int i = 0; i < path.size(); i++) {
            kdown(path.get(i), k - i, block, ans);
            block = path.get(i); // block wherever we iterate
        }
        return ans; // give path till k calls
    }

}
