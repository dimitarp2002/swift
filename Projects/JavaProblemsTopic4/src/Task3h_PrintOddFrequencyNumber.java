
import java.util.Scanner;

public class Task3h_PrintOddFrequencyNumber {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        int count;
        
        for (int i = 0; i < arr.length; i++) {
            count =0;
            for(int j=0; j < arr.length;j++){
                if (arr[j]==arr[i]){
                    count++;
                }
            }
            if (count%2!=0){
                System.out.println(arr[i]);
                return;
            }
        }
    }
    
}
