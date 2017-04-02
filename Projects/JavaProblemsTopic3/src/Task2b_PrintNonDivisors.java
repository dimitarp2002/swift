
import java.util.Scanner;

public class Task2b_PrintNonDivisors {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter one number:");
        int number = sc.nextInt();
        for (int i = 1; i <= number; i++) {
            if (!((i % 3 == 0) || (i % 7 == 0))) {
                System.out.print(i + " ");
            }

        }
        System.out.println();

    }

}
