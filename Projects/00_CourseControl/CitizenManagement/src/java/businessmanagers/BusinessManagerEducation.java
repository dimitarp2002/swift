
package businessmanagers;

import education.Education;
import java.util.List;
import storages.DALException;


public interface BusinessManagerEducation {
    
    
     public List<Education>  getEducations(String citizenId)throws DALException;
     public void insertEducation(String institutionName, String  enrollmentDate, 
             String  graduationDate, String  degree, String grade, String citizenId )throws DALException;
    
}
