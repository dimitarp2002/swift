
import java.util.Scanner;

public class Task2i_ToBinaryAndHexa {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number to convert to binary and Hex: ");
        int number = sc.nextInt();
        int numberCopy=number;
        String converted="";
        
        do{
            converted=(number%2)+converted;
            number/=2;
       
        }while(((number/2)!=0)||((number%2!=0)));
        
        System.out.println(converted);
        
        converted="";
        String Hex="";
        number=numberCopy;
         do{
            switch(number%16){
                case 0: Hex="0";break;
                case 1: Hex="1";break;
                case 2: Hex="2";break;
                case 3: Hex="3";break;
                case 4: Hex="4";break;
                case 5: Hex="5";break;
                case 6: Hex="6";break;
                case 7: Hex="7";break;
                case 8: Hex="8";break;
                case 9: Hex="9";break;
                case 10:Hex= "A";break;
                case 11:Hex= "B";break;
                case 12:Hex= "C";break;
                case 13:Hex= "D";break;
                case 14:Hex= "E";break;
                case 15:Hex= "F";break;
            
            }
           
            converted = Hex + converted;
            number/=16;
       
        }while(((number/16)!=0)||((number%16!=0)));
     
        System.out.println(converted);
        
    }

}
