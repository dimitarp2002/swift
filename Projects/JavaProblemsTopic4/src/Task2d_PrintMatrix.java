
import java.util.Scanner;

public class Task2d_PrintMatrix {

    public static void main(String[] args) {

        int row;
        int col;
        int indx = 1;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");

        int number = sc.nextInt();
        row = col = number;
        int arr[][] = new int[row][col];

        int i, j, z;
        i = j = z = 0;
        int limit;
        if (arr.length % 2 == 0) {
            limit = arr.length / 2 ;
        } else {
            limit = arr.length / 2;
        }

        for (z = 0; z <= (limit); z++) {
            for (; j < ((arr.length) - z); j++) {
                arr[i][j] = indx++;
            }

            j--;

            for (i++; i < arr.length - z; i++) {
                arr[i][j] = indx++;
            }

            i--;

            for (j--; j >= z; j--) {
                arr[i][j] = indx++;
            }

            j++;

             if (indx > (arr.length*arr.length)){
                break;
                }
            
            for (i--; i >= z + 2; i--) {
               
                arr[i][j] = indx++;
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
