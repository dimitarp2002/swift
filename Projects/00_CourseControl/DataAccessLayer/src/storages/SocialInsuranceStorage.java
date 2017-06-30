package storages;

import insurance.SocialInsuranceRecord;
import java.util.List;
import personaldetails.Citizen;

public interface SocialInsuranceStorage {

    public void insert(SocialInsuranceRecord record, int citizenId) throws DALException;

    List<SocialInsuranceRecord> getSocialInsuranceById(int citizenId) throws DALException;

    public void truncateSocialInsTable() throws DALException;
    public void Bulkinsert(String filename) throws DALException;
    public void insert(List<SocialInsuranceRecord> records, int citizenId) throws DALException;
    public void insertSIRecords(List<Citizen> citizens) throws DALException;
}
