
import java.util.Scanner;


public class Task4d_ConvertibleStrings {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");

        String str = sc.nextLine();
        boolean isPalindrome = true;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt((str.length() - 1) - i)) {
                isPalindrome = false;
            }
        }

        System.out.println(isPalindrome);

    }
    
}
