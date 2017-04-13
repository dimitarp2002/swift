

public class Vehicle {
    
    String type;
    String model;
    int power;
    double fuelConsumption;
    double distance=0;
    int yearProduced;
    int licenseNo;
    
    
    int weight;
    String color;

    public Vehicle(String type, String model, int power, double fuelConsumption, int yearProduced, int licenseNo) {
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = fuelConsumption;
        this.yearProduced = yearProduced;
        this.licenseNo = licenseNo;
        this.weight = 0;
        this.color="N/A";
    }

    public Vehicle(String type, String model, int power, double fuelConsumption, int yearProduced, int licenseNo, int weight) {
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = fuelConsumption;
        this.yearProduced = yearProduced;
        this.licenseNo = licenseNo;
        this.weight = weight;
        this.color="N/A";
    }

    public Vehicle(String type, String model, int power, double fuelConsumption, int yearProduced, int licenseNo, String color) {
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = fuelConsumption;
        this.yearProduced = yearProduced;
        this.licenseNo = licenseNo;
        this.color = color;
        this.weight = 0;
    }

    public Vehicle(String type, String model, int power, double fuelConsumption, int yearProduced, int licenseNo, int weight, String color) {
        this.type = type;
        this.model = model;
        this.power = power;
        this.fuelConsumption = fuelConsumption;
        this.yearProduced = yearProduced;
        this.licenseNo = licenseNo;
        this.weight = weight;
        this.color = color;
    }

    public int getLicenseNo() {
        return licenseNo;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    
    
    double calculateTripPrice(double fuelPrice){
    return fuelPrice*this.distance*(this.fuelConsumption/100.0);
    }
    
    
    double getInsurancePrice() {
        int carAge=2016 -this.yearProduced;
        double typeCoefficient=1.00;
        switch(this.type){
            case "car": typeCoefficient=1.00; break;
            case "suv": typeCoefficient=1.12; break;
            case "truck": typeCoefficient=1.20; break;
            case "motorcycle": typeCoefficient=1.50; break;
        
        }
        
        return ( 0.16 * power ) * (1.25 * carAge ) *( 0.05 * fuelConsumption ) * typeCoefficient;
    
    }
    
    
    void printInfo(){
        System.out.printf("%04d - %s, %d , %s %n", this.licenseNo, this.model, this.yearProduced, this.color);
    
    }
}
