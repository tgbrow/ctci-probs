package common;

public class BitUtility {

    public static final int BYTE_BITS = 8;
    public static final int INT_BITS  = Integer.BYTES * BYTE_BITS;

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

    public static int getBit(int x, int bitIdx) {
        if (!isValidIntBitIdx(bitIdx)) {
            throw new IllegalArgumentException("Invalid bit index!");
        }
        return (x >> bitIdx) & 1;
    }

    public static int makeBitZero(int x, int bitIdx) {
        return setBit(x, bitIdx, 0);
    }

    public static int makeBitOne(int x, int bitIdx) {
        return setBit(x, bitIdx, 1);
    }

    private static int setBit(int x, int bitIdx, int value) {
        if (!isValidIntBitIdx(bitIdx)) {
            throw new IllegalArgumentException("Invalid bit index!");
        }
        if (value != 0 && value != 1) {
            throw new IllegalArgumentException("Invalid bit value!");
        }
        int mask = 1 << bitIdx;
        return value == 0 ? x ^ mask : x | mask;
    }

    private static boolean isValidIntBitIdx(int bitIdx) {
        return bitIdx >= 0 && bitIdx < INT_BITS;
    }

}
