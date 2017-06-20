package storages;

import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import education.PrimaryEducation;
import education.SecondaryEducation;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
    public void truncateEducationTable() throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call sp_truncate_table(? )}")) {
            statement.setString(1, "Education");
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

    @Override
    public void insert(Education education, int citizenId) throws DALException {

        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call `sp_insert_education`(?, ?, ?, ?, ?, ?, ? )}")) {

            statement.setString("p_institutionName", education.getInstitutionName());
            statement.setDate("p_enrollmentDate", Date.valueOf(education.getEnrollmentDate()));
            statement.setDate("p_graduationDate", Date.valueOf(education.getGraduationDate()));
            statement.setString("p_degree", education.getDegree().toString());
            statement.setBoolean("p_graduaded", education.isGraduated());
            if (education instanceof PrimaryEducation) {
                statement.setNull("p_grade", Types.DOUBLE);
            } else {
                statement.setFloat("p_grade", ((GradedEducation) education).getFinalGrade());
            }
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

                    result.add(createEducation(institutionName, enrollmentDate, graduationDate, degree, grade));
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Unable to get Educations for citizen with Id = "
                    + citizenId, ex);
        }
        return result;

    }

    public static Education createEducation(String institutionName, LocalDate enrollmentDate,
            LocalDate graduationDate, String degree, float grade) {

        Education result = null;
        switch (degree) {
            case "Primary":
            case "P":
                PrimaryEducation eduPrimary = new PrimaryEducation(institutionName, enrollmentDate, graduationDate);
                eduPrimary.gotGraduated();
                result = eduPrimary;
                break;
            case "Secondary":
            case "S":
                SecondaryEducation eduSecondary = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
                eduSecondary.gotGraduated(grade);
                result = eduSecondary;
                break;
            case "Bachelor":
            case "B":
                HigherEducation eduBachelor = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                eduBachelor.gotGraduated(grade);
                result = eduBachelor;
                break;
            case "Master":
            case "M":
                HigherEducation eduMaster = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                eduMaster.gotGraduated(grade);
                result = eduMaster;
                break;
            case "Doctorate":
            case "D":
                HigherEducation eduDoctorate = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                eduDoctorate.gotGraduated(grade);
                result = eduDoctorate;
                break;
        }
        return result;
    }

//    public static Education createEducation(String institutionName, LocalDate enrollmentDate, 
//            LocalDate graduationDate) {
//        Education result=null;
//        float grade = 0f;
//        String degree = "P";
//        return createEducation(institutionName, enrollmentDate, graduationDate, degree, grade);
//     
//    }
}
