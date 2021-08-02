import java.util.Set;
import java.util.HashSet;

public class Prob_01_08 {

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {1, 2, 0, 4, 5},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 0},
        };
        printMatrix(matrix);
        zero(matrix);
        printMatrix(matrix);
    }
    
    public static void zero(int[][] matrix) {
        Set<Integer> zeroRows = new HashSet<Integer>();
        Set<Integer> zeroColumns = new HashSet<Integer>();

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] == 0) {
                    zeroRows.add(i);
                    zeroColumns.add(j);
                }
            }
        }

        for (int row : zeroRows) {
            matrix[row] = new int[matrix[row].length];
        }

        for (int col : zeroColumns) {
            for (int i = 0; i < matrix.length; ++i) {
                matrix[i][col] = 0;
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
            for (int i : row) {
                sb.append(i);
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
