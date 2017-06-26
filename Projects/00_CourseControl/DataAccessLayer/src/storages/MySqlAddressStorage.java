package storages;

import address.Address;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

public class MySqlAddressStorage implements AddressStorage {

    private final String url;
    private final String username;
    private final String password;
    Connection con;
    CallableStatement statement;

    public MySqlAddressStorage(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        con = DriverManager.getConnection(url, username, password);
        statement = con.prepareCall("{call `sp_insert_address`(?, ?, ?, ?, ?, ?, ?, ?, ? )}");
    }

    public void insert(Address address, int citizenId) throws DALException {
        try  {

            statement.setString("p_country", address.getCountry());
            statement.setString("p_city", address.getCity());
            statement.setString("p_municipality", address.getMunicipality());
            statement.setString("p_postCode", address.getPostalCode());
            statement.setString("p_street", address.getStreet());
            statement.setString("p_number", address.getNumber());
            try {
                statement.setInt("p_floor", address.getFloor());
            } catch (NullPointerException ex) {
                statement.setNull("p_floor", Types.INTEGER);
            }
            try {
                statement.setInt("p_app_no", address.getApartmentNo());
            } catch (NullPointerException ex) {
                statement.setNull("p_app_no", Types.INTEGER);
            }

            statement.setInt("p_citizen_id", citizenId);

            statement.executeQuery();

        } catch (SQLException ex) {

            // SQLException is actually a linked list of Exceptions
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
            throw new DALException("Unsuccessful insertion", ex);

        }

    }

    @Override
    public List<Address> getAddressById(int citizenId) throws DALException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void truncateAddressTable() throws DALException {
        try (Connection conn = DriverManager.getConnection(url, username, password);
                CallableStatement statementt = conn.prepareCall("{call sp_truncate_table(? )}")) {
            statementt.setString(1, "Addresses");
            statementt.executeQuery();

        } catch (SQLException ex) {

            // SQLException is actually a linked list of Exceptions
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
            throw new DALException("Unsuccessful truncation", ex);

        }

    }
    
        public void Bulkinsert(String filename) throws DALException {
    
    try (Connection con = DriverManager.getConnection(url, username, password);
                Statement statement = con.createStatement();
                ) {
//                statement.executeUpdate( "LOAD DATA LOCAL INFILE '/home/username/avail30trplog' INTO TABLE  logname.log FIELDS TERMINATED BY ' ' LINES TERMINATED BY '\\n'");
                Integer result = statement.executeUpdate(
                        "LOAD DATA LOCAL INFILE "
                        + "'"
                        + filename
                        + "'"
                        + "INTO TABLE  Addresses FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'"
                        );
    
    }catch (SQLException ex) {

            // SQLException is actually a linked list of Exceptions
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
            throw new DALException("Unable to insert Citizen", ex);
        }
    
    }

}
