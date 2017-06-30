package storages;

import education.Education;
import education.GradedEducation;
import education.PrimaryEducation;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import personaldetails.Citizen;
import static storages.CreateEducation.createEducation;

public class MySqlEducationStorage implements EducationStorage {

    private final String url;
    private final String username;
    private final String password;

    public MySqlEducationStorage(String url, String username, String password) throws SQLException {
        this.url = url;
        this.username = username;
        this.password = password;

    }

    @Override
    public void truncateEducationTable() throws DALException {
        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statementt = con.prepareCall("{call sp_truncate_table(? )}")) {
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

        try (Connection con = DriverManager.getConnection(url, username, password);
                CallableStatement statement = con.prepareCall("{call `sp_insert_education`(?, ?, ?, ?, ?, ?, ? )}")) {

            statement.setString("p_institutionName", education.getInstitutionName());
            statement.setDate("p_enrollmentDate", Date.valueOf(education.getEnrollmentDate()));
            statement.setDate("p_graduationDate", Date.valueOf(education.getGraduationDate()));
            statement.setString("p_degree", education.getDegree().toString());
            statement.setBoolean("p_graduaded", education.isGraduated());

            if (education instanceof PrimaryEducation) {
                statement.setNull("p_grade", Types.DOUBLE);
            } else if (((GradedEducation) education).getFinalGrade() == null) {
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

        try (Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, citizenId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    String institutionName = rs.getString("institutionName");
                    LocalDate enrollmentDate = rs.getDate("enrollmentDate").toLocalDate();
                    LocalDate graduationDate = rs.getDate("graduationDate").toLocalDate();
                    String degree = rs.getString("degree");
                    boolean graduaded = rs.getBoolean("graduaded");
                    Float grade = rs.getFloat("grade");
                    if (grade == 0.0) {
                        grade = null;
                    }
                    System.out.println(grade);
                    result.add(createEducation(institutionName, enrollmentDate, graduationDate, degree, grade));
                }
            }
        } catch (SQLException ex) {
            throw new DALException("Unable to get Educations for citizen with Id = "
                    + citizenId, ex);
        }
        return result;

    }

    @Override
    public void Bulkinsert(String filename) throws DALException {

        try (Connection con = DriverManager.getConnection(url, username, password);
                Statement statement = con.createStatement();) {
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
        try (Connection con = DriverManager.getConnection(url, username, password);
                Statement statement = con.createStatement();) {
            if (!(educations.isEmpty())) {

                StringBuilder query = new StringBuilder().append(" INSERT INTO `Education` ( `institutionName`, `enrollmentDate`, `graduationDate`, `degree`, `graduaded`,  `grade`, `citizen_id`)  VALUES ");
                for (int i = 0; i < educations.size(); i++) {
                    int isGraduated;
                    if (educations.get(i).isGraduated()) {
                        isGraduated = 1;
                    } else {
                        isGraduated = 0;
                    }
                    if (educations.get(i) instanceof PrimaryEducation) {
                        query.append("('").append(educations.get(i).getInstitutionName()).append("', '").append(educations.get(i).getEnrollmentDate()).append("', '").append(educations.get(i).getGraduationDate()).append("', '").append(educations.get(i).getDegree()).append("', ").append(isGraduated).append(", " + "NULL" + ", ").append(citizenId).append(") , ");
                    } else {

                        query.append("('").append(educations.get(i).getInstitutionName()).append("', '").append(educations.get(i).getEnrollmentDate()).append("', '").append(educations.get(i).getGraduationDate()).append("', '").append(educations.get(i).getDegree()).append("', ").append(isGraduated).append(", ").append(((GradedEducation) educations.get(i)).getFinalGrade()).append(", ").append(citizenId).append(") , ");
                    }
                }
                query.setCharAt(query.lastIndexOf(","), ';');
                statement.execute(query.toString());
            }
        } catch (SQLException e) {
            throw new DALException("Unable to insert Education", e);
        }

    }
    
    @Override
            public void insertEducations(List<Citizen> citizens) throws DALException {
         try (Connection con = DriverManager.getConnection(this.url, this.username, this.password);
                Statement statement = con.createStatement(); ){
            int maxNumberOfSqlEntries = 120000;
            int counter =1;
            StringBuilder query = new StringBuilder().append("INSERT INTO Education (institutionName, enrollmentDate, graduationDate, degree, graduaded, grade, citizen_id) VALUES ");
            for (int i=0; i<citizens.size();i++) {
                List<Education> educations = citizens.get(i).getEducations();
                 for (int j=0;j<educations.size();j++){
                     int isGraduated;
                    if (educations.get(j).isGraduated()) {
                        isGraduated = 1;
                    } else {
                        isGraduated = 0;
                    }
                    String gradeInSQLtable = "NULL";
                     if (!(educations.get(j) instanceof PrimaryEducation)) {
                         if(!(((GradedEducation)educations.get(j)).getFinalGrade()==null)){
                        gradeInSQLtable= ((GradedEducation)educations.get(j)).getFinalGrade().toString();
                         }
                    }
                    
                query.append("\n('"+ educations.get(j).getInstitutionName() +  "', '" + educations.get(j).getEnrollmentDate() + "', '" + educations.get(j).getGraduationDate() + "', '"+ educations.get(j).getDegree() + "', " + isGraduated + ", " + gradeInSQLtable + ", " +(i+1) +  " ), ");
                counter++;
            }
                 if(counter>maxNumberOfSqlEntries){
                 query.setCharAt(query.lastIndexOf(","), ';');
                  statement.execute(query.toString());
                  counter = 1;
                  query.setLength(0);
                  query.append("INSERT INTO Education (institutionName, enrollmentDate, graduationDate, degree, graduaded, grade, citizen_id) VALUES ");
                  
                 }
            }
            query.setCharAt(query.lastIndexOf(","), ';');
//            PrintWriter personW=null;
//            try{
//            personW = new PrintWriter("/home/dimitar/Downloads/failed_bulk_insert.txt", "UTF-8");
//            } catch (Exception exe ) {
//                System.out.println("Bye");
//            }
//            personW.printf(query.toString());
            statement.execute(query.toString());
            
            
        } catch (SQLException e) {
            throw new DALException("Unable to insert Educations",e);
        }
        
    }
    
    
    

}
