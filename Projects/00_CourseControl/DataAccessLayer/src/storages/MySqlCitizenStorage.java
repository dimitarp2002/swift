package storages;

import address.Address;
import education.Education;
import insurance.SocialInsuranceRecord;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import personaldetails.Citizen;
import personaldetails.Gender;

public class MySqlCitizenStorage implements CitizenStorage {

    private final String url;
    private final String username;
    private final String password;

    public MySqlCitizenStorage(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
        public void truncateCitizenTable() throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call sp_truncate_table(? )}")) {
            statement.setString(1, "Citizen");
            statement.executeQuery();

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
    public Citizen getCitizenById(int citizenId) throws DALException {

        Citizen result=null;
        

        String query = "SELECT * FROM Citizen "
                + "WHERE id = ?;";

        try (Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, citizenId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    String firstName = rs.getString("firstName");
                    String middleName = rs.getString("middleName");
                    String lastName = rs.getString("lastName");
                    Gender gender;
                    if (rs.getString("gender").equalsIgnoreCase("M") ){
                    gender = Gender.Male;
                    }else{
                    gender = Gender.Female;
                    }
       
                    int height = rs.getInt("height"); 

                    LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                    result = new Citizen(firstName, middleName, lastName, gender, height, dateOfBirth);

                }
            }
        } catch (SQLException ex) {
            throw new DALException("Unable to citizen with Id = "
                    + citizenId, ex);
        }
        
        return result;
    }

    @Override
    public int insert(Citizen person) throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call sp_insert_citizen(?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setString("p_firstName", person.getFirstName());
            statement.setString("p_middleName", person.getMiddleName());
            statement.setString("p_lastName", person.getLastName());
            statement.setString("p_gender", person.getGender().toString());
            statement.setInt("p_height", person.getHeight());
            statement.setDate("p_dateOfBirth", Date.valueOf(person.getDateOfBirth()));
            statement.registerOutParameter("p_id", Types.INTEGER);

            statement.executeQuery();
            
            return statement.getInt("p_id");

        } catch (SQLException ex) {

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


