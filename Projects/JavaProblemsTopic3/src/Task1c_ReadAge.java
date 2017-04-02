
import java.util.Scanner;

public class Task1c_ReadAge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter age:");
        int age = sc.nextInt();

        if ((age >= 18) && (age < 120)) {
            System.out.println("YES");
        } else if ((age > 0) && (age < 18)) {
            System.out.println("NO");
        } else {
            System.out.println("ERROR");
        }

    }
}
