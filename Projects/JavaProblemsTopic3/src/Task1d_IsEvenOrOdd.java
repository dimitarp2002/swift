
import java.util.Scanner;

public class Task1d_IsEvenOrOdd {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        if (number != 0) {
            if (number % 2 == 0) {
                System.out.println("EVEN");
            } else {
                System.out.println("ODD");
            }
        } else {
            System.out.println("ZERO");
        }

    }

}
