import java.util.Stack;

class StockSpan{
    public static void main(String[] args) {
        int[] prices = {100,80,60,70,75,85};
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<n;i++){
            while (!stack.isEmpty() && prices[stack.peek()]<=prices[i]){
                stack.pop();
            }
            if (stack.isEmpty()) span[i] = i+1;
            else span[i] = i - stack.peek();
            stack.push(i);
        }
        for(int a : span){
            System.out.print(a+" ");
        }
    }
}
