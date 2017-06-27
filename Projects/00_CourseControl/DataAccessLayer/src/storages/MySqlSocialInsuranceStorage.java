package storages;

import insurance.SocialInsuranceRecord;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySqlSocialInsuranceStorage implements SocialInsuranceStorage {

    private final String url;
    private final String username;
    private final String password;
    

    public MySqlSocialInsuranceStorage(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        

    }

    @Override
    public void insert(SocialInsuranceRecord record, int citizenId) throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call `sp_insert_sriRecord`(?, ?, ?, ? )}")) {

            statement.setInt("p_year", record.getYear());
            statement.setInt("p_month", record.getMonth());
            statement.setDouble("p_amount", record.getAmount());
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
    public List<SocialInsuranceRecord> getSocialInsuranceById(int citizenId) throws DALException {

        List<SocialInsuranceRecord> result = new ArrayList<>();

        String query = "SELECT * FROM SocialInsuransRecords "
                + "WHERE citizen_id = ? "
                + "ORDER BY year DESC, month DESC;";

        try (Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, citizenId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    int year = rs.getInt("year");
                    int month = rs.getInt("month");
                    double amount = rs.getDouble("amount");

                    result.add(new SocialInsuranceRecord(year, month, amount));
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Unable to get social insurance records for citizen with Id = "
                    + citizenId, ex);
        }
        return result;
    }

    @Override
    public void truncateSocialInsTable() throws DALException {

        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statementt = con.prepareCall("{call sp_truncate_table(? )}")) {
            statementt.setString(1, "SocialInsuransRecords");
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

        try (Connection con = DriverManager.getConnection(url, username, password);
                Statement statementt = con.createStatement();) {
//                statement.executeUpdate( "LOAD DATA LOCAL INFILE '/home/username/avail30trplog' INTO TABLE  logname.log FIELDS TERMINATED BY ' ' LINES TERMINATED BY '\\n'");
            Integer result = statementt.executeUpdate(
                    "LOAD DATA LOCAL INFILE "
                    + "'"
                    + filename
                    + "'"
                    + "INTO TABLE  SocialInsuransRecords FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n'"
            );

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

    @Override
    public void insert(List<SocialInsuranceRecord> records, int citizenId) throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                Statement statement = con.createStatement();){
            
            StringBuilder query = new StringBuilder().append("INSERT INTO SocialInsuransRecords (year, month, amount, citizen_id) VALUES ");
            for (SocialInsuranceRecord record : records) {
                query.append("\n(" + record.getYear() + ", " + record.getMonth() + ", " + record.getAmount() + ", " + citizenId + "), ");
            }
            query.setCharAt(query.lastIndexOf(","), ';');
            statement.execute(query.toString());
        } catch (SQLException e) {
            throw new DALException("Unable to insert Citizen", e);
        }
    }

}
