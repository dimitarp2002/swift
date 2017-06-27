
package businessmanagers;

import personaldetails.Citizen;
import storages.DALException;


public interface BusinessManagerCitizen {
    
    public Citizen getCitizen(String citizenId) throws DALException;
    public String isEligible(String citizenId) throws DALException ;
    
}
