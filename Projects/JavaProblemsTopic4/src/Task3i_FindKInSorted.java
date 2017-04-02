
import java.util.Arrays;
import java.util.Scanner;

public class Task3i_FindKInSorted {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter two digits: ");
        int number = sc.nextInt();
        int K = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        int x = Arrays.binarySearch(arr, K);
        if (x >= 0) {
            System.out.println(x);
        } else {
            System.out.println("NO");
        }
    }

}
