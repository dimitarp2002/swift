
import java.util.Scanner;

public class Task3c_PrintClusterCount {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        boolean cluster_started = false;
        int cluster_count = 0;

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] == arr[i - 1] && cluster_started == false) {
                cluster_started = true;
                cluster_count++;
            }

            if (arr[i] != arr[i - 1] && cluster_started == true) {
                cluster_started = false;

            }
        }
        System.out.println(cluster_count);

    }

}
