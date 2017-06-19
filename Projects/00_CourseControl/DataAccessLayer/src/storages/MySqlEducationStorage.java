package storages;

import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import education.PrimaryEducation;
import education.SecondaryEducation;
import insurance.SocialInsuranceRecord;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlEducationStorage implements EducationStorage {

    private final String url;
    private final String username;
    private final String password;

    public MySqlEducationStorage(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void insert(Education education) throws DALException {

    }

    @Override
    public List<Education> getEducationById(int citizenId) throws DALException {
        List<Education> result = new ArrayList<>();

        String query = "SELECT * FROM Education "
                + "WHERE citizen_id = ? "
                + "ORDER BY `graduationDate` DESC ";

        try (Connection conn = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, citizenId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    String institutionName = rs.getString("institutionName");
                    LocalDate enrollmentDate = rs.getDate("enrollmentDate").toLocalDate();
                    LocalDate graduationDate = rs.getDate("graduationDate").toLocalDate();
                    String degree = rs.getString("degree");
                    boolean graduaded = rs.getBoolean("graduaded");
                    float grade = rs.getFloat("grade");

                    switch (degree) {
                        case "Primary":
                            Education eduPrimary = new PrimaryEducation(institutionName, enrollmentDate, graduationDate);
                            eduPrimary.setGraduated(graduaded); 
                            result.add(eduPrimary);
                            break;
                        case "Secondary":
                            Education eduSecondary  = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
                            eduSecondary.setGraduated(graduaded);
                            ((GradedEducation)eduSecondary).setFinalGrade(grade); 
                            result.add(eduSecondary);
                            break;
                        case "Bachelor":
                            Education eduBachelor = new HigherEducation(institutionName, enrollmentDate, graduationDate,EducationDegree.Bachelor); 
                            eduBachelor.setGraduated(graduaded); 
                            ((GradedEducation)eduBachelor).setFinalGrade(grade); 
                            result.add(eduBachelor);
                            break;
                        case "Master":
                            Education eduMaster = new HigherEducation(institutionName, enrollmentDate, graduationDate,EducationDegree.Bachelor); 
                            eduMaster.setGraduated(graduaded); 
                            ((GradedEducation)eduMaster).setFinalGrade(grade); 
                            result.add(eduMaster);
                            break;
                        case "Doctorate":
                            Education eduDoctorate = new HigherEducation(institutionName, enrollmentDate, graduationDate,EducationDegree.Bachelor); 
                            eduDoctorate.setGraduated(graduaded); 
                            ((GradedEducation)eduDoctorate).setFinalGrade(grade); 
                            result.add(eduDoctorate);
                            break;
                        case "None":
                            break;

                    }

                }
            }
        } catch (SQLException ex) {
            throw new DALException("Unable to get Educations for citizen with Id = "
                    + citizenId, ex);
        }
        return result;

    }

}
