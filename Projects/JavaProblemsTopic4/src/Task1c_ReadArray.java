
import java.util.Scanner;

public class Task1c_ReadArray {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }

        }
        System.out.println("");

    }

}
