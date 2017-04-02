import java.util.Scanner;

public class Task4a_PrintSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        for (int i=0;i<number;i++){
            if ( (i==0) || (i==number-1) ){
                for (int j=0; j<number; j++){
                    System.out.print("*");
                }
                
            }else for (int j=0; j<number; j++){
                    if ( j==0 || j==number-1 ){
                    System.out.print("*");
                    } else System.out.print(" ");
                }
            
            System.out.println();
        }
      

        
    }
}
