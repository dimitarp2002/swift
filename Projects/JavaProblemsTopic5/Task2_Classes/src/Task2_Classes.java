
public class Task2_Classes {
    
    public static void main(String[] args) {
        
        Person man1 = new Person();
        System.out.println(man1.getAge());
        System.out.println(man1.getName());
        
        Person man2 = new Person("Pesho", 29);
        System.out.println(man2.getAge());
        System.out.println(man2.getName());
        
        man1.setName("Gosho");
        man1.setAge(160);
        System.out.println(man1.getAge());
        System.out.println(man1.getName());
        
        
        Car car1 = new Car("Mercedes-Benz", "S220", 2009, 160);
        System.out.println(car1.taxValue());
        Car car2 = new Car("Opel", "Astra", 1996, 85);
        System.out.println(car2.taxValue());
        Car car3 = new Car("Bugatti", "Veyron", 2015, 612);
        System.out.println(car3.taxValue());
        Car car4 = new Car("Lada", "5", 1989, 75);
        System.out.println(car4.taxValue());
        
    }
    
}
