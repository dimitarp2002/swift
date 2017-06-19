
package storages;

import personaldetails.Citizen;


public interface CitizenStorage {
    
    public void insert(Citizen person) throws DALException;
    public Citizen getCitizenById (int citizenID) throws DALException;
    
}
