
package businessmanagers;

import address.Address;
import storages.DALException;


public interface BusinessManagerAddress {
    public Address getAddress(String citizenId)throws DALException;
    
}
