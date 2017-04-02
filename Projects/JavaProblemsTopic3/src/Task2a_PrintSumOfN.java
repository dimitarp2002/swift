import java.util.Scanner;

public class Task2a_PrintSumOfN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter one number:");
        int number = sc.nextInt();
        int sum = 0;
        for (int i = 1; i <= number; i++) {
             sum+=i;
            
        }
        System.out.printf("The sum 1 to %d id %d %n", number, sum);

    }
    
}
