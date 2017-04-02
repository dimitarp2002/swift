
import java.util.Scanner;

public class Task4c_CaesarCipher {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter a string: ");
        
        String str = sc.nextLine();
        
        String action = sc.nextLine();
        
        if (action.compareToIgnoreCase("encode") == 0) {
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) >= 'A' && str.charAt(i) < 'Z') || (str.charAt(i) >= 'a' && str.charAt(i) < 'z')) {
                    System.out.printf("%c", str.charAt(i) + 1);
                } else if (str.charAt(i) == 'z') {
                    System.out.printf("%c", 'a');
                } else if (str.charAt(i) == 'Z') {
                    System.out.printf("%c", 'A');
                } else {
                    System.out.printf("%c", str.charAt(i));
                }                
            }
        } else if (action.compareToIgnoreCase("decode") == 0) {
            
            for (int i = 0; i < str.length(); i++) {
                if ((str.charAt(i) > 'a' && str.charAt(i) <= 'z') || (str.charAt(i) > 'A' && str.charAt(i) <= 'Z')) {
                    System.out.printf("%c", str.charAt(i) - 1);
                } else if (str.charAt(i) == 'a') {
                    System.out.printf("%c", 'z');
                } else if (str.charAt(i) == 'A') {
                    System.out.printf("%c", 'Z');
                } else {
                    System.out.printf("%c", str.charAt(i));
                }                
            }
            
        }
        
    }
    
}
