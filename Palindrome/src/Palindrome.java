import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner in = new
                Scanner(System.in);
        System.out.println("Enter a 5-digit number: ");
        int number = in.nextInt();
        if (number /10000 >=1 && number / 10000 < 10) {
            if (number % 100 == (number % 10000 - number % 1000)/100 + number / 10000)
                System.out.println("This number is a palindrome"  );
                    else
                        System.out.println("This is not palindrome");
        }
        else
            System.out.println("This is not a 5-digit number");
    }
}
