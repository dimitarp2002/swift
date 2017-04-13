
public class Employee {
    
    String name;
    String position;
    String department;
    double salary;
    
    int age;
    String email;


    

    public Employee(String name, String position, String department, double salary, int age, String email) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.email = email;
    }

    public Employee(String name, String position, String department, double salary) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.age = 0;
        this.email="";
    }

    public Employee(String name, String position, String department, double salary, int age) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.email="";
    }

    public Employee(String name, String position, String department, double salary, String email) {
        this.name = name;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.email = email;
        this.age=0;
    }
    
    
    
    
    public void printInfo(){
        System.out.print(this.name+", "+this.department+", "+this.position);
        if(this.email!=""){
            System.out.println(this.email);
        }else System.out.println();
    }

    public double getSalary() {
        return salary;
    }
    
    
    
    
}
