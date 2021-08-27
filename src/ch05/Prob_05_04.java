package ch05;

import static common.BitUtility.INT_BITS;
import static common.BitUtility.bitStrCompact;
import static common.BitUtility.getBit;
import static common.BitUtility.makeBitOne;
import static common.BitUtility.makeBitZero;

public class Prob_05_04 {

    public static void main(String[] args) {
        int min = 25;
        int max = 100;
        int[] testData = new int[max - min + 1];
        for (int i = 0; i < testData.length; ++i) {
            testData[i] = min + i;
        }
        for (int x : testData) {
            int res = findSameOnesLarger(x);
            int bruteRes = bruteForceLarger(x);
            if (res != bruteRes) {
                printDiff(x, res, bruteRes, true);
                return;
            }

            res = findSameOnesSmaller(x);
            bruteRes = bruteForceSmaller(x);
            if (res != bruteRes) {
                printDiff(x, res, bruteRes, false);
                return;
            }
        }
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

        // Counts of 0s and 1s *before* the first "10" sequence.
        int zeroCnt = 0;
        int oneCnt = 0;

        for (int i = 0; i < INT_BITS; ++i) {
            int bit = getBit(x, i);
            if (bit == 0 && getBit(x, i+1) == 1) {
                // We found the first "10" sequence.
                // Swap this bit and the next, i.e. turn "10" into "01".
                int nextSmallest = makeBitZero(x, i+1);
                nextSmallest = makeBitOne(nextSmallest, i);
                // Arrange the trailing bits to make as large a number as possible, i.e. push all
                // the 1s as far left as possible.
                if (oneCnt > 0 && zeroCnt > 0) {
                    // Clear the least-significant "oneCnt + ZeroCnt" bits, then "or" in our desired suffix.
                    nextSmallest &= ~0 << (oneCnt + zeroCnt);
                    nextSmallest |= ~(~0 << oneCnt) << zeroCnt;
                }
                return nextSmallest;
            }

            if (bit == 0) {
                ++zeroCnt;
            } else { // implicit: bit == 1
                ++oneCnt;
            }
        }

        // This should be unreachable.
        return -1;
    }

    private static int findSameOnesLarger(int x) {
        // Only handle positive inputs.
        if (x < 1) return 0;

        // Counts of 0s and 1s *before* the first "01" sequence.
        int zeroCnt = 0;
        int oneCnt = 0;

        for (int i = 0; i < INT_BITS; ++i) {
            int bit = getBit(x, i);
            if (bit == 1 && getBit(x, i+1) == 0) {
                // We found the first "01" sequence.
                // Swap this bit and the next, i.e. turn "01" into "10".
                int nextLargest = makeBitOne(x, i+1);
                nextLargest = makeBitZero(nextLargest, i);
                // Arrange the trailing bits to make as small a number as possible, i.e. push all
                // the 1s as far right as possible.
                if (oneCnt > 0 && zeroCnt > 0) {
                    // Clear the least-significant "oneCnt + ZeroCnt" bits, then "or" in our desired suffix.
                    nextLargest &= ~0 << (oneCnt + zeroCnt);
                    nextLargest |= ~(~0 << oneCnt);
                }
                return nextLargest;
            }

            if (bit == 0) {
                ++zeroCnt;
            } else { // implicit: bit == 1
                ++oneCnt;
            }
        }

        // This should be unreachable.
        return -1;
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
