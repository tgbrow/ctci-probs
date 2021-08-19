package ch04;

import common.TreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Prob_04_09 {

    public static void main(String[] args) {
        TreeNode n07 = new TreeNode( 7);
        TreeNode n08 = new TreeNode( 8);
        TreeNode n10 = new TreeNode(10);
        TreeNode n15 = new TreeNode(15);
        TreeNode n20 = new TreeNode(20);
        TreeNode n30 = new TreeNode(30);
        TreeNode n40 = new TreeNode(40);
        TreeNode n50 = new TreeNode(50);
        TreeNode n55 = new TreeNode(55);
        TreeNode n60 = new TreeNode(60);

        // depth 0 -> 1
        n30.left  = n15;
        n30.right = n50;
        // depth 1 -> 2
        n15.left  = n08;
        n15.right = n20;
        n50.left  = n40;
        n50.right = n60;
        // depth 2 -> 3
        n08.left  = n07;
        n08.right = n10;
        n60.left  = n55;

        printResults(printBstSequences(n30));
    }

    private static ArrayList<LinkedList<TreeNode>> printBstSequences(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> results = new ArrayList<>();
        if (root == null) return results;
        LinkedList<TreeNode> seq = new LinkedList<>();
        Set<TreeNode> choices = new HashSet<>();
        choices.add(root);
        helper(seq, choices, results);
        return results;
    }

    private static void helper(LinkedList<TreeNode> seq, Set<TreeNode> choices, ArrayList<LinkedList<TreeNode>> results) {
        if (choices.isEmpty()) {
            results.add(new LinkedList<>(seq));
            return;
        }

        TreeNode[] choicesCopy = choices.toArray(TreeNode[]::new);
        for (TreeNode n : choicesCopy) {
            choices.remove(n);
            if (n.left  != null) choices.add(n.left);
            if (n.right != null) choices.add(n.right);
            seq.add(n);
            helper(seq, choices, results);
            seq.remove(n);
            if (n.left  != null) choices.remove(n.left);
            if (n.right != null) choices.remove(n.right);
            choices.add(n);
        }
    }

    private static void printResults(ArrayList<LinkedList<TreeNode>> results) {
        StringBuilder sb = new StringBuilder();
        for (LinkedList<TreeNode> seq : results) {
            for (TreeNode n : seq) {
                sb.append(n.data + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
    
}
