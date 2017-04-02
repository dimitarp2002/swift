
import java.util.Scanner;

public class Task4f_LongestCommonSubstring {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 2 strings: ");

        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        int startIndex = -1;
        int stopIndex = -1;
        int maxMatchLength = 0;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = i + 1; j < str1.length(); j++) {
                if (str2.contains(str1.substring(i, j)) && (j - i > maxMatchLength)) {
                    maxMatchLength = j - i;
                    startIndex = i;
                    stopIndex = j;

                }
            }
        }

        System.out.println(str1.subSequence(startIndex, stopIndex));

    }

}
