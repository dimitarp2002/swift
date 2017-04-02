import java.util.Scanner;

public class Task4b_PrintZFigure {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number: ");
        int number = sc.nextInt();
        for (int i=1;i<=number;i++){
            if ( (i==1) || (i==number) ){
                for (int j=1; j<=number; j++){
                    System.out.print("*");
                }
                
            }else for (int j=1; j<=number; j++){
                    if ( i+j==number+1 ){
                    System.out.print("*");
                    } else System.out.print(" ");
                }
            
            System.out.println();
        }
      

        
    }
    
    
    
}
