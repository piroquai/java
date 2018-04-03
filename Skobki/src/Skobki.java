import java.util.Scanner;

public class Skobki {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter String: ");
        String stroka = in.nextLine();
        int balans_skobok = 0;
        for (int i = 0; i < stroka.length(); i++) {
            if (stroka.charAt(i) == '(')
                balans_skobok++;
            if (stroka.charAt(i) == ')')
                balans_skobok--;
            if (balans_skobok < 0)
                break;
        }
        if (balans_skobok == 0)
            System.out.println("OK");
        else System.out.println("Error");
    }
}
