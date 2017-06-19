package citizenstoragemanagercli;

import education.Education;
import insurance.SocialInsuranceRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import personaldetails.Citizen;
import storages.CitizenStorage;
import storages.DALException;
import storages.EducationStorage;
import storages.MySqlCitizenStorage;
import storages.MySqlEducationStorage;
import storages.MySqlSocialInsuranceStorage;
import storages.SocialInsuranceStorage;

public class CitizenStorageManagerCLI {

    public static void main(String[] args) throws DALException {
        
        String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false";
        String username = "dimitar";
        String password = "123456";

        CitizenStorage storage
                = new MySqlCitizenStorage(url, username, password);
        EducationStorage storageE
                = new MySqlEducationStorage(url, username, password);
        SocialInsuranceStorage storageS
                = new MySqlSocialInsuranceStorage(url, username, password);

        //CLEAR THE TABLES
        
        String filename;
        Scanner sc;

        if (args.length > 0) {
            filename = args[0];

            try {
                sc = new Scanner(new FileReader(filename));

            } catch (FileNotFoundException ex) {
                System.out.println("File not found.");
                return;
            }
        } else {
            sc = new Scanner(System.in);
        }
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("d.M.yyyy");
        int count = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < count * 2; i++) {
            String line = sc.nextLine();
            if (i % 2 == 0) {

                String[] split = line.split(";");
//                for (String s : split) {
//                    System.out.println(s);
//                }
                String firstName = split[0].trim();
                String middleName = split[1].trim();
                String lastName = split[2].trim();
                String genderStr = split[3].trim();
                LocalDate dateOfBirth = LocalDate.parse(split[4].trim(), formatter);
                int height = Integer.parseInt(split[5].trim());
                String country = split[6].trim();
                String city = split[7].trim();
                String municipality = split[8].trim();
                String postalCode = split[9].trim();
                String street = split[10].trim();
                String streetNumber = split[11].trim();

                Integer floor = null;
                try {
                    if (split.length > 12) {
                        floor = Integer.parseInt(split[12].trim());
                    }
                } catch (NumberFormatException ex) {
                    floor = null;
                }

                Integer appNo = null;
                try {
                    if (split.length > 13) {
                        appNo = Integer.parseInt(split[13].trim());
                    }
                } catch (NumberFormatException ex) {
                    appNo = null;
                }

                int j =0;
                while (14+j<split.length) {

                    String educationType = split[14+(j++)].trim();
                    String institution_name = split[14+(j++)].trim();
                    LocalDate enrollmentDate = LocalDate.parse(split[14+(j++)].trim(), formatter);
                    LocalDate graduationDate = LocalDate.parse(split[14+(j++)].trim(), formatter);
                    Float grade= null;
                    switch (educationType) {
                        case "S" : grade = Float.parseFloat(split[14+(j++)].trim());
                        break;
                        case "M" : grade = Float.parseFloat(split[14+j++].trim());
                        break;
                        case "B" : grade = Float.parseFloat(split[14+j++].trim());
                        break;
                        case "D" : grade = Float.parseFloat(split[14+j++].trim());
                        case "P" : 
                    }

                    System.out.printf("%s %s %s %s %s %n", 
                            educationType, institution_name, enrollmentDate , graduationDate,  grade);
                }
                System.out.printf("%s %s %s %s  %s %d %s %s %s %s %s %s %d %d %n%n",
                        firstName, middleName, lastName, genderStr, dateOfBirth, height, country, city, municipality, postalCode,
                        street, streetNumber, floor, appNo);
            }
        }

        
        
        

        Citizen person = storage.getCitizenById(1);
        person.printPerson();

        List<Education> educations = storageE.getEducationById(1);
        for (Education education : educations) {
            person.addEducation(education);
        }
        person.printEducations();

        List<SocialInsuranceRecord> siRecords = storageS.getSocialInsuranceById(1);
        for (SocialInsuranceRecord siRecord : siRecords) {
            person.addSocialInsuranceRecord(siRecord);
        }
        person.printSrRecords();

    }

}
