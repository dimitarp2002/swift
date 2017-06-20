package storages;

import education.Education;
import java.util.List;


public interface EducationStorage {

    public void insert(Education education, int citizenId) throws DALException;
    public List <Education> getEducationById(int citizenId) throws DALException;
    public void truncateEducationTable() throws DALException;

}
