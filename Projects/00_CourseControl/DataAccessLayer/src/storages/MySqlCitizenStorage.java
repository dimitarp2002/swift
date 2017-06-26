package storages;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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

public class MySqlCitizenStorage implements CitizenStorage {

    private final String url;
    private final String username;
    private final String password;
    Connection con;
//    CallableStatement statement;
    
     

    public MySqlCitizenStorage(String url, String username, String password) throws DALException,SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        con = DriverManager.getConnection(this.url, this.username, this.password);
//        statement = con.prepareCall("{call sp_insert_citizen(?, ?, ?, ?, ?, ?, ?)}");
    }
    
        public void truncateCitizenTable() throws DALException {
        try ( CallableStatement statement = con.prepareCall("{call sp_truncate_table(? )}")) {
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

        try ( PreparedStatement stmt = con.prepareStatement(query)) {

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
        try  {
            CallableStatement statement = con.prepareCall("{call sp_insert_citizen(?, ?, ?, ?, ?, ?, ?)}");
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
                        + "INTO TABLE  Citizen FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'"
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
    public void insert(List<Citizen> citizens) throws DALException {
         try {
            Statement statement = con.createStatement();
            StringBuilder query = new StringBuilder().append("INSERT INTO Citizen (id, firstName, middleName, lastName, gender, height, dateOfBirth) VALUES ");
            for (int i=0; i<citizens.size();i++) {
                query.append("\n("+ (i+1) + ", '"+ citizens.get(i).getFirstName() +  "', '" + citizens.get(i).getMiddleName() + "', '" + citizens.get(i).getLastName() + "', '"+citizens.get(i).getGender() + "', " + citizens.get(i).getHeight() + ", '"+citizens.get(i).getDateOfBirth() + "' ), ");
            }
            query.setCharAt(query.lastIndexOf(","), ';');
            statement.execute(query.toString());
            
            
        } catch (SQLException e) {
            throw new DALException("Unable to insert Citizen",e);
        }
        
    }

}


