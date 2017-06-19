package storages;

import insurance.SocialInsuranceRecord;
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

    /**
     *
     * @param record
     * @param citizenId
     */
    @Override
    public void insert(SocialInsuranceRecord record, int citizenId) throws DALException {

    }

    @Override
    public List<SocialInsuranceRecord> getSocialInsuranceById(int citizenId) throws DALException {

        List<SocialInsuranceRecord> result = new ArrayList<>();

        String query = "SELECT * FROM SocialInsuransRecords "
                + "WHERE citizen_id = ? "
                + "ORDER BY year DESC, month DESC;";

        try ( Connection conn = DriverManager.getConnection( url, username, password );
                PreparedStatement stmt = conn.prepareStatement( query ) ) {

            stmt.setInt( 1, citizenId );

            try ( ResultSet rs = stmt.executeQuery() ) {

                while ( rs.next() ) {

                    int year = rs.getInt( "year" );
                    int month = rs.getInt( "month" );
                    double amount = rs.getDouble( "amount" );

                    result.add( new SocialInsuranceRecord( year, month, amount ) );
                }
            }
        } catch ( SQLException ex ) {
            throw new DALException( "Unable to get social insurance records for citizen with Id = " 
                    + citizenId, ex );
        }
        return result;
    }
}
