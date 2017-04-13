
public class Task1c_IndexOf {
    static int indexOf(int a[], int b) {
        for(int i=0; i<a.length; i++){
        if(a[i]==b){
            return i;
        }
        }
        return -1;
    }

    public static void main(String[] args) {
        int arr[] = {5, 6, 7, 8, 9, 10};
        int a = indexOf(arr, 7);
        System.out.println(a);
        
        

    }
}
