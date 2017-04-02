import java.util.Scanner;

public class Task3a_PrintMinAndMax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        int i = 0;
        int min = 2000000000;
        int max = -2000000000;
        System.out.printf("Please enter %d numbers separated by space: ", number);
        while(i<number){
            int x = sc.nextInt();
            if (x>max){
                max = x;
            }
            
            if (x<min){
                min = x;
            }
            i++;
        }
      

        System.out.println("Max: "+ max +" Min: "+ min);
    }
}
