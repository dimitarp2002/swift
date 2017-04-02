import java.util.Scanner;

public class Task3d_PrintReversedSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        int i = 0;
        String draft="";
        System.out.printf("Please enter %d numbers separated by space: ", number);
        while(i<number){
        int x = sc.nextInt();
        draft=x+" "+draft;
        i++;
        }
      

        System.out.println(draft);
    }

    
}
