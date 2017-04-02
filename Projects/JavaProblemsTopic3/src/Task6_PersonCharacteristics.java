
import java.util.Calendar;
import java.util.Scanner;

public class Task6_PersonCharacteristics {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter how many persons will you enter:");
        int N = sc.nextInt();
        for (int i=1;i<=N;i++){
        short birthYear;
        float weight;
        short height;
        String profession;
                
        
        System.out.println("Please enter first name:");
        String firstName = sc.nextLine();
        System.out.println("Please enter family name:");
        String familyName = sc.nextLine();
        do {
            System.out.println("Please enter birthYear:");
            birthYear = sc.nextShort();
        }while (birthYear<1900 || birthYear >=2017);
        
        do {
        System.out.println("Please enter weight:"); 
        weight = sc.nextFloat();
        } while (weight<0 || weight> 200);
        
        do{
        System.out.println("Please enter height"); 
        height = sc.nextShort();
        }while(height<0 || height>250);
        
        sc.nextLine();
        
        
        System.out.println("Please enter profession:"); 
        profession = sc.nextLine();
        
        
        System.out.print(firstName + " " + familyName + " is "
                + (Calendar.getInstance().get(Calendar.YEAR) - birthYear)
                + " years old. His weight is " + weight + " and he is "
                + height + " cm tall. He is a " + profession +". ");

        if((Calendar.getInstance().get(Calendar.YEAR) - birthYear)<=18){
            System.out.print(firstName + " " +familyName + " is under-aged.");
        }
        System.out.println("");
        }
    }

}
