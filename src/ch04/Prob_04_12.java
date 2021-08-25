package ch04;

import common.TreeNode;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Prob_04_12 {

    public static void main(String[] args) {
        TreeNode n02  = new TreeNode(-2);
        TreeNode n03  = new TreeNode(-3);
        TreeNode p01  = new TreeNode( 1);
        TreeNode p02  = new TreeNode( 2);
        TreeNode p03a = new TreeNode( 3);
        TreeNode p03b = new TreeNode( 3);
        TreeNode p05  = new TreeNode( 5);
        TreeNode p10  = new TreeNode(10);
        TreeNode p11  = new TreeNode(11);

        // depth 0 -> 1
        p10.left   = p05;
        p10.right  = n03;
        // depth 1 -> 2
        p05.left   = p03a;
        p05.right  = p01;
        n03.right  = p11;
        // depth 2 -> 3
        p03a.left  = p03b;
        p03a.right = n02;
        p01.right  = p02;

        printSumPaths(findSumPaths(p10, 8));
    }

    private static ArrayList<LinkedList<TreeNode>> findSumPaths(TreeNode root, int sum) {
        Map<TreeNode, MinMax> map = new HashMap<>();
        buildMinMax(root, map);
        ArrayList<LinkedList<TreeNode>> paths = new ArrayList<>();
        for (Map.Entry<TreeNode, MinMax> entry : map.entrySet()) {
            MinMax mm = entry.getValue();
            if (mm.min <= sum && sum <= mm.max) {
                findSumPaths(entry.getKey(), sum, map, paths);
            }
        }
        return paths;
    }

    private static void findSumPaths(TreeNode n, int sum, Map<TreeNode, MinMax> minMaxMap, ArrayList<LinkedList<TreeNode>> paths) {
        if (n == null) return;
        int rem = sum - n.data;
        if (rem == 0) {
            LinkedList<TreeNode> path = new LinkedList<TreeNode>();
            path.add(n);
            paths.add(path);
        }
        childPaths(n, n.left,  rem, minMaxMap, paths);
        childPaths(n, n.right, rem, minMaxMap, paths);
    }

    private static void childPaths(TreeNode n, TreeNode nChild, int rem, Map<TreeNode, MinMax> minMaxMap, ArrayList<LinkedList<TreeNode>> paths) {
        if (nChild != null) {
            MinMax childMM = minMaxMap.get(nChild);
            if (childMM.min <= rem && rem <= childMM.max) {
                ArrayList<LinkedList<TreeNode>> childPaths = new ArrayList<>();
                findSumPaths(nChild, rem, minMaxMap, childPaths);
                for (LinkedList<TreeNode> childPath : childPaths) {
                    childPath.addFirst(n);
                    paths.add(childPath);
                }
            }
        }
    }

    private static MinMax buildMinMax(TreeNode n, Map<TreeNode, MinMax> map) {
        if (n == null) return new MinMax(null, null);

        MinMax left  = buildMinMax(n.left, map);
        MinMax right = buildMinMax(n.right, map);

        int min = Math.min(left.min == null ? 0 : left.min, right.min == null ? 0 : right.min);
        min = Math.min(n.data, n.data + min);
        int max = Math.max(left.max == null ? 0 : left.max, right.max == null ? 0 : right.max);
        max = Math.max(n.data, n.data + max);
        MinMax curr = new MinMax(min, max);

        map.put(n, curr);
        return curr;
    }

    private static void printSumPaths(ArrayList<LinkedList<TreeNode>> paths) {
        if (paths.isEmpty()) {
            System.out.println("No paths found.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (LinkedList<TreeNode> path : paths) {
            for (TreeNode n : path) {
                sb.append(n.data + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static class MinMax {
        Integer min;
        Integer max;

        public MinMax(Integer min, Integer max) {
            this.min = min;
            this.max = max;
        }
    }
    
}
