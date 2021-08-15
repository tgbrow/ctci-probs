package ch01;

public class Prob_01_07 {
    
    public static void main(String[] args) throws Exception {
        String[][] image = buildImage(9);
        printImage(image);
        rotate(image);
        printImage(image);
    }

    public static void rotate(String[][] image) throws Exception {
        if (!imageIsValid(image)) {
            throw new Exception("Image must be a fully non-null, NxN matrix!");
        }

        int n = image.length;
        int edgeIdx = n - 1;
        
        for (int layer = 0; layer < n/2; ++layer) {

            int minIdx = layer;
            int maxIdx = edgeIdx - layer;
    
            for (int col = minIdx; col < maxIdx; ++col) {
                String topTemp = image[minIdx][col];
                // move left into top
                image[minIdx][col] = image[edgeIdx - col][minIdx];
                // move bot into left 
                image[edgeIdx - col][minIdx] = image[maxIdx][edgeIdx - col];
                // move right into bot
                image[maxIdx][edgeIdx - col] = image[col][maxIdx];
                // move top into right
                image[col][maxIdx] = topTemp;
            }
        }
    }

    private static final int SQUARE_NUM_EDGES = 4;

    public static void rotateBeta(String[][] image) throws Exception {
        if (!imageIsValid(image)) {
            throw new Exception("Image must be a fully non-null, NxN matrix!");
        }

        int n = image.length;
        int edgeIdx = n - 1;

        for (int layer = 0; layer < n/2; ++layer) {

            for (int col = layer; col < edgeIdx - layer; ++col) {
    
                String val = image[layer][col];
                int newRow = col;
                int newCol = edgeIdx - layer;
    
                for (int edge = 0; edge < SQUARE_NUM_EDGES; ++edge) {
                    String tempVal = image[newRow][newCol];
                    image[newRow][newCol] = val;
                    int newRowTemp = newRow;
                    newRow = newCol;
                    newCol = edgeIdx - newRowTemp;
                    val = tempVal;
                }
    
            }
    
        }
    }



    public static boolean imageIsValid(String[][] image) {
        if (image == null) {
            return false;
        }

        int refLen = image.length;

        for (String[] row : image) {
            if (row == null || row.length != refLen) {
                return false;
            }
        }

        return true;
    }

    public static void printImage(String[][] image) {
        StringBuilder sb = new StringBuilder();
        for (String[] row : image) {
            for (String s : row) {
                sb.append(s);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    // only works as intended for n <= 9
    public static String[][] buildImage(int n) {
        String[][] image = new String[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                image[i][j] = (char) ('a' + i) + String.valueOf(j);
            }
        }
        return image;
    }

}
