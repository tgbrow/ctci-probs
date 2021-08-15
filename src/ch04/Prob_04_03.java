package ch04;

import common.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;

public class Prob_04_03 {

    public static void main(String[] args) {
        int[] testData = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode root = Prob_04_02.buildMinBst(testData);
        ArrayList<LinkedList<Integer>> dls = depthLists(root);
        System.out.println(toString(dls));
    }

    private static ArrayList<LinkedList<Integer>> depthLists(TreeNode root) {
        ArrayList<LinkedList<Integer>> lists = new ArrayList<>();
        depthListsHelper(root, lists, 0);
        return lists;
    }

    private static void depthListsHelper(TreeNode n, ArrayList<LinkedList<Integer>> lists, int depth) {
        if (n == null) return;

        LinkedList<Integer> depthList;
        if (lists.size() - 1 < depth) {
            depthList = new LinkedList<>();
            lists.add(depthList);
        } else {
            depthList = lists.get(depth);
        }
        depthList.add(n.data);

        depthListsHelper(n.left,  lists, depth + 1);
        depthListsHelper(n.right, lists, depth + 1);
    }

    private static String toString(ArrayList<LinkedList<Integer>> x) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < x.size(); ++i) {
            LinkedList<Integer> list = x.get(i);
            sb.append(i + ": ");
            for (int d : list) {
                sb.append(d + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
