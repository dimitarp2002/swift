
import java.util.Calendar;

public class Task10_PersonCharacteristics {

    public static void main(String[] args) {

        String firstName = "Georgi";
        String familyName = "Georgiev";
        short birthYear = 2002;
        float weight = 48.3f;
        short height = 156;
        String profession = "student";

        System.out.println(firstName + " " + familyName + " is "
                + (Calendar.getInstance().get(Calendar.YEAR) - birthYear)
                + " years old. His weight is " + weight + " and he is "
                + height + " cm tall. He is a " + profession);

    }

}
