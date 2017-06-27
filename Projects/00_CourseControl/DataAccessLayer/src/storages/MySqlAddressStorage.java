package storages;

import address.Address;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;
import personaldetails.Citizen;
import personaldetails.Gender;

public class MySqlAddressStorage implements AddressStorage {

    private final String url;
    private final String username;
    private final String password;
    Connection con;

    public MySqlAddressStorage(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        con = DriverManager.getConnection(url, username, password);
        
    }

    @Override
    public void insert(Address address, int citizenId) throws DALException {
        try (CallableStatement statement = con.prepareCall("{call `sp_insert_address`(?, ?, ?, ?, ?, ?, ?, ?, ? )}")) {

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
    public Address getAddressById(int citizenId) throws DALException {
        Address result=null;
        String query = "SELECT * FROM Addresses "
                + "WHERE id = ?;";
                try ( PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, citizenId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    String country = rs.getString("country");
                    String city = rs.getString("city");
                    String municipality = rs.getString("municipality");
                    String postCode = rs.getString("postCode");
                    String street = rs.getString("street");
                    String number = rs.getString("number");
                    Integer floor = rs.getInt("floor");
                    Integer appNo = rs.getInt("app_no");
                    
                    
                    result = new Address(country, city, municipality, postCode, street, number, floor, appNo);

                }
            }
        } catch (SQLException ex) {
            throw new DALException("Unable to find address for citizen with  Id = " 
                    + citizenId, ex);
        }
        
        return result;
        
        
    }

    @Override
    public void truncateAddressTable() throws DALException {
        try (CallableStatement statementt = con.prepareCall("{call sp_truncate_table(? )}")) {
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
    
    @Override
        public void Bulkinsert(String filename) throws DALException {
    
    try (  Statement statement = con.createStatement();
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
