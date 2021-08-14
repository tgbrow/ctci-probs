public class Prob_04_02 {

    public static void main(String[] args) {
        int[] testData = {0, 1, 2, 3, 4, 5, 6, 7};
        TreeNode minBstRoot = buildMinBst(testData);
        System.out.println(minBstRoot.toStringInOrder());
    }

    private static TreeNode buildMinBst(int[] sorted) {
        return minBstHelper(sorted, 0, sorted.length - 1);
    }

    // sorted: must be sorted min to max
    // min: the left-most index to consider in the array (inclusive)
    // max: the right-most index to consider in the array (inclusive)
    private static TreeNode minBstHelper(int[] sorted, int min, int max) {
        if (min > max) return null;
        int mid = (min + max) / 2;
        TreeNode root = new TreeNode();
        root.data = sorted[mid];
        root.left = minBstHelper(sorted, min, mid - 1);
        root.right = minBstHelper(sorted, mid + 1, max);
        return root;
    }
}
