
import java.util.Scanner;

public class Task5d_SumOfNumbersInString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a string with numbers in it: ");
        String inputString = sc.nextLine();
        int sum = 0;
        int number = 0;
        for (int i = 0; i < inputString.length(); i++) {
            int digit = 0;

            if (inputString.charAt(i) >= '0' && inputString.charAt(i) <= '9') {
                digit = inputString.charAt(i) - '0';
                number = 10 * number + digit;
            }

            if (inputString.charAt(i) == ' '
                    || inputString.charAt(i) == '.' || inputString.charAt(i) == '!'
                    || inputString.charAt(i) == '?' || i == inputString.length() - 1) {
                sum += number;
                number = 0;

            }

        }

        System.out.println(sum);
    }

}
