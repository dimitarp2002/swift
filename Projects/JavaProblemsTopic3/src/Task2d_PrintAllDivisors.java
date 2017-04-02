
import java.util.Scanner;

public class Task2d_PrintAllDivisors {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        String divisors = "";
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                divisors += i + " ";
            }

        }
        System.out.println(divisors);
    }

}
