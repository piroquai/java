import java.util.Scanner;

public class Fibonacci {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter N:");
        //Nth fibonacci number
        int N = in.nextInt();
        if (N == 1 || N == 2) {
            System.out.println("Nth Fibonacci number is " + 1);
        } else {
            int fibo3 = 0;
            int fibo1 = 1;
            int fibo2 = 1;
            for (int i = 2; i <= N; i++) {
                fibo3 = fibo2 + fibo1;
                fibo1 = fibo2;
                fibo2 = fibo3;
                System.out.println( fibo3);
            }

        }
    }
}
