package Trees;

import LinkedList.LinkedList;

public class ViewSet {

    public static class TreeNode {

        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static void levelorderLineWise(TreeNode root) {

        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        int level = 0;

        while (que.size() != 0) {
            int size = que.size();
            System.out.println("Level " + level + ": ");
            while (size-- > 0) {
                TreeNode rn = que.removeFirst();

                System.out.println(rn.val + ", ");
                ;
                if (rn.left != null)
                    que.addLast(rn.left);
                if (rn.right != null)
                    que.addLast(rn.right);
            }
            level++;
        }

    }
}
