
import java.util.Scanner;

public class Task3d_PrintSplitIndex {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a digit: ");
        int number = sc.nextInt();
        int arr[] = new int[number];
        System.out.printf("Please enter %d digits: %n ", number);
        for (int i = 0; i < number; i++) {
            arr[i] = sc.nextInt();
        }
        boolean isSplitIndex = false;
        int index = -1;
        int sumLeftOfIndex = 0;
        int sumRightOfIndex = 0;
        
        
        for (int i = 0; i < arr.length; i++) {
            sumLeftOfIndex += arr[i];
            sumRightOfIndex = 0;
            for (int j = i+1; j < arr.length; j++) {
                sumRightOfIndex += arr[j];
            }
            
            if (sumLeftOfIndex == sumRightOfIndex) {
                index = i;
                isSplitIndex = true;
            }
        }
        if (isSplitIndex) {
            System.out.println(index);
        } else {
            System.out.println("NO");
        }
    }

}
