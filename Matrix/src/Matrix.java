import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter matrix size: ");
        int matrix_size = in.nextInt();
        int[][] matrix = new int[matrix_size][matrix_size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++)
                matrix[i][j] = (int) (Math.random() * 10);
        }
        for (int i = 0; i < matrix.length; i++, System.out.println()) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
        }
        int sled = 0;
        for (int i = 0; i < matrix_size; i++)
            sled += matrix[i][i];
        System.out.println("След матрицы равен " + sled);
    }
}
