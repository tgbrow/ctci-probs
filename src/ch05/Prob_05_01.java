package ch05;

import static common.BitUtility.bitStrCompact;

public class Prob_05_01 {

    public static void main(String[] args) {
        int n = 0b10101001;
        int m =   0b1111;
        int i =        2;
        int j =     5;
        System.out.println(bitStrCompact(insertBits(n, m, i, j)));

        n = 0b10000000000;
        m =     0b10011;
        i =           2;
        j =       6;
        System.out.println(bitStrCompact(insertBits(n, m, i, j)));
    }
    
    private static int insertBits(int n, int m, int i, int j) {
        int mask = ~0 << (j - i);
        mask = ~mask << i;
        mask = ~mask;
        return (n & mask) | (m << i);
    }
    
}
