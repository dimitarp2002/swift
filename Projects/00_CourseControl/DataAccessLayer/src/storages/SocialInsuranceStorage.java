package storages;

import insurance.SocialInsuranceRecord;
import java.util.List;

public interface SocialInsuranceStorage {
    
    public void get();
    public void insert(SocialInsuranceRecord record, int citizenId) throws DALException;
    List <SocialInsuranceRecord> get (int citizenId) throws DALException;
    
}
