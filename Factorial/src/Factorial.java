import java.util.InputMismatchException;
import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter N: ");
            int N = in.nextInt();
            if (N < 0) {
                throw new IllegalArgumentException();
            }
            System.out.println(factorial(N));
        } catch (InputMismatchException e) {
            System.out.println("Value is not integer");
        }
    }
    public static int factorial(int N) {
        int F = 1;
        for (int i = 1; i <= N; i++)
            F *= i;
        return (F);
    }
}
