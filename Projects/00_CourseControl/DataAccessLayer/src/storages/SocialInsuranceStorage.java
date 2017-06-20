package storages;

import insurance.SocialInsuranceRecord;
import java.util.List;

public interface SocialInsuranceStorage {

    public void insert(SocialInsuranceRecord record, int citizenId) throws DALException;

    List<SocialInsuranceRecord> getSocialInsuranceById(int citizenId) throws DALException;

    public void truncateSocialInsTable() throws DALException;
}
