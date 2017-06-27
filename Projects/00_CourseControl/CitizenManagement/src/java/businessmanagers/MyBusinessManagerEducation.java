package businessmanagers;

import education.Education;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import storages.DALException;
import storages.EducationStorage;
import storages.MySqlEducationStorage;

public class MyBusinessManagerEducation implements BusinessManagerEducation {

    String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    String username = "dimitar";
    String password = "123456";

    EducationStorage storageEducation;

    public MyBusinessManagerEducation() throws DALException {

        try {
            storageEducation = new MySqlEducationStorage(url, username, password);

        } catch (SQLException ex) {
            throw new DALException("Unable to open Education storage", ex);
        }

    }

    @Override
    public List<Education> getEducations(String citizenId) throws DALException {
        List<Education> educations = new ArrayList<>();
        int receivedId = 0;
        if (citizenId != null && !(citizenId.equalsIgnoreCase(""))) {
            receivedId = Integer.parseInt(citizenId);
            try {
                educations = storageEducation.getEducationById(receivedId);
            } catch (DALException ex) {
                throw new DALException("Unable to retriev Educations for citizen with id: " + receivedId, ex);
            }
        }

        return educations;
    }

    @Override
    public void insertEducation(String institutionName, String enrollmentDate,
            String graduationDate, String degree, String isGraduated, String grade, String citizenId) throws DALException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate enrlDate;
        LocalDate gradDate;
        try{
        enrlDate = LocalDate.parse(enrollmentDate, formatter);
        gradDate = LocalDate.parse(graduationDate, formatter);
        }catch(DateTimeParseException ex){
        throw new DALException("Not correct format for Date. It should be yyyy-M-d", ex);
        }
        Float grde=null;
        if(grade.isEmpty()){
            grade = null;
        }
        try{
            if (grade != null){
        grde = Float.parseFloat(grade);
        if(grde<3 || grde>6){
            throw new NumberFormatException("The grade should be between 3 and 6");
        }
            }
        }catch(NumberFormatException ex){
            throw  new DALException("Not correct format for Float grade ", ex);
        }
        
        if(!(degree.equalsIgnoreCase("P") || degree.equalsIgnoreCase("S")   ||  degree.equalsIgnoreCase("B")  ||  
                degree.equalsIgnoreCase("M")  ||  degree.equalsIgnoreCase("D")   
                || degree.equalsIgnoreCase("Primary")  ||   degree.equalsIgnoreCase("Secondary") || degree.equalsIgnoreCase("Bachelor")   || 
                degree.equalsIgnoreCase("Master")  ||    degree.equalsIgnoreCase("Doctorate"))){
        
        throw new DALException("Not correct value for degree. It can be only one of these: "
                + " Primary, Secondary, Bachelor, Master or Doctorate");
        }
        
        Education education = MySqlEducationStorage.createEducation(institutionName, enrlDate, gradDate, degree, grde);
         if (citizenId != null) {
             int receivedId = Integer.parseInt(citizenId);
        storageEducation.insert(education, receivedId );
         }
    }

}
