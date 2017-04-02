public class Task9_LeapYear {
    public static void main(String[] args) {
        
        int year = 2016;
        boolean isLeapYear;
        boolean isYearDevisibleBy4    =   (year%4==0);
        boolean isYearDevisibleBy100  =   (year%100==0);
        boolean isYearDevisibleBy400  =   (year%400==0);
       // isLeapYear = ((year%4==0))||isYearDevidedBy400;
        // (by4 && !by 100) || by400
        System.out.println((isYearDevisibleBy4 && (!isYearDevisibleBy100)) ||isYearDevisibleBy400);
        
    }
    
    
}
