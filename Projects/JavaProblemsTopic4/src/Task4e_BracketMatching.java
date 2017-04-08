
import java.util.Scanner;

public class Task4e_BracketMatching {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");

        String str = sc.nextLine();

        char[] charray = str.toCharArray();
        boolean bracketsMatch = true;

        for (int i = charray.length - 2; i >= 0; i--) {
            if (    (charray[i] == '(')  ||  (charray[i] == '[')     ||     (charray[i] == '{')    ) {
                int j = i + 1;
                while (charray[j] == '0') {
                    j++;
                }
                if (charray[j] - charray[i] == 1 || charray[j] - charray[i] == 2) { // { } in Unicode table are non consequent characters
                    charray[i] = '0';
                    charray[j] = '0';
                }
            }
        }
        for (int i = 0; i < charray.length; i++) {
            if (charray[i] != '0') {
                bracketsMatch = false;
            }

        }

        System.out.println(bracketsMatch);

    }

}
