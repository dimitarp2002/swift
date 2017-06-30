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
import java.util.List;
import personaldetails.Citizen;

public class MySqlAddressStorage implements AddressStorage {

    private final String url;
    private final String username;
    private final String password;
    

    public MySqlAddressStorage(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void insert(Address address, int citizenId) throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call `sp_insert_address`(?, ?, ?, ?, ?, ?, ?, ?, ? )}")) {

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
                try ( Connection con = DriverManager.getConnection(url, username, password);
                        PreparedStatement stmt = con.prepareStatement(query)) {

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
                    if(floor==0){
                    floor=null;
                    }
                    Integer appNo = rs.getInt("app_no");
                    if(appNo==0){
                    appNo=null;
                    }
                    
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
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statementt = con.prepareCall("{call sp_truncate_table(? )}")) {
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
    
    try (  Connection con = DriverManager.getConnection(url, username, password);
            Statement statement = con.createStatement();  ) {
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
        
        
    @Override
            public void insertAddresses(List<Citizen> citizens) throws DALException {
         try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
                Statement statement = con.createStatement(); ){
            
            StringBuilder query = new StringBuilder().append("INSERT INTO Addresses (country, city, municipality, postCode, street, number, floor, app_no, citizen_id) VALUES ");
            for (int i=0; i<citizens.size();i++) {
                Address address = citizens.get(i).getAddress();
                query.append("\n('"+ address.getCountry() +  "', '" + address.getCity() + "', '" + address.getMunicipality() + "', '"+address.getPostalCode() + "', '" + address.getStreet() + "', '" + address.getNumber() + "', "+ address.getFloor() + ", "+address.getApartmentNo() + ", "+ (i+1) + " ), ");
            }
            query.setCharAt(query.lastIndexOf(","), ';');
            statement.execute(query.toString());
            
            
        } catch (SQLException e) {
            throw new DALException("Unable to insert Citizen",e);
        }
        
    }

}
