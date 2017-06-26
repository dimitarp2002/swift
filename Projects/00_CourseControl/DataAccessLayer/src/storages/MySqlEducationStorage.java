package storages;

import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import education.PrimaryEducation;
import education.SecondaryEducation;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlEducationStorage implements EducationStorage {

    private final String url;
    private final String username;
    private final String password;
    Connection con;
//    CallableStatement statement;

    public MySqlEducationStorage(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;
        con = DriverManager.getConnection(url, username, password);
//        statement = con.prepareCall("{call `sp_insert_education`(?, ?, ?, ?, ?, ?, ? )}");
    }

    @Override
    public void truncateEducationTable() throws DALException {
        try (CallableStatement statementt = con.prepareCall("{call sp_truncate_table(? )}")) {
            statementt.setString(1, "Education");
            statementt.executeQuery();
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

        try (CallableStatement statement = con.prepareCall("{call `sp_insert_education`(?, ?, ?, ?, ?, ?, ? )}")) {

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
            LocalDate graduationDate, String degree, Float grade) {

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
                SecondaryEducation eduSecondary
                        = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
                eduSecondary.gotGraduated(grade);
                result = eduSecondary;
                break;
            case "Bachelor":
            case "B":
                HigherEducation eduBachelor
                        = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                eduBachelor.gotGraduated(grade);
                result = eduBachelor;
                break;
            case "Master":
            case "M":
                HigherEducation eduMaster
                        = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Master);
                eduMaster.gotGraduated(grade);
                result = eduMaster;
                break;
            case "Doctorate":
            case "D":
                HigherEducation eduDoctorate
                        = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                eduDoctorate.gotGraduated(grade);
                result = eduDoctorate;
                break;
        }
        return result;
    }

    public void Bulkinsert(String filename) throws DALException {

        try (Statement statement = con.createStatement();) {
            Integer result = statement.executeUpdate(
                    "LOAD DATA LOCAL INFILE "
                    + "'"
                    + filename
                    + "'"
                    + "INTO TABLE  Education FIELDS TERMINATED BY ';' LINES TERMINATED BY '\n' "
            );

        } catch (SQLException ex) {

            // SQLException is actually a linked list of Exceptions
            while (ex != null) {
                System.out.println(ex.getSQLState());
                System.out.println(ex.getMessage());
                System.out.println(ex.getErrorCode());
                ex = ex.getNextException();
            }
            throw new DALException("Unable to insert Citizen", ex);
        }

    }

    @Override
    public void insert(List<Education> educations, int citizenId) throws DALException {
        try {
            if(!(educations.isEmpty())){
//                for (Education education : educations){
//                    System.out.println(education.getInstitutionName() + " " + education.getEnrollmentDate() +" " + education.getGraduationDate() + " " + education.getDegree() + " " + education.isGraduated());
//                }
            Statement statement = con.createStatement();
            StringBuilder query = new StringBuilder().append(" INSERT INTO `Education` ( `institutionName`, `enrollmentDate`, `graduationDate`, `degree`, `graduaded`,  `grade`, `citizen_id`)  VALUES ");
            for (int i=0; i<educations.size();i++) {
                int isGraduated;
                if(educations.get(i).isGraduated()){
                isGraduated = 1;
                }else{
                isGraduated = 0;
                }
                if(educations.get(i) instanceof PrimaryEducation){
                query.append("('").append(educations.get(i).getInstitutionName()).append("', '").append(educations.get(i).getEnrollmentDate()).append("', '").append(educations.get(i).getGraduationDate()).append("', '").append(educations.get(i).getDegree()).append("', ").append(isGraduated).append(", " + "NULL" + ", ").append(citizenId).append(") , ");
                }else{
//                       if ( ((GradedEducation) educations.get(i)).getFinalGrade() != null ) {
//                       
//                       }
                        query.append("('").append(educations.get(i).getInstitutionName()).append("', '").append(educations.get(i).getEnrollmentDate()).append("', '").append(educations.get(i).getGraduationDate()).append("', '").append(educations.get(i).getDegree()).append("', ").append(isGraduated).append(", ").append(((GradedEducation) educations.get(i)).getFinalGrade()).append(", ").append(citizenId).append(") , ");
                }
            }
            query.setCharAt(query.lastIndexOf(","), ';');
//            System.out.println(query.toString());
            statement.execute(query.toString());
        }
        } catch (SQLException e) {
            throw new DALException("Unable to insert Education", e);
        }

    }

}
