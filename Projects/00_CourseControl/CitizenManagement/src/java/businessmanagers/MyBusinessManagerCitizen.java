package businessmanagers;

import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import insurance.SocialInsuranceRecord;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import personaldetails.Citizen;
import storages.*;

public class MyBusinessManagerCitizen implements BusinessManagerCitizen {

    String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    String username = "dimitar";
    String password = "123456";

    CitizenStorage storageCitizen;

    BusinessManagerEducation BME = new MyBusinessManagerEducation();
    BusinessManagerSIRecords BMS = new MyBusinessManagerSIRecords();

    public MyBusinessManagerCitizen() throws DALException {

        try {
            storageCitizen = new MySqlCitizenStorage(url, username, password);

        } catch (SQLException ex) {
            throw new DALException("Unable to open storage", ex);
        }
    }

    @Override
    public Citizen getCitizen(String citizenId) throws DALException {
        Citizen citizen = null;
        int receivedId = 0;
        if (citizenId != null && !(citizenId.equalsIgnoreCase(""))) {
            receivedId = Integer.parseInt(citizenId);
            citizen = storageCitizen.getCitizenById(receivedId);

        }
        return citizen;
    }

    @Override
    public String isEligible(String citizenId) throws DALException {
        String result = "Not Eligible for Social Payments";
        boolean isEducationApt = false;
        List<Education> educations = BME.getEducations(citizenId);
        for (int i = 0; i < educations.size(); i++) {
            if (educations.get(i).isGraduated() && !(educations.get(i).getDegree() == EducationDegree.Primary)) {
                isEducationApt = true;
                break;
            }
        }

        if (isEducationApt) {
            boolean areSocialIRsApt = true;
            List<SocialInsuranceRecord> records = new ArrayList<>();
            records = BMS.getSiRecords(citizenId);
//            LocalDate date = LocalDate.now().minus(Period.of(0, 3, 0));
            LocalDate date = LocalDate.now().minusMonths(3);
            int referedYear = date.getYear();
            int referedMonth = date.getMonthValue();
            for (int j = 0; j < records.size(); j++) {
                if (records.get(j).getYear() >= referedYear
                        && records.get(j).getMonth() >= referedMonth) {
                    areSocialIRsApt = false;
                    break;
                }
            }
            if (areSocialIRsApt) {
                LocalDate startDate = date.minusYears(2);
                int startYear = startDate.getYear();
                int startMonth = startDate.getMonthValue();
                double sum = 0.0;
                for (int k = 0; k < records.size(); k++) {
                    if (records.get(k).getYear() == startYear && records.get(k).getMonth() >= startMonth) {
                        sum += records.get(k).getAmount();
                    }
                    
                    if (records.get(k).getYear() > startYear && records.get(k).getYear() < referedYear) {
                        sum += records.get(k).getAmount();
                    }
                    
                    if (records.get(k).getYear() == referedYear && records.get(k).getMonth() < referedMonth) {
                        sum += records.get(k).getAmount();
                    }

                }
                result = String.format("Eligible for social payments with deserved salary %.2f %n", sum / 24);
            }

        }
    
    return result ;
}

}
