package common;

import java.util.ArrayList;

public class GraphNode {
    private ArrayList<GraphNode> adjacent;

    public GraphNode() {
        this.adjacent = new ArrayList<>();
    }

    public void addAdjacent(GraphNode n) {
        adjacent.add(n);
    }

    public ArrayList<GraphNode> getAdjacent() {
        return new ArrayList<>(adjacent);
    }
}