package climanager;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import personaldetails.Citizen;
import storages.*;
import storages.MySqlSocialInsuranceStorage;
import storages.SocialInsuranceStorage;

public class CitizenStorageManagerCLIfast {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/Citizen_and_SI_db?useSSL=false&useUnicode=true"
                + "&characterEncoding=UTF-8&autoReconnect=true";
        String username = "dimitar";
        String password = "123456";

        CitizenStorage storageCitizen
                = new MySqlCitizenStorage(url, username, password);
        AddressStorage storageAddress
                = new MySqlAddressStorage(url, username, password);
        EducationStorage storageEducation
                = new MySqlEducationStorage(url, username, password);
        SocialInsuranceStorage storageSocialInsurance
                = new MySqlSocialInsuranceStorage(url, username, password);


        storageEducation.truncateEducationTable();
        storageCitizen.truncateCitizenTable();
        storageAddress.truncateAddressTable();
        storageSocialInsurance.truncateSocialInsTable();

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
            System.out.println("Enter some value:");
            sc = new Scanner(System.in);

        }
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern("d.M.yyyy");
        int count = sc.nextInt();
        sc.nextLine();
        Citizen person = null;
        int sciId = 1;
        int educationId = 1;
        int addressId = 1;

        try {
            PrintWriter personW = new PrintWriter("/home/dimitar/Downloads/person-file-name.txt", "UTF-8");
            PrintWriter addressW = new PrintWriter("/home/dimitar/Downloads/addr-file-name.txt", "UTF-8");
            PrintWriter SCIw = new PrintWriter("/home/dimitar/Downloads/sci-file-name.txt", "UTF-8");
            PrintWriter EducationW = new PrintWriter("/home/dimitar/Downloads/edu-file-name.txt", "UTF-8");
            for (int i = 0; i < count; i++) {
                String line = sc.nextLine();

                String[] split = line.split(";");
                String floor = "\\N";
                if (split.length > 12) {
                    floor = split[12].trim();
                }
                String app_N = "\\N";
                if (split.length >= 13) {
                    app_N = split[13];
                }
                personW.printf("%d;%s;%s;%s;%s;%s;%s%n", i + 1, split[0], split[1], split[2], split[3], split[5], LocalDate.parse(split[4], formatter));
                addressW.printf("%d;%s;%s;%s;%s;%s;%s;%s;%s;%d%n", addressId++, split[6], split[7], split[8], split[9], split[10], split[11], floor, app_N, i + 1);

            int j = 0;
            while (14 + j < split.length) {

                String degree="None";
                        switch( split[14 + (j++)].trim()) {
                            case "P" : degree= "Primary";
                            break;
                            case "S" : degree= "Secondary";
                            break;
                            case "B" : degree= "Bachelor";
                            break;
                            case "M" : degree= "Master";
                            break;
                            case "D" : degree= "Doctorate";
                            break;
                        
                        }
                String institutionName = split[14 + (j++)].trim();
                LocalDate enrollmentDate = LocalDate.parse(split[14 + (j++)].trim(), formatter);
                LocalDate graduationDate = LocalDate.parse(split[14 + (j++)].trim(), formatter);
                int graduaded;
                if (graduationDate.isAfter(LocalDate.now())) {
                        graduaded = 0;
                    } else {
                        graduaded = 1;
                    }

                String grade = "\\N";
                try {
                    //check if there are more educations to be added:
                    if (14 + (j + 1) < split.length) {
                        //check if the next field is type of education {P,S,B,M,D} or grade:
                        if (split[14 + j].equalsIgnoreCase("P") || split[14 + j].equalsIgnoreCase("S")
                                || split[14 + j].equalsIgnoreCase("B") || split[14 + j].equalsIgnoreCase("M")
                                || split[14 + j].equalsIgnoreCase("D")) {
                            // if 14+j is type of Education then grade remains NULL
                            EducationW.printf("%d;%s;%s;%s;%s;%d;%s;%d;%n", 
                                    educationId++,  institutionName, enrollmentDate, graduationDate, degree, graduaded,grade, i+1 );
                        } else {
                            // else 14+j is a grade and we will parse it:
                            grade = split[14 + (j++)].trim();
                            EducationW.printf("%d;%s;%s;%s;%s;%d;%s;%d;%n", 
                                    educationId++,  institutionName, enrollmentDate, graduationDate, degree, graduaded,grade, i+1 );
                        }
                    } else // there are no more education to be added, the current is the last
                     if (14 + (j + 1) == split.length) {
                            // grade exists and we will parse it 
                            grade = split[14 + (j++)].trim();
                            EducationW.printf("%d;%s;%s;%s;%s;%d;%s;%d;%n", 
                                    educationId++,  institutionName, enrollmentDate, graduationDate, degree, graduaded,grade, i+1 );
                            break;
                        } else {
                            // grade doen'r exist, so now grade remains NULL
                            EducationW.printf("%d;%s;%s;%s;%s;%d;%s;%d;%n", 
                                    educationId++,  institutionName, enrollmentDate, graduationDate, degree, graduaded,grade, i+1 );
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

                for (int k = 0; k < split.length; k += 3) {

                    SCIw.printf("%d;%s;%s;%s;%d%n", sciId++, split[k], split[k + 1], split[k + 2], i + 1);
                }

            }
            EducationW.close();
            addressW.close();
            SCIw.close();
            personW.close();
        } catch (IOException e) {
            throw new DALException("Unsuccessful", e);
        }

        storageCitizen.Bulkinsert("/home/dimitar/Downloads/person-file-name.txt");
        storageAddress.Bulkinsert("/home/dimitar/Downloads/addr-file-name.txt");
        storageEducation.Bulkinsert("/home/dimitar/Downloads/edu-file-name.txt");
        storageSocialInsurance.Bulkinsert("/home/dimitar/Downloads/sci-file-name.txt");

    }

}
