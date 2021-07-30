public class Prob_01_06 {
    
    public static void main(String[] args) {

        String[] testStrs = {
            "aabcccccaaa",
            "aa",
            "a",
            "",
            "aabbcc",
            "aaabbbccc",
            "aaaaAA",
            "teehee",
            "taahaa",
        };

        for (String s : testStrs) {
            System.out.printf("\"%s\" => \"%s\"\n", s, compress(s));
        }

    }

    public static String compress(String str) {
        if (str.length() < 2) return str;

        StringBuilder sb = new StringBuilder();
        int charCnt = 0;
        
        for (int i = 0; i < str.length(); ++i) {
            ++charCnt;

            if (i+1 >= str.length() || str.charAt(i+1) != str.charAt(i) ) {
                sb.append(str.charAt(i));
                sb.append(charCnt);
                charCnt = 0;
            }
        }

        String compressed = sb.toString();
        return compressed.length() < str.length() ? compressed : str;
    }

}
