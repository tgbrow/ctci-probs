package ch01;

public class Prob_01_03 {

    public static void main(String[] args) throws Exception {

        char[] str = "Mr John Smith    ".toCharArray();
        urlify(str, 13);
        System.out.printf("res: \"%s\"\n", String.valueOf(str));

    }

    public static void urlify(char[] str, int len) throws Exception {
        System.out.printf("\n\nurlify(\"%s\", %d)\n", String.valueOf(str), len);


        if (len < 0) {
            throw new Exception("invalid length");
        }
    
        // count spaces
        int numSpaces = 0;
        for (int i = 0; i < len; ++i) {
            if (str[i] == ' ') {
                ++numSpaces;
            }
        }
        System.out.printf("numSpaces: %d\n", numSpaces);
    
        for (int i = len-1; numSpaces > 0; --i) {
            int offset = numSpaces * 2;
    
            if (str[i] == ' ') {
                str[i + offset    ] = '0';
                str[i + offset - 1] = '2';
                str[i + offset - 2] = '%';
                --numSpaces;
            } else {
                str[i + offset] = str[i];
            }
            System.out.printf("str: \"%s\"\n", String.valueOf(str));
        }
    }

}