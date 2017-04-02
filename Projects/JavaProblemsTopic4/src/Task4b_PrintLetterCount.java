import java.util.Scanner;


public class Task4b_PrintLetterCount {
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");

        String str = sc.nextLine();

        int count;
        
        String lowerStr = str.toLowerCase();
        
        for (char ch ='a'; ch<'z';ch++){
            count=0;
            for (int i=0;i<lowerStr.length();i++){
                if(ch==lowerStr.charAt(i)){
                count++;
                }
            }
            if (count>0){
                System.out.printf("%c (%d) %n", ch, count);
            }
        }

    }
    
}
