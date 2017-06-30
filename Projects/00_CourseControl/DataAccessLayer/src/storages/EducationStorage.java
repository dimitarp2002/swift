package storages;

import education.Education;
import java.util.List;
import personaldetails.Citizen;

public interface EducationStorage {

    public void insert(Education education, int citizenId) throws DALException;

    public List<Education> getEducationById(int citizenId) throws DALException;

    public void truncateEducationTable() throws DALException;

    public void Bulkinsert(String filename) throws DALException;

    public void insert(List<Education> educations, int citizenId) throws DALException;

    public void insertEducations(List<Citizen> citizens) throws DALException;

}
