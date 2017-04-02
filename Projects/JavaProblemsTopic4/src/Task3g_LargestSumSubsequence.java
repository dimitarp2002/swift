
import java.util.Scanner;

public class Task3g_LargestSumSubsequence {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        
        int startIndex=0;
        int stopIndex=0;
        int sum=0;
        int maxSum=Integer.MIN_VALUE;
        
        for(int i=0;i<arr.length;i++){
            sum = 0;
            for (int j=i;j<arr.length;j++){
            sum+=arr[j];
            if(sum>maxSum){
            startIndex=i;
            stopIndex=j;
            maxSum=sum;
            }
        
        }
        }
        for(int i=startIndex;i<=stopIndex;i++){
            System.out.print(arr[i]+" ");
        }
    }
    
}
