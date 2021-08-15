package ch04;

import common.TreeNode;

public class Prob_04_05 {

    public static void main(String[] args) {
        TreeNode n30a = new TreeNode(30);
        TreeNode n30b = new TreeNode(30);
        TreeNode n40  = new TreeNode(40);
        TreeNode n50  = new TreeNode(50);
        TreeNode n55  = new TreeNode(55);
        TreeNode n60  = new TreeNode(60);
        // depth 0 to 1
        n50.left = n40;
        n50.right = n60;
        // depth 1 to 2
        n40.left = n30a;
        n60.left = n55;
        // depth 2 to 3
        n30a.left = n30b;
        System.out.println("isBst: " + isBst(n50));
    }

    private static boolean isBst(TreeNode root) {
        return isBstHelper(root, null, null);
    }

    private static boolean isBstHelper(TreeNode n, Integer min, Integer max) {
        if (n == null) {
            return true;
        }
        if ((min != null && n.data <= min) || (max != null && n.data > max)) {
            return false;
        }
        return isBstHelper(n.left, min, n.data) && isBstHelper(n.right, n.data, max);
    }
    
}
