package Questions_Sheet;

import java.util.*;
import java.util.LinkedList;

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

    // LEETCODE 437. Path Sum III

    public int pathSumm(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        int res = 0;
        if (root.val == targetSum)
            res++;
        res += pathSumm(root.left, targetSum - root.val);
        res += pathSumm(root.right, targetSum - root.val);
        return res;
    }

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null)
            return 0;
        return pathSum(root.left, targetSum) + pathSumm(root, targetSum) + pathSum(root, right, targetSum);
    }

    // LEETCODE 889. Construct Binary Tree from Preorder and Postorder Traversal

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        return createTree(preorder, postorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode createTree(int[] preorder, int[] post, int prs, int pre, int pos, int poe) {
        if (prs > pre) {
            return null;
        }
        if (prs == pre) {
            return new TreeNode(preorder[prs]);
        }
        TreeNode node = new TreeNode(preorder[prs]);
        int val = preorder[prs + 1];
        int idx = -1;
        for (int i = pos; i <= poe; i++) {
            if (post[i] == val) {
                idx = i;
                break;
            }
        }
        // colse - count of left subtree elements
        int colse = idx - pos + 1;
        node.left = createTree(preorder, post, prs + 1, prs + colse, pos, idx);
        node.right = createTree(preorder, post, prs + colse + 1, pre, idx + 1, poe - 1);
        return node;
    }

    // LEETCODE 96. Unique Binary Search Trees

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    // LEETCODE 99. Recover Binary Search Tree

    TreeNode firstPtr = null;
    TreeNode lastPtr = null;
    TreeNode prevPtr = null;

    public void recoverTree(TreeNode root) {
        inOrderTraversal(root);

        int temp = firstPtr.val;
        firstPtr.val = lastPtr.val;
        lastPtr.val = temp;
    }

    private void inOrderTraversal(TreeNode root) {
        if (root.left != null) {
            inOrderTraversal(root.left);
        }

        if (prevPtr != null) {
            if (prevPtr.val >= root.val) {
                if (firstPtr == null) {
                    firstPtr = prevPtr;
                }
                lastPtr = root;
            }
        }

        prevPtr = root;

        if (root.right != null) {
            inOrderTraversal(root.right);
        }
    }

    // LEETCODE 116. Populating Next Right Pointers in Each Node

    public Node connect(Node root) {
        if (root == null)
            return root;

        Node curr = root;
        while (curr != null) {
            Node head = new Node();
            Node prev = head;

            while (curr != null) {
                if (curr.left != null) {
                    prev.next = curr.left;
                    prev = prev.next;
                }
                if (curr.right != null) {
                    prev.next = curr.right;
                    prev = prev.next;
                }
                curr = curr.next;
            }
            curr = head.next;
        }
        return root;

    }

    // LEETCODE 114. Flatten Binary Tree to Linked List

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        if (root.left != null) {
            TreeNode leftRightMost = root.left;
            while (leftRightMost.right != null)
                leftRightMost = leftRightMost.right;
            TreeNode rootRight = root.right;
            root.right = root.left;
            leftRightMost.right = rootRight;
            root.left = null;
        }
        flatten(root.right);
    }

    // LEETCODE 662. Maximum Width of Binary Tree

    class Pair {
        TreeNode node;
        int index;

        Pair(TreeNode node, int index) {
            this.index = index;
            this.node = node;
        }
    }

    class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            Queue<Pair> q = new LinkedList<>();
            int ans = Integer.MIN_VALUE;
            q.add(new Pair(root, 0));
            while (q.isEmpty() == false) {
                int size = q.size();
                int minSizeOfLevel = q.peek().index;
                for (int i = 0; i < size; i++) {
                    Pair p = q.poll();
                    int id = p.index - minSizeOfLevel;
                    if (p.node.left != null)
                        q.offer(new Pair(p.node.left, (2 * id + 1)));
                    if (p.node.right != null)
                        q.offer(new Pair(p.node.right, (2 * id + 2)));
                    if (i == size - 1) {
                        ans = Math.max((p.index - minSizeOfLevel) + 1, ans);
                    }
                }
            }
            return ans;
        }
    }

    // LEETCODE 230. Kth Smallest Element in a BST

    private int count = 0;
    private int ans = 0;

    private void helper(TreeNode root, int k) {
        if (root == null)
            return;
        helper(root.left, k);
        ans++;
        if (ans == k) {
            count = root.val;
            return;
        }
        helper(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return count;
    }

    // LEETCODE 103. Binary Tree Zigzag Level Order Traversal

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null)
            return ans;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int level = 0;
        while (!q.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>();
            int length = q.size();
            for (int i = 0; i < length; i++) {
                TreeNode node = q.poll();
                if (level % 2 == 0)
                    temp.add(node.val);
                else
                    temp.addFirst(node.val);
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);

            }
            ans.add(temp);
            level++;
        }
        return ans;
    }

    // GFG Min distance between two given nodes of a Binary Tree

    class GfG {
        Node LCA(Node root, int n1, int n2) {
            if (root == null)
                return null;

            if (root.data == n1 || root.data == n2)
                return root;

            Node l = LCA(root.left, n1, n2);
            Node r = LCA(root.right, n1, n2);

            if (l != null && r != null)
                return root;

            if (l == null && r == null)
                return null;

            return (l != null) ? l : r;
        }

        int findDist(Node root, int a, int b) {
            int x = pathLength(root, a) - 1;
            int y = pathLength(root, b) - 1;

            int lcadata = LCA(root, a, b).data;
            int lcaDistance = pathLength(root, lcadata) - 1;
            return (x + y) - 2 * lcaDistance;
        }

        int pathLength(Node root, int n1) {
            if (root == null)
                return 0;
            else {
                int x = 0;
                if ((root.data == n1) || (x = pathLength(root.left, n1)) > 0 ||
                        (x = pathLength(root.right, n1)) > 0)
                    return x + 1;
            }

            return 0;
        }
    }

    // GFG Count BST nodes that lie in a given range

    class Solution {

        int getCount(Node node, int low, int high) {
            if (node == null)
                return 0;

            if (node.data == high && node.data == low)
                return 1;

            if (node.data >= low && node.data <= high)
                return 1 + this.getCount(node.left, low, high) +
                        this.getCount(node.right, low, high);

            else if (node.data < low)
                return this.getCount(node.right, low, high);

            else
                return this.getCount(node.left, low, high);
        }
    }

    // GFG Preorder to Postorder

    public static Node post_order(int pre[], int size) {
        int[] in = Arrays.copyOf(pre, pre.length);
        Arrays.sort(in);
        int[] idx = { 0 };
        Node head = createbst(in, pre, 0, size - 1, idx);
        return head;
    }

    public static Node createbst(int[] in, int[] pre, int i, int j, int[] idx) {
        if (i > j) {
            return null;
        }
        Node temp = new Node(pre[idx[0]]);
        int t = find(in, temp.data, i, j);
        int nw = idx[0];
        idx[0] = nw + 1;
        temp.left = createbst(in, pre, i, t - 1, idx);
        temp.right = createbst(in, pre, t + 1, j, idx);
        return temp;
    }

    public static int find(int[] in, int x, int i, int j) {

        for (int k = i; k <= j; k++) {
            if (in[k] == x) {
                return k;
            }
        }
        return -1;
    }

}
