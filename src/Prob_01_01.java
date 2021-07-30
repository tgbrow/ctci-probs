import java.util.Set;
import java.util.HashSet;

public class Prob_01_01 {
   
    public static void main(String[] args) throws Exception {
        String[] strs = { "Test", "test", "", "yaris"};
        for (String str : strs) {
            boolean areUnique = charactersAreUnique(str);
            boolean areUniqueBeta = charactersAreUniqueBeta(str);
            System.out.printf("\"%s\": %s, %s\n", str, areUnique, areUniqueBeta);
        }  
    }

    // version using hash set
    public static boolean charactersAreUnique(String str) {
        Set<Character> seen = new HashSet<Character>();
        for (char c : str.toCharArray()) {
            if (!seen.add(c)) {
                return false;
            }
        }
        return true;
    }

    // version without auxiliary data structure
    public static boolean charactersAreUniqueBeta(String str) {
        for (int i = 0; i < str.length() - 1; i++) {
            char c = str.charAt(i);
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(j) == c) return false;
            }
        }
        return true;
    }

}

