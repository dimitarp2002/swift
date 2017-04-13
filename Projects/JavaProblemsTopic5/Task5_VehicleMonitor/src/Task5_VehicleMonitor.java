
import java.util.Random;
import java.util.Scanner;

public class Task5_VehicleMonitor {

    public static String generateLicense() {
        Random rn = new Random();
        String licenseN = "";
        for (int j = 0; j < 4; j++) {
            licenseN += rn.nextInt(10);
        }
        return licenseN;
    }


    public static void main(String[] args) {

        String type = "";
        String model = "";
        int power = 0;
        double fuelConsumption = 0;
        int yearProduced = 0;
        String licenseNo = "";

        double distance = 0;

        int weight = -1;
        String color = "";

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter two digits:  ");
        int number = sc.nextInt();
        fuelConsumption = sc.nextDouble();
        sc.nextLine();
        Vehicle vehicles[] = new Vehicle[number];
        String licenses[] = new String[number];

        for (int i = 0; i < number; i++) {
            String[] split = sc.nextLine().split(",");
            weight = -1;
            color = "";
            for (int j = 0; j < split.length; j++) {
                if (j == 0) {
                    type = split[j];
                } else if (j == 1) {
                    model = split[j].trim();
                } else if (j == 2) {
                    power = Integer.parseInt(split[2].trim());
                } else if (j == 3) {
                    fuelConsumption = Double.parseDouble(split[j].trim());
                } else if (j == 4) {
                    yearProduced = Integer.parseInt(split[j].trim());
                } else if (j == 5) {
                    distance = Double.parseDouble(split[j].trim());
                } else if (j == 6) {
                    for (char c : split[j].toCharArray()) {
                        if ('0' <= c && c <= '9') {
                            weight = Integer.parseInt(split[j]);
                            break;
                        } else {
                            color = split[j];
                            break;
                        }
                    }
                } else if (j == 7) {
                    for (char c : split[j].toCharArray()) {
                        if ('0' <= c && c <= '9') {
                            weight = Integer.parseInt(split[j]);
                            break;
                        } else {
                            color = split[j];
                            break;
                        }
                    }
                }
            }

            licenseNo = generateLicense();
            for (int j = i - 1; j >= 0; j--) {
                if (vehicles[j].getLicenseNo() == licenseNo) {
                    licenseNo = generateLicense();
                    j = i;
                }
            }

            if (weight == -1 && color == "") {
                vehicles[i] = new Vehicle(type, model, power, fuelConsumption, yearProduced, licenseNo);
            } else if (weight == -1 && color != "") {
                vehicles[i] = new Vehicle(type, model, power, fuelConsumption, yearProduced, licenseNo, color);
            } else if (weight != -1 && color == "") {
                vehicles[i] = new Vehicle(type, model, power, fuelConsumption, yearProduced, licenseNo, weight);
            } else {
                vehicles[i] = new Vehicle(type, model, power, fuelConsumption, yearProduced, licenseNo, weight, color);
            }
        }

        System.out.println("");
        for (int i=0;i<vehicles.length;i++){
                vehicles[i].printInfo();
        }
        
        
    }

}
