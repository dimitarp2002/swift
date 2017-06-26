package citizenstoragemanagercli;

import address.Address;
import insurance.SocialInsuranceRecord;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import personaldetails.Citizen;
import personaldetails.Gender;
import storages.*;
import static storages.MySqlEducationStorage.createEducation;

public class CitizenStorageManagerCLI {

    public static void main(String[] args) throws DALException {

        String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "dimitar";
        String password = "123456";

        CitizenStorage storageCitizen;
        AddressStorage storageAddress;
        EducationStorage storageEducation;
        SocialInsuranceStorage storageSocialInsurance;

        try {
            storageCitizen = new MySqlCitizenStorage(url, username, password);
            storageAddress = new MySqlAddressStorage(url, username, password);
            storageEducation = new MySqlEducationStorage(url, username, password);
            storageSocialInsurance = new MySqlSocialInsuranceStorage(url, username, password);

        } catch (SQLException ex) {
            throw new DALException("Unable to open storage", ex);
        }

        storageEducation.truncateEducationTable();
        storageCitizen.truncateCitizenTable();
        storageAddress.truncateAddressTable();
        storageSocialInsurance.truncateSocialInsTable();

        String filename;
        Scanner sc;
        List<Citizen> citizens = new ArrayList<>();
        int citizenId = 0;

        if (args.length > 0) {
            filename = args[0];

            try {
                sc = new Scanner(new FileReader(filename));

            } catch (FileNotFoundException ex) {
                throw new DALException("File not found", ex);
            }

        } else {
            sc = new Scanner(System.in);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        int count = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {
            String line = sc.nextLine();
            String[] split = line.split(";");
            String firstName = split[0].trim();
            String middleName = split[1].trim();
            String lastName = split[2].trim();
            Gender gender;
            if (split[3].trim().equalsIgnoreCase("M")) {
                gender = Gender.Male;
            } else {
                gender = Gender.Female;
            }
            LocalDate dateOfBirth = LocalDate.parse(split[4].trim(), formatter);
            int height = Integer.parseInt(split[5].trim());
            Citizen citizen = new Citizen(firstName, middleName, lastName, gender, height, dateOfBirth);

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
            citizen.setAddress(new Address(country, city, municipality, postalCode, street, streetNumber, floor, appNo));

            int j = 0;
            while (14 + j < split.length) {

                String degree = split[14 + (j++)].trim();
                String institutionName = split[14 + (j++)].trim();
                LocalDate enrollmentDate = LocalDate.parse(split[14 + (j++)].trim(), formatter);
                LocalDate graduationDate = LocalDate.parse(split[14 + (j++)].trim(), formatter);

                Float grade = null;
                try {
                    //check if there are more educations to be added:
                    if (14 + (j + 1) < split.length) {
                        //check if the next field is type of education {P,S,B,M,D} or grade:
                        if (split[14 + j].equalsIgnoreCase("P") || split[14 + j].equalsIgnoreCase("S")
                                || split[14 + j].equalsIgnoreCase("B") || split[14 + j].equalsIgnoreCase("M")
                                || split[14 + j].equalsIgnoreCase("D")) {
                            // if 14+j is type of Education then grade remains NULL
                            citizen.addEducation(createEducation(institutionName, enrollmentDate, graduationDate, degree, grade));//createEducation is defined in MySQLEducationStorage
                        } else {
                            // else 14+j is a grade and we will parse it:
                            grade = Float.parseFloat(split[14 + (j++)].trim());
                            citizen.addEducation(createEducation(institutionName, enrollmentDate, graduationDate, degree, grade));//createEducation is defined in MySQLEducationStorage
                        }
                    } else // there are no more education to be added, the current is the last
                     if (14 + (j + 1) == split.length) {
                            // grade exists and we will parse it 
                            grade = Float.parseFloat(split[14 + (j++)].trim());
                            citizen.addEducation(createEducation(institutionName, enrollmentDate, graduationDate, degree, grade)); //createEducation is defined in MySQLEducationStorage
                            break;
                        } else {
                            // grade doen'r exist, so now grade remains NULL
                            citizen.addEducation(createEducation(institutionName, enrollmentDate, graduationDate, degree, grade));//createEducation is defined in MySQLEducationStorage
                            break;
                        }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    storageEducation.truncateEducationTable();
                    storageCitizen.truncateCitizenTable();
                    storageAddress.truncateAddressTable();
                    storageSocialInsurance.truncateSocialInsTable();
                    throw new DALException("Unsuccessful parsing of lines", ex);

                }
            }

            line = sc.nextLine();
            split = line.split(";");

            List<SocialInsuranceRecord> siRecords = new ArrayList<>();
            for (int k = 0; k < split.length;) {
                int year = Integer.parseInt(split[k++].trim());
                int month = Integer.parseInt(split[k++].trim());
                double amount = Double.parseDouble(split[k++].trim());
                citizen.addSocialInsuranceRecord(new SocialInsuranceRecord(year, month, amount));

            }
            citizens.add(citizen);
        }

        storageCitizen.insert(citizens);
        for (int i = 0; i < citizens.size(); i++) {
            storageAddress.insert(citizens.get(i).getAddress(), i + 1);
            storageEducation.insert(citizens.get(i).getEducations(), i + 1);
            storageSocialInsurance.insert(citizens.get(i).getSocialInsuranceRecords(), i + 1);

        }
    }
}
