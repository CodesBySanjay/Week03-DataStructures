import java.util.*;

class SlidingWindow{
    public static void main(String[] args) {
        int[] arr = {5,8,3,5,1,9,4,6};
        int k=6;
        Deque<Integer> dq = new LinkedList<>();
        for(int i=0;i<arr.length;i++){
            if(!dq.isEmpty() && dq.peekFirst() <= i-k) dq.removeFirst();
            while(!dq.isEmpty() && arr[i]>=arr[dq.peekLast()]) dq.removeLast();
            dq.addLast(i);
            if(i>=k-1) System.out.println(arr[dq.peek()]);
        }
    }
}