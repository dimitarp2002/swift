
import java.util.Scanner;

public class Task3f_LargestSumOfKSubsequence {
    
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
        int startIndex=0;
        int stopIndex=0;
        int sumOfKelement=0;
        int maxSumOfKelement=Integer.MIN_VALUE;
        
        for (int i = K-1; i < arr.length; i++) {
            sumOfKelement=0;
            for (int j = i-(K-1);j<=i;j++){
            sumOfKelement+=arr[j];
            }
            if (sumOfKelement>maxSumOfKelement){
                maxSumOfKelement=sumOfKelement;
                startIndex = i-(K-1);
                stopIndex = i;
            }
        }
        for (int i=startIndex; i<=stopIndex;i++){
            System.out.print(arr[i]+" ");
        }
        
    }
    
}
