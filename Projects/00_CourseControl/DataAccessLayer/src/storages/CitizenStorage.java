
package storages;

import personaldetails.Citizen;


public interface CitizenStorage {
    
    public int insert(Citizen person) throws DALException;
    public Citizen getCitizenById (int citizenID) throws DALException;
    public void truncateCitizenTable() throws DALException;
    
}
