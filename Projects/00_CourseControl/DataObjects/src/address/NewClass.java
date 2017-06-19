
package address;

import java.time.LocalDate;
import java.time.Month;
import personaldetails.Citizen;
import personaldetails.Gender;

public class NewClass {
    
    public static void main(String[] args) {
        

    
    Gender gender = Gender.Male;
    Citizen citizen = new Citizen("Mitko", "Paskov", "Dimitrov",
            gender, 170, LocalDate.of(1983, Month.MARCH, 12));
    Address address = new Address("Bulgaria", "Sofia", "Sofia", "1000",
            "Ivan Vazov", "4000");
    
  
    
//        System.out.println(citizen.getAddress());
    
   
        if( citizen.getAddress().equals(null)){
            System.out.println("is NULL");
        }else{
            System.out.println("other");
        }
       
 
        
    }
}
