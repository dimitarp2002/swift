import java.util.Scanner;

public class Task3c_PrintOnlyEvenNumbers {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        int i = 0;
        String draft = "";
        System.out.printf("Please enter %d numbers separated by space: ", number);
        while(i<number){
            int x = sc.nextInt();
            if (x%2==0){
                draft+=x+" ";
            }
            i++;
        }
      

        System.out.println(draft);
    }
    
}
