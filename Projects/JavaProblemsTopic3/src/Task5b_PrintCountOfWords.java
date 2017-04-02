
import java.util.Scanner;

public class Task5b_PrintCountOfWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string: ");
        String str = sc.nextLine();
        int words = 0;

        for (int i = 1; i < str.length(); i++) {
            if ((str.charAt(i) == ' ' || str.charAt(i) == '.'
                    || str.charAt(i) == '!' || str.charAt(i) == '?'
                    || str.charAt(i) == ',')
                    && (str.charAt(i - 1) != ' ' && str.charAt(i - 1) != '.'
                    && str.charAt(i - 1) != '!' && str.charAt(i - 1) != '?'
                    && str.charAt(i - 1) != ',')) {
                words++;
            }
        }
        System.out.println(words);
    }

}
