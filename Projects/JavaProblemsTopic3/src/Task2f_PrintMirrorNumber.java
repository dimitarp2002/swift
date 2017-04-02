
import java.util.Scanner;

public class Task2f_PrintMirrorNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        String strnum = number + "";

        for (int i = strnum.length() -1; i >= 0; i--) {
            System.out.print(strnum.charAt(i));
        }
        System.out.println();
    }

}
