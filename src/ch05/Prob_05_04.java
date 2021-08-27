package ch05;

import static common.BitUtility.INT_BITS;
import static common.BitUtility.bitStrCompact;
import static common.BitUtility.getBit;
import static common.BitUtility.makeBitOne;

public class Prob_05_04 {

    private static final int MAX_TEST_VAL = 123_456_789;
    private static final int MILLION = 1_000_000;
    private static final int MSG_INTERVAL = 5 * MILLION;

    public static void main(String[] args) {
        for (int i = 0; i <= MAX_TEST_VAL; ++i) {
            int res = findSameOnesLarger(i);
            int bruteRes = bruteForceLarger(i);
            if (res != bruteRes) {
                printDiff(i, res, bruteRes, true);
                return;
            }

            res = findSameOnesSmaller(i);
            bruteRes = bruteForceSmaller(i);
            if (res != bruteRes) {
                printDiff(i, res, bruteRes, false);
                return;
            }

            if (i % MSG_INTERVAL == 0 && i != 0) {
                System.out.println((i / MILLION) + " million cases completed\n...");
            }
        }
        System.out.println("All " + MAX_TEST_VAL + " cases completed successfully!");
    }

    private static void printDiff(int x, int actual, int expected, boolean larger) {
        System.out.println("Diff detected for " + (larger ? "LARGER" : "SMALLER") + " case:");
        System.out.println("    actual: " + bitStrCompact(x) + " => " + bitStrCompact(actual));
        System.out.println("  expected: " + bitStrCompact(x) + " => " + bitStrCompact(expected));
        System.out.println();
    }

    private static int findSameOnesSmaller(int x) {
        // Only handle positive inputs.
        if (x < 1) return 0;
        // If input (sans leading 0s) is composed of all 1s, there is no solution.
        if (allOnes(x)) return 0;

        // Iterate (from LSB to MSB) until we reach a 0.
        // numOnes will be the number of trailing 1s.
        int i = 0;
        while (i < INT_BITS && getBit(x, i) == 1) {
            ++i;
        }
        int numOnes = i;

        // Iterate past any 0s until we reach the next 1.
        while (i < INT_BITS && getBit(x, i) == 0) {
            ++i;
        }
        // The bit at i is a 1. Count it.
        ++numOnes;

        // The idea now is to turn the "10" (starting at i) into "01". Then, arrange the remaining
        // bits to the right to make as large a number as possible while preserving the 1s count,
        // i.e. place the 1s as far left as possible.
        // In other words, we want the bits from i rightward to look like: 0{numOnes 1s}{0s through i=0}

        // To do this, we first clear all bits from i rightward.
        int result = x & (~0 << (i+1));
        // Then fill in the most-significant bits to the right of bit i with as many 1s as needed.
        return result | ~(~0 << numOnes) << (i - numOnes);
    }

    private static int findSameOnesLarger(int x) {
        // Only handle positive inputs.
        if (x < 1) return 0;

        // Iterate (from LSB to MSB) until we reach a 1.
        int i = 0;
        while (i < INT_BITS && getBit(x, i) == 0) {
            ++i;
        }

        // Iterate past any 1s until we reach the next 0.
        int numOnes = 0;
        while (i < INT_BITS && getBit(x, i) == 1) {
            ++numOnes;
            ++i;
        }

        // The idea now is to turn the "01" (starting at i) into "10". Then, arrange the remaining
        // bits to the right to make as small a number as possible while preserving the 1s count,
        // i.e. place the 1s as far right as possible.
        // In other words, we want the bits from i rightward to look like: 1{trailingZeros 0s}{1s through i=0}

        // To do this, first set bit i to 1.
        int result = makeBitOne(x, i);
        // Next, clear all bits to the right of bit i.
        result &= (~0 << i);
        // Then fill in the least-significant bits with as many 1s as needed.
        return result | ~(~0 << (numOnes - 1));
    }

    private static int bruteForceSmaller(int x) {
        if (x < 1 || allOnes(x)) return 0;
        int ones = countOnes(x);
        while (x > 0) {
            x -= 1;
            if (countOnes(x) == ones) {
                return x;
            }
        }
        // This should be unreachable.
        return 0;
    }

    private static int bruteForceLarger(int x) {
        if (x < 1) return 0;
        int ones = countOnes(x);
        while (true) {
            x += 1;
            if (countOnes(x) == ones) {
                return x;
            }
        }
    }

    private static int countOnes(int x) {
        int oneCnt = 0;
        while (x != 0) {
            if ((x & 1) == 1) {
                ++oneCnt;
            }
            x >>>= 1;
        }
        return oneCnt;
    }

    private static int firstZeroIdx(int x) {
        for (int i = 0; i < INT_BITS; ++i) {
            if ((x & 1) == 0) {
                return i;
            }
            x >>>= 1;
        }
        return -1;
    }

    private static boolean allOnes(int x) {
        return x != 0 && countOnes(x) == firstZeroIdx(x);
    }
    
}
