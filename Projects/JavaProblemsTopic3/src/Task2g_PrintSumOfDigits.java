
import java.util.Scanner;

public class Task2g_PrintSumOfDigits {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        String strnum = number + "";
        int digit, sum=0;
        for (int i = 0; i < strnum.length(); i++) {
            digit = strnum.charAt(i) - '0';
            sum += digit;
        }
        System.out.println(sum);
        System.out.println();
    }

}
