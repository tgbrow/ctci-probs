package common;

public class Utility {

    public static String bitStrFull(int x) {
        return bitStr(x, false);
    }

    public static String bitStrCompact(int x) {
        return bitStr(x, true);
    }

    private static String bitStr(int x, boolean compact) {
        String str = "";
        for (int i = 0; i < 8 * Integer.BYTES; ++i) {
            str = Math.abs(x % 2) + str;
            x >>= 1;
            if (compact && x == 0) {
                break;
            }
        }
        return str;
    }
    
}
