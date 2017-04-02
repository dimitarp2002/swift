
import java.util.Scanner;

public class Task1f_ReadNNumbersOnNewLines {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter one number:");
        int number = sc.nextInt();
        System.out.printf("Please enter %d numbers separated by new line: ", number);
        String result = "";
        for (int i = 0; i < number; i++) {
            int input = sc.nextInt();
            result += input + " ";
        }
        System.out.println(result);

    }

}
