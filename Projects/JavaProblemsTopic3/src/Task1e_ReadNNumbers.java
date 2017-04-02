
import java.util.Scanner;

public class Task1e_ReadNNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter one number:");
        int number = sc.nextInt();
        System.out.printf("Please enter %d numbers separated by space: ", number);
        int i=0;
        String draft="";
        while(i<number){
            int x = sc.nextInt();
            draft+=x+ " ";
            i++;
        }
        System.out.println(draft);
        /*
        Scanner sc1 = new Scanner(System.in);

        String input = sc1.nextLine();
        System.out.println();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ') {
                System.out.printf("%n");
            } else {
                System.out.printf("%c", input.charAt(i));
            }
                
        }*/
        System.out.println();
    }

}
