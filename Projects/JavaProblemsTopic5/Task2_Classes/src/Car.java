
public class Car {
    String brand;
    String model;
    int enginePower;
    int yearOfProduction;
    
    Car(String newBrand, String newModel,  int newYearOfProduction, int newEnginePower){
    brand= newBrand;
    model = newModel;
    enginePower = newEnginePower;
    yearOfProduction = newYearOfProduction;
    }
    
    public int insuranceCategory(){
    if((2016-yearOfProduction)<8){
        return 1;
    }else if((2016-yearOfProduction)<15){
        return 2;
    }else if((2016-yearOfProduction)<25){
        return 3;
    }else {
        return 4;
    }
    }
    
    
    
    public double taxValue(){
        int cat =this.insuranceCategory();
        double cat_base;
        switch(cat){
            case 1: cat_base=150;
                    break;
            case 2: cat_base=200;
                    break;
            case 3: cat_base=300;
                    break;
            case 4: cat_base=500;
                    break;
            default : cat_base=500;
                    break;
        }
        if(enginePower<80){
            return cat_base+=((cat_base*20)/100);        // 20%
        }else if (enginePower<140){
            return cat_base;
        }else {
            return cat_base+=((cat_base*45)/100);        // 45%
        }
    }
    
    
    
}
