package businessmanagers;

import address.Address;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import storages.AddressStorage;
import storages.DALException;
import storages.MySqlAddressStorage;

public class MyBusinessManagerAddress implements BusinessManagerAddress {

    String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    String username = "dimitar";
    String password = "123456";

    AddressStorage storageAddress;

    public MyBusinessManagerAddress() throws DALException {

        try {

            storageAddress = new MySqlAddressStorage(url, username, password);

        } catch (SQLException ex) {
            throw new DALException("Unable to open storage", ex);
        }

    }

    @Override
    public Address getAddress(String citizenId) throws DALException {
        Address address = null;
        if (citizenId != null && !(citizenId.equalsIgnoreCase(""))) {
            int receivedId = Integer.parseInt(citizenId);

            try {
                address = storageAddress.getAddressById(receivedId);
            } catch (DALException ex) {
                throw new DALException("Unable to retrieve address for citizen with id: "+ receivedId, ex);
            }

        }

        return address;
    }

}
