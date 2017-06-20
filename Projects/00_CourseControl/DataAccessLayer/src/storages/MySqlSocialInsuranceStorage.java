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

    public MySqlSocialInsuranceStorage(String url, String username, String password) {
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

        try (Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {

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
                CallableStatement statement = con.prepareCall("{call sp_truncate_table(? )}")) {
            statement.setString(1, "SocialInsuransRecords");
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
}
