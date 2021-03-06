package storages;

import address.Address;
import java.util.List;
import personaldetails.Citizen;

public interface AddressStorage {

    public void insert(Address address, int citizenId) throws DALException;

    Address getAddressById(int citizenId) throws DALException;

    public void truncateAddressTable() throws DALException;

    public void Bulkinsert(String filename) throws DALException;

    public void insertAddresses(List<Citizen> citizens) throws DALException;

}
