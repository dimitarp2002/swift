import java.util.Scanner;

public class Task3b_PrintSumOfN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        int i = 0;
        int sum =0;
        System.out.printf("Please enter %d numbers separated by space: ", number);
        while(i<number){
            int x = sc.nextInt();
            sum+=x;
            i++;
        }
      

        System.out.println("Sum: "+sum);
    }
    
}
