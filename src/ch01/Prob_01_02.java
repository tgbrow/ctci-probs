package ch01;

public class Prob_01_02 {
    public static final int CHARS = 256;

    public static void main(String[] args) {
        String prime = "Test one";
        String[] strs = { "one Test", "eost Tne", "eost one"};
        for (String str : strs) {
            boolean isPerm = isPermutation(prime, str);
            System.out.printf("\"%s\": %s\n", str, isPerm);
        }  
    }

    public static boolean isPermutation(String a, String b) {
        if (a.length() != b.length()) return false;

        int[] counts = new int[CHARS];

        for (char c : a.toCharArray()) {
            counts[c] += 1;
        }

        for (char c : b.toCharArray()) {
            counts[c] -= 1;
        }

        for (int cnt : counts) {
            if (cnt != 0) return false;
        }

        return true;
    }
}