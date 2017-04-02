
import java.util.Scanner;

public class Task4c_PrintChristmasTree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        for (int i = 0; i <= number - 1; i++) {
            for (int j = (-1 * (number - 3)); j <= (number - 3); j++) {
                if (i < number - 2) {
                    if (((j <= 0) && (j >= -i)) || ((j > 0) && (j <= i))) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }

                } else if (i == number - 2) {
                    if (j == 0) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }

                } else if (((j <= 0) && (j >= -1)) || ((j > 0) && (j <= 1))) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }

            }

            System.out.println();
        }

    }

}
