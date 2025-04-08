import java.util.HashMap;

class ZeroSumSubarrays{
    public static void main(String[] args){
        int[] arr = {3,4,-7,1,2,6,-8};
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0; 
        int count = 0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
            if(sum==0) count++;
            if (map.containsKey(sum)) count+=map.get(sum);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        System.out.println("Total zero-sum subarrays: "+count);
    }
}