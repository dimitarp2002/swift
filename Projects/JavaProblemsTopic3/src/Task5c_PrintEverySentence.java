
import java.util.Scanner;

public class Task5c_PrintEverySentence {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String str = sc.nextLine();
        int words = 0;

        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            if ((str.charAt(i) == '?') || (str.charAt(i) == '!')
                    || (str.charAt(i) == '.')) {
                System.out.println();
            }

        }

    }

}
