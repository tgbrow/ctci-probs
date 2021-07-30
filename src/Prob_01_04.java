import java.util.Map;
import java.util.HashMap;

public class Prob_01_04 {

    public static void main(String[] args) throws Exception {
        String str = "Taco Cat";
        System.out.printf("%s: %s", str, isPalinPerm(str));
    }

    public static boolean isPalinPerm(String str) {
        String s = str.replaceAll("\\s", "").toLowerCase();
    
        Map<Character, Integer> charCnts = new HashMap<Character, Integer>();
        for (char c : s.toCharArray()) {
            charCnts.put(c, charCnts.getOrDefault(c, 0) + 1);
        }
    
        int oddCnts = 0;
        for (Map.Entry<Character, Integer> e : charCnts.entrySet()) {
            if (e.getValue() % 2 != 0 && ++oddCnts > 1) {
                return false;
            }
        }
        return true;
    }

}