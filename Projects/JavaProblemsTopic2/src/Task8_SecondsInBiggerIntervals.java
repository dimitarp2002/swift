public class Task8_SecondsInBiggerIntervals {
    public static void main(String[] args) {
        
        int seconds = 1234567;
        int days;
        int hours;
        int minutes;
        int secs;
        int remaining;
        
        days = seconds/86400; // 1 day = 60*60*24=86400 seconds 
        remaining = seconds%86400;
        
        hours = remaining/3600;
        remaining %=3600;
        
        minutes = remaining/60;
        remaining %=60;
                
        secs = remaining;
        
        System.out.println("Days:"+days+" Hours:"+hours+" Minutes:"+minutes+" Secs:"+secs);
    }    
}
