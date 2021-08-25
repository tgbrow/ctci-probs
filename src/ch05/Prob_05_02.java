package ch05;

public class Prob_05_02 {

    public static void main(String[] args) {
        double[] testData = {
            0.625,
            0.5,
            0.822265625,
            1 - Math.pow(2, -32),
            1 - Math.pow(2, -33),
            -0.5,
            0,
            1.2,
        };
        for (double d : testData) {
            System.out.printf("%s => %s\n", d, binaryString(d));
        }
    }

    // Maximum number of characters to allow past the decimal point.
    private static final int MAX_CHARS = 32;
    private static final int BIN_BASE = 2;
    private static final String ERR_STR = "ERROR";

    private static String binaryString(double d) {
        if (d <= 0 || d >= 1) return ERR_STR;

        String str = "0.";
        for (int n = 1; d > 0; ++n) {
            if (n > MAX_CHARS) return ERR_STR;
            d *= BIN_BASE;
            str += (int) d;
            d -= (int) d;
        }

        return str;
    }
    
}
