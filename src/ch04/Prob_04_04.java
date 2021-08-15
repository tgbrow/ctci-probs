package ch04;

import common.TreeNode;

public class Prob_04_04 {
    
    public static void main(String[] args) {
        TreeNode[] n = new TreeNode[100];
        for (int i = 0; i < n.length; ++i) {
            n[i] = new TreeNode(i);
        }
        n[0].left = n[1];
        n[1].left = n[2];
        n[2].left = n[3];
        n[3].left = n[4];
        n[0].right = n[5];
        n[5].left = n[6];
        n[5].right = n[7];

        System.out.println("isBal: " + isBalanced(n[0]));
    }

    private static boolean isBalanced(TreeNode root) {
        return getTreeStatsAbortIfUnbalanced(root).isBalanced;
    }

    private static TreeStats getTreeStatsAbortIfUnbalanced(TreeNode n) {
        if (n == null) return new TreeStats(0, true);

        TreeStats left  = getTreeStatsAbortIfUnbalanced(n.left);
        if (!left.isBalanced) return new TreeStats(-1, false);

        TreeStats right = getTreeStatsAbortIfUnbalanced(n.right);
        if (!right.isBalanced) return new TreeStats(-1, false);

        return new TreeStats(left, right);
    }

    private static class TreeStats {
        boolean isBalanced;
        int height;

        public TreeStats(int height, boolean isBalanced) {
            this.height = height;
            this.isBalanced = isBalanced;
        }

        public TreeStats(TreeStats left, TreeStats right) {
            this.isBalanced =
                left.isBalanced
                && right.isBalanced
                && (Math.abs(left.height - right.height) < 2);
            this.height = Math.max(left.height, right.height) + 1;
        }
    }
    
}
