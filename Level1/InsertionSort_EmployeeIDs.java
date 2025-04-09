import java.util.*;

public class InsertionSort_EmployeeIDs {
   public static void main(String[] args) {
       int[] emp = {2,7,5,1,6,3};
       int n = emp.length;
       for(int i=1;i<n;i++){
           int key = emp[i];
           int j = i-1;
           while(j>=0 && emp[j]>key){
               emp[j+1] = emp[j];
               j--;
           }
           emp[j+1] = key;
       }
       System.out.println("Sorted Array: "+Arrays.toString(emp));
   }
}