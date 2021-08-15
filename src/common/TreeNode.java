package common;

public class TreeNode {
    public int data;
    public TreeNode left;
    public TreeNode right;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb, -1);
        return sb.toString();
    }

    private void toString(StringBuilder sb, int depth) {
        sb.append(String.format(
            "v: %d, l: %s, r: %s%s\n",
            data,
            left  == null ? "null" :  left.data,
            right == null ? "null" : right.data,
            depth == -1 ? "" : ", d: " + depth));
    }

    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        toStringInOrder(sb, 0);
        return sb.toString();
    }

    private void toStringInOrder(StringBuilder sb, int depth) {
        if (left != null) left.toStringInOrder(sb, depth + 1);
        toString(sb, depth);
        if (right != null) right.toStringInOrder(sb, depth + 1);
    }
}
