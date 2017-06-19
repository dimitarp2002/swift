package storages;

import address.Address;
import education.Education;
import insurance.SocialInsuranceRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 
                    String genderStr = rs.getString("gender"); 
                    System.out.println(genderStr);
                    Gender gender;
                    if (genderStr.equalsIgnoreCase("M") || genderStr.equalsIgnoreCase("М")){
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
    public void insert(Citizen person) throws DALException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}


