package businessmanagers;

import insurance.SocialInsuranceRecord;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import storages.DALException;
import storages.MySqlSocialInsuranceStorage;
import storages.SocialInsuranceStorage;

public class MyBusinessManagerSIRecords implements BusinessManagerSIRecords {

    String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    String username = "dimitar";
    String password = "123456";

    SocialInsuranceStorage storageSocialInsurance;

    public MyBusinessManagerSIRecords() throws DALException {

        try {
            storageSocialInsurance = new MySqlSocialInsuranceStorage(url, username, password);

        } catch (SQLException ex) {
            throw new DALException("Unable to open storage", ex);
        }

    }

    @Override
    public List<SocialInsuranceRecord> getSiRecords(String citizenId) throws DALException {
        List<SocialInsuranceRecord> records = new ArrayList<>();
        if (citizenId != null) {
            int receivedId = Integer.parseInt(citizenId);
            records = storageSocialInsurance.getSocialInsuranceById(receivedId);
        }

        return records;
    }

    @Override
    public void insertSiRecord(String yearStr, String monthStr, String amountStr, String citizenId) throws DALException {
        int receivedId = 0;
        int year = 0;
        int month = 0;
        Double amount = 0.0;
        SocialInsuranceRecord newRecord = null;

        try {
            year = Integer.parseInt(yearStr);
            if (1900 > year || year > 2040) {
                throw new NumberFormatException("Year should be between 1900 and 2040");
            }
        } catch (NumberFormatException e) {
            throw new DALException("Not correct format for Integer year", e);
        }

        try {
            month = Integer.parseInt(monthStr);
            if (1 > month || month > 12) {
                throw new NumberFormatException("Month should be between 1 and 12");
            }
        } catch (NumberFormatException e) {
            throw new DALException("Not correct format for Integer month", e);
        }

        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            throw new DALException("Not correct format for Double amount", e);
        }

        if (year != 0 && month != 0 && amount != 0.0) {
            newRecord = new SocialInsuranceRecord(year, month, amount);
        }

        if (citizenId != null) {
            if (newRecord != null) {
                receivedId = Integer.parseInt(citizenId);
                storageSocialInsurance.insert(newRecord, receivedId);
            }

        } else {

        }

    }

}
