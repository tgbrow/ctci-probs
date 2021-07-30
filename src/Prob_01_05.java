public class Prob_01_05 {

        public static void main(String[] args) {
            System.out.println(isOneAway("pale", "ple"));
            System.out.println(isOneAway("pales", "pale"));
            System.out.println(isOneAway("pale", "bale"));
            System.out.println(isOneAway("pale", "bake"));
        }
    
        public static boolean isOneAway(String a, String b) {
            int lenDiff = Math.abs(a.length() - b.length());
            if (lenDiff > 1) {
                return false;
            } else if (lenDiff == 0) {
                return atMostOneReplacement(a, b);
            } else { // lenDiff == 1
                return atMostOneAddition(a, b);
            }
        }
        
        // pre: a and b have same length
        public static boolean atMostOneReplacement(String a, String b) {
            int diffCnt = 0;
            for (int i = 0; i < a.length(); ++i) {
                if (a.charAt(i) != b.charAt(i) && ++diffCnt > 1) {
                    return false;
                }
            }
            return true;
        }
        
        // pre: a and b lengths differ by exactly 1
        public static boolean atMostOneAddition(String a, String b) {
            String longer, shorter;
            if (a.length() > b.length()) {
                longer = a;
                shorter = b;
            } else {
                longer = b;
                shorter = a;
            }
        
            int diffCnt = 0, longIdx = 0, shortIdx = 0;
            while (longIdx < longer.length() && shortIdx < shorter.length()) {
                if (longer.charAt(longIdx) != shorter.charAt(shortIdx)) {
                    if (++diffCnt > 1) {
                        return false;
                    }
                    ++longIdx;
                } else {
                    ++shortIdx;
                    ++longIdx;
                }
            }
    
            return true;
        }
}
