package ch05;

import static common.Utility.bitStrCompact;

public class Prob_05_03 {

    public static void main(String[] args) {
        int[] testData = {
            0,
            1,
            0b11,
            0b100011,
            0b101,
            0b1101,
            1775,
            -2345,
            -1,
        };
        for (int x : testData) {
            System.out.println(bitStrCompact(x) + " => " + oneRunExplicit(x));
            System.out.println(bitStrCompact(x) + " => " + oneRunCompact(x));
        }
    }

    private static int oneRunCompact(int x) {
        // Note: These checks are optional / only for purpose of optimization.
        if (x == 0) return 0;
        if (x == ~0) return 8 * Integer.BYTES;

        int maxOnes = 0;
        int currOnes = 0;
        int addOnes = 0;

        while (x != 0) {
            if ((x & 1) == 1) {
                ++currOnes;
            } else {
                addOnes = (x & 2) == 0 ? 0 : currOnes + 1;
                currOnes = 0;
            }
            maxOnes = Math.max(addOnes + currOnes, maxOnes);
            x >>>= 1;
        }
        return maxOnes;
    }

    private static int oneRunExplicit(int x) {
        // Note: These checks are optional / only for purpose of optimization.
        if (x == 0) return 0;
        if (x == ~0) return 8 * Integer.BYTES;

        int longest = 0;
        int prevDigit = 0;
        int currOnes = 0;
        int prevOnes = 0;
        boolean canCombine = false;

        while (x != 0) {
            int digit = x & 1;

            if (prevDigit == 0 && digit == 0) {
                // In a run of zeros.
                canCombine = false;
            }

            if (prevDigit == 0 && digit == 1) {
                // Start of a run of ones.
                prevOnes = currOnes;
                currOnes = 1;
            }

            if (prevDigit == 1 && digit == 0) {
                // Just ended a run of ones.
                longest = updateLongest(longest, currOnes, canCombine, prevOnes);
                canCombine = true;
            }

            if (prevDigit == 1 && digit == 1) {
                // In a run of ones.
                ++currOnes;
            }

            prevDigit = digit;
            x >>>= 1;
        }

        // We know the final digit was a one (given the "while" condition), so we must do a final update.
        return updateLongest(longest, currOnes, canCombine, prevOnes);
    }

    private static int updateLongest(int longest, int currOnes, boolean combine, int prevOnes) {
        return Math.max(longest, currOnes + (combine ? prevOnes + 1 : 0));
    }
    
}
