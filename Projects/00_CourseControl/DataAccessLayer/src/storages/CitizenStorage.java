
package storages;

import java.util.List;
import personaldetails.Citizen;


public interface CitizenStorage {
    
    public int insert(Citizen person) throws DALException;
    public Citizen getCitizenById (int citizenID) throws DALException;
    public void truncateCitizenTable() throws DALException;
    public void Bulkinsert(String filename) throws DALException;
    public void insert(List<Citizen> citizens) throws DALException;
    
}
