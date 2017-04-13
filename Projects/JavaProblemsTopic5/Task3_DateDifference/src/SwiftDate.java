
public class SwiftDate {

    int year;
    int month;
    int day;

    public SwiftDate(int _year, int _month, int _day) {
        year = _year;
        month = _month;
        day = _day;
    }

    boolean isLeapYear() {
        return isLeapYear(this.year);
    }

    static boolean isLeapYear(int year) {
        boolean isLeapYear;
        boolean isYearDevisibleBy4 = (year % 4 == 0);
        boolean isYearDevisibleBy100 = (year % 100 == 0);
        boolean isYearDevisibleBy400 = (year % 400 == 0);
        isLeapYear = ((isYearDevisibleBy4 && (!isYearDevisibleBy100)) || isYearDevisibleBy400);
        return isLeapYear;
    }

    int getCentury() {
        return (year / 100 + 1);
    }

    static private int getDaysFromBeginingOfYear(int Year, int Month, int Day) {
        int Days = 0;
        switch (Month - 1) {
            case 11:
                Days += 30;
            case 10:
                Days += 31;
            case 9:
                Days += 30;
            case 8:
                Days += 31;
            case 7:
                Days += 31;
            case 6:
                Days += 30;
            case 5:
                Days += 31;
            case 4:
                Days += 30;
            case 3:
                Days += 31;
            case 2: {
                Days += 28;
                if (isLeapYear(Year)) {
                    Days += 1;
                }
            }
            case 1:
                Days += 31;
        }
        Days+=Day;
        return Days;
    }

    int getDdaysDifference(SwiftDate other) {
        int DaysDifference = 0;
        int x = (this.year - 1) * 365 + (this.year - 1) / 4 - (this.year - 1) / 100 + (this.year - 1) / 400;
        int y = (other.year - 1) * 365 + (other.year - 1) / 4 - (other.year - 1) / 100 + (other.year - 1) / 400;
        x += getDaysFromBeginingOfYear(this.year, this.month, this.day);
        y += getDaysFromBeginingOfYear(other.year, other.month, other.day);
        if (x > y) {
            DaysDifference = x - y+1;
        } else {
            DaysDifference = y - x+1;
        }

        return DaysDifference;

    }
    
    String getInfo(){
    String info = String.format(" %d %d %d - %d century", this.year, this.month, this.day, getCentury());
    if (this.isLeapYear()){
        return info + " It is LEAP year.";
    }else return info;
    
    }

}
