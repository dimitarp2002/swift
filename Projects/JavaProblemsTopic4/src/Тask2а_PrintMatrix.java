
import java.util.Scanner;

public class Тask2а_PrintMatrix {

    public static void main(String[] args) {

        int row;
        int col;
        int indx = 1;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");

        int number = sc.nextInt();
        row = col = number;
        int arr[][] = new int[row][col];

        for (col = 0; col < number; col++) {
            for (row = 0; row < number; row++) {
                arr[row][col] = indx;
                indx++;

            }

        }

        for (row = 0; row < number; row++) {
            for (col = 0; col < number; col++) {
                System.out.printf("%4d", arr[row][col]);
            }
            System.out.println();
        }

    }

}
