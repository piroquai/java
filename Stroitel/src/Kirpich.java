import java.util.Arrays;
import java.util.Scanner;

public class Kirpich {
    public static void main(String[] args) {
        int holeWidth;
        int holeHeight;
        int brick[] = new int[3];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter hole width: ");
        holeWidth = in.nextInt();
        System.out.println("Enter hole height: ");
        holeHeight = in.nextInt();
        System.out.println("Enter brick width: ");
        brick[0] = in.nextInt();
        System.out.println("Enter brick height: ");
        brick[1] = in.nextInt();
        System.out.println("Enter brick length: ");
        brick[2] = in.nextInt();

        int holeMax = Math.max(holeHeight, holeWidth);
        int holeMin = Math.min(holeHeight, holeWidth);
        Arrays.sort(brick);

        if (holeMax >= brick[1] && holeMin >= brick[0])
            System.out.println("Brick fits");
        else
            System.out.println("Brick doesn't fit");
    }
}
