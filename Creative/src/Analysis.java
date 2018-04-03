import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Analysis {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter string: ");
        String stroka = in.nextLine();
        //ASCI codes of  " ,.!?:;()"
        int[] razdeliteli = {32, 44, 46, 33, 63, 58, 59, 40, 41};
        int letters = 0;
        int words = 0;
        // auxiliary variables
        boolean n1, n2 = false, n3 = false;

        for (int i = 0; i < stroka.length(); i++) {
            int current_char = (int) stroka.charAt(i);
            if (!contains(razdeliteli, current_char))
                letters++;
            // catching word's ends, false means not letter, word is >1 char
            n1 = n2;
            n2 = n3;
            n3 = !contains(razdeliteli, current_char);
            if (n1 && n2 && !n3)
                words++;
        }
        //if string ends with a word add one more
        if (n3 && n2) words++;
        System.out.println("Number of letters= " + letters);
        System.out.println("Number of words= " + words);
    }

    // check if char is not letter
    private static boolean contains(int[] ar, int value) {
        // slow method without for
//        return IntStream.of(ar).anyMatch(c -> c == value);
        // faster method
        for (int arVal : ar) {
            if (arVal == value) {
                return true;
            }
        }
        return false;
    }
}
