
import java.util.Calendar;
import java.util.Scanner;

public class Task5_PersonCharacteristics {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter how many persons will you enter:");
        int N = sc.nextInt();
        sc.nextLine();
        for (int i = 1; i <= N; i++) {
            String firstName;
            String familyName;
            short birthYear;
            float weight;
            short height;
            String profession;
            char gender;

            System.out.printf("Please enter firstName;familyName;gender;birthYear;weight;height;"
                    + "profession;grade 1-4 separated by ;  %n");

            String input = sc.nextLine();
            String[] split = input.split(";");

            firstName = split[0];
            familyName = split[1];
            gender = split[2].charAt(0);
            birthYear = Short.parseShort(split[3]);
            weight = Float.parseFloat(split[4]);
            height = Short.parseShort(split[5]);
            profession = split[6];
            float[] grade = {
                Float.parseFloat(split[7])
              , Float.parseFloat(split[8])
              , Float.parseFloat(split[9])
              , Float.parseFloat(split[10])};

            float avg_grade = 0;
            for (int j = 0; j < grade.length; j++) {
                avg_grade += grade[j];
            }

            avg_grade /= grade.length;

            String pronoun;
            String pronouN;
            String pronounPosesive;
            switch (gender) {
                case 'F':
                case 'f':
                    pronoun = "she";
                    pronouN = "She";
                    pronounPosesive = "Her";
                    break;
                default:
                    pronoun = "he";
                    pronouN = "He";
                    pronounPosesive = "His";
                    break;
            }

            System.out.printf("%s %s is %d years old. %s weight is %.1f and %s is %d cm tall. %s is a %s with an average grade of %.3f.", firstName, familyName, (Calendar.getInstance().get(Calendar.YEAR) - birthYear), pronounPosesive, weight, pronoun, height, pronouN, profession, avg_grade);

            if ((Calendar.getInstance().get(Calendar.YEAR) - birthYear) <= 18) {
                System.out.print(" " + firstName + " " + familyName + " is under-aged.");
            }
            System.out.println("");
        }
    }

}
