package ch05;

import static common.BitUtility.BYTE_BITS;

public class Prob_05_08 {

    public static void main(String[] args) {
        byte[] testData = {
            (byte) 0b0000_1111, (byte) 0b0000_1111, (byte) 0b0000_1111,
            (byte) 0b1111_0000, (byte) 0b1111_0000, (byte) 0b1111_0000,
            (byte) 0b1010_1010, (byte) 0b1010_1010, (byte) 0b1010_1010,
            (byte) 0b1111_0000, (byte) 0b1111_0000, (byte) 0b1111_0000,
            (byte) 0b0000_1111, (byte) 0b0000_1111, (byte) 0b0000_1111,
        };
        byte[] zeroData = new byte[testData.length];

        int width = 3 * BYTE_BITS;
        int x1 = 2;
        int x2 = 20;
        int y = 1;

        printScreen(testData, width);
        drawLine(zeroData, width, x1,   x2,   y);
        drawLine(zeroData, width, x1+1, x2-1, y+1);
        drawLine(zeroData, width, x1+2, x2-2, y+2);
        printScreen(zeroData, width);
        drawLine(testData, width, x1,   x2,   y);
        drawLine(testData, width, x1+1, x2-1, y+1);
        drawLine(testData, width, x1+2, x2-2, y+2);
        printScreen(testData, width);
    }

    private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        if (width < 0 || width % 8 != 0 || screen.length % (width / 8) != 0
            || x1 < 0 || x2 < 0 || x1 > width || x2 > width || x2 < x1
            || y < 0 || y * width / 8 >= screen.length) {
            throw new IllegalArgumentException("bad input");
        }

        int startBit = y * width + x1;
        int   endBit = y * width + x2;
        
        int startByte = startBit / 8;
        int   endByte =   endBit / 8;

        int startOffset = startBit % 8;
        int   endOffset =   endBit % 8;
        
        if (startByte == endByte) {
            screen[startByte] |= mask(startOffset, endOffset);
            return;
        }

        screen[startByte] |= mask(startOffset, BYTE_BITS - 1);
        for (int i = startByte + 1; i < endByte; ++i) {
            screen[i] = (byte) 0xFF;
        }
        screen[endByte] |= mask(0, endOffset);
    }

    private static byte mask(int first, int last) {
        return (byte) ((0xFF >>> first) & (0xFF << (BYTE_BITS - 1 - last)));
    }

    private static void printScreen(byte[] screen, int width) {
        StringBuilder sb = new StringBuilder();
        int widthBytes = width / 8;
        for (int i = 0; i < screen.length; ++i) {
            int x = screen[i];
            for (int j = 0; j < BYTE_BITS; ++j) {
                boolean bitIsZero = (x & (0x80 >>> j)) == 0;
                sb.append(bitIsZero ? 0 : 1);
            }
            if (i % widthBytes == widthBytes - 1) sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    
}
