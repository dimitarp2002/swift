
import java.util.Scanner;

public class Task2c_PrintMatrix {

    public static void main(String[] args) {

        int row;
        int col;
        int indx = 1;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");

        int number = sc.nextInt();
        row = col = number;
        int arr[][] = new int[row][col];

        for (int n = 0; n < arr.length; n++) {
            for (row = 0, col = n - row; row <= n; row++, col = n - row) {
                if (n % 2 != 0) {
                    arr[row][col] = indx++;
                } else {
                    arr[col][row] = indx++;
                }

            }
        }

        for (int n = arr.length; n < (2 * arr.length - 1); n++) {
            for (row = n - (arr.length - 1), col = n - row; row <= (arr.length - 1); row++, col = n - row) {
                if (n % 2 != 0) {
                    arr[row][col] = indx++;
                } else {
                    arr[col][row] = indx++;
                }

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
