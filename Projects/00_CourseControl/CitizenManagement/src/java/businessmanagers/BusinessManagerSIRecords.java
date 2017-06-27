
package businessmanagers;

import insurance.SocialInsuranceRecord;
import java.util.List;
import storages.DALException;


public interface BusinessManagerSIRecords {
    public List<SocialInsuranceRecord> getSiRecords(String citizenId) throws DALException;
    public void insertSiRecord(String yearStr, String monthStr, String amountStr,  String citizenId) throws DALException;
    
}
