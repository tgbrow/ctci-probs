package ch04;

import common.GraphNode;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Prob_04_01 {

    public static void main(String[] args) {
        GraphNode n1 = new GraphNode();
        GraphNode n2 = new GraphNode();
        GraphNode n3 = new GraphNode();
        GraphNode n4 = new GraphNode();
        GraphNode n5 = new GraphNode();
        n1.addAdjacent(n2);
        n2.addAdjacent(n3);
        n3.addAdjacent(n1);
        n3.addAdjacent(n4);
        n3.addAdjacent(n5);
        System.out.println("n1 to n3: " + pathExists(n1, n3));
        System.out.println("n1 to n5: " + pathExists(n1, n5));
        System.out.println("n2 to n5: " + pathExists(n2, n5));
        System.out.println("n2 to n1: " + pathExists(n2, n1));
        System.out.println("n4 to n1: " + pathExists(n4, n1));
        System.out.println("n5 to n4: " + pathExists(n5, n4));
    }

    // ASSUMPTION: start and target exist in g
    private static boolean pathExists(GraphNode start, GraphNode target) {
        if (start == null || target == null) return false;
        if (start == target) return true;

        Queue<GraphNode> q = new LinkedList<>();
        Set<GraphNode> seen = new HashSet<>();
        q.add(start);
        seen.add(start);

        while (!q.isEmpty()) {
            GraphNode n = q.remove();
            for (GraphNode adj : n.getAdjacent()) {
                if (!seen.contains(adj)) {
                    if (adj == target) return true;
                    q.add(adj);
                    seen.add(adj);
                }
            }
        }
        return false;
    }
    
}
