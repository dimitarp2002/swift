import java.util.Scanner;
public class Task3_DateDifference {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter two dates  YYYY mm dd:  ");
        SwiftDate[] dates = new SwiftDate[2];
        
        for (int i = 0; i < 2; i++) {
        String [] parts = sc.nextLine().split(" ");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        dates[i] = new SwiftDate(year,month,day);
        }
        
        
        System.out.println(dates[0].getDdaysDifference(dates[1]));
        
    }
    
    
}
