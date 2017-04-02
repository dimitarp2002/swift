import java.util.Scanner;
import java.util.Arrays;

public class Task3a_PrintLargestNumber {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        
        Arrays.sort(arr);
        System.out.println(arr[arr.length-1]);
        
    }
    
    
}
