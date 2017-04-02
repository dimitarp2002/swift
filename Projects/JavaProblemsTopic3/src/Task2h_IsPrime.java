
import java.util.Scanner;

public class Task2h_IsPrime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number to check if it is Prime: ");
        int number = sc.nextInt();
        boolean isPrime = true;
        for (int i=2; i<=number/2; i++){
            if (number%i==0) {
            isPrime = false;
            break;
        }
        }
            System.out.println(isPrime);
        }
        
        
    }


