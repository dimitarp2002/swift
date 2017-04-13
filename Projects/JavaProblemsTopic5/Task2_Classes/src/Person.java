
public class Person {
    
    String name;
    int age;
    
    Person(){
    name="No name";
    age=-1;
    }
    
    Person(String newName){
    name=newName;
    age=-1;
    }
    
    Person(String newName, int newAge){
    name=newName;
    age=newAge;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setName(String newName) {
        this.name=newName;
    }
    public void setAge(int newAge) {
        if(0<newAge&&newAge<150){
        this.age=newAge;
        }else System.out.println("Invalid age");
    }
    
    public String introduce(){
        if(age==-1){
            if(name=="No name"){
            return "I am John Doe";
            }else{
            return "Hello, I am" + name ;
            }
        }else{
            if(name=="No name"){
            return "I am John Doe";
            }else{
            return "Hello, I am" + name + ". I am "+age+" years old.";
            }
        
        }
    
    }
}

