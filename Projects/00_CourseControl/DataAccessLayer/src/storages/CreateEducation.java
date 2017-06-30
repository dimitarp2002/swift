package storages;

import education.Education;
import education.EducationDegree;
import education.GradedEducation;
import education.HigherEducation;
import education.PrimaryEducation;
import education.SecondaryEducation;
import java.time.LocalDate;

public class CreateEducation {

    public static Education createEducation(String institutionName, LocalDate enrollmentDate,
            LocalDate graduationDate, String degree, Float grade) throws DALException {

        Education result = null;
        try {
            switch (degree) {

                case "Primary":
                case "P":
                    PrimaryEducation eduPrimary = new PrimaryEducation(institutionName, enrollmentDate, graduationDate);
                    checkRulesPrimaryEducation(eduPrimary, grade);
                    result = eduPrimary;
                    break;

                case "Secondary":
                case "S":
                    SecondaryEducation eduSecondary
                            = new SecondaryEducation(institutionName, enrollmentDate, graduationDate);
                    checkRulesGradedEducation(eduSecondary, grade);
                    result = eduSecondary;
                    break;
                case "Bachelor":
                case "B":
                    HigherEducation eduBachelor
                            = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Bachelor);
                    checkRulesGradedEducation(eduBachelor, grade);
                    result = eduBachelor;
                    break;

                case "Master":
                case "M":
                    HigherEducation eduMaster
                            = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Master);

                    checkRulesGradedEducation(eduMaster, grade);
                    result = eduMaster;
                    break;
                case "Doctorate":
                case "D":
                    HigherEducation eduDoctorate
                            = new HigherEducation(institutionName, enrollmentDate, graduationDate, EducationDegree.Doctorate);
                    checkRulesGradedEducation(eduDoctorate, grade);
                    result = eduDoctorate;
                    break;
            }
        } catch (IllegalArgumentException | IllegalStateException ex) {
            throw new DALException("Mismatched values for Education", ex);
        }
        return result;
    }

    private static void checkRulesGradedEducation(GradedEducation education, Float grade) throws DALException {

        if (education.getGraduationDate().isAfter(LocalDate.now())) {
            if (grade == null) {
                education.setGraduated(false);
            } else {
                throw new DALException(education.getDegree() + " Education graduation date is in the future."
                        + " It should not have a grade");
            }
        } else if (grade == null) {
            throw new DALException(education.getDegree() + " Education graduation date is in the past. "
                    + "Grade shoud be provided");
        } else {
            education.gotGraduated(grade);
        }

    }

    private static void checkRulesPrimaryEducation(PrimaryEducation education, Float grade) throws DALException {
        if (grade != null) {
            throw new DALException(education.getDegree() + " Education should not hava a grade");
        }
        if (education.getGraduationDate().isAfter(LocalDate.now())) {
            education.setGraduated(false);
        } else {
            education.gotGraduated();
        }

    }

}
