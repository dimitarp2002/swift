
public class Task1e_Print2dArray {
    
     public static void main(String[] args) {
         
        int arr[][] = new int[4][4];
        
        int indx=1;
        
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                arr[i][j]=indx++;
                
            }
        
        }
        
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                System.out.printf("%4d",arr[i][j]);
                
            }
            System.out.println("");
        }
        
        

    }
    
}
