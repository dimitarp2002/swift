
import java.util.Scanner;

public class Task2e_PrintFirstDigit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        String strnum = number + "";
        System.out.println(strnum.charAt(0));

        System.out.println();
    }

}
