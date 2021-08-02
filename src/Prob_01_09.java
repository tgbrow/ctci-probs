public class Prob_01_09 {
    
    public static void main(String[] args) {
        String s1 = "testing";
        String s2 = "tingtes";
        System.out.printf("%s, %s: %s\n", s1, s1, isRotation(s1, s2));

        s1 = "testing";
        s2 = "tingtez";
        System.out.printf("%s, %s: %s\n", s1, s1, isRotation(s1, s2));
    }

    public static boolean isRotation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        String s1Double = s1 + s1;
        return isSubstring(s2, s1Double);
    }

    // The problem lets us assume this method is provided but that we can only call it once.
    public static boolean isSubstring(String sub, String str) {
        return str.contains(sub);
    }

}
