import java.util.Stack;

class Sorting{
    void sortStack(Stack<Integer> stack){
        if (stack.isEmpty()) return;
        int temp = stack.pop();
        sortStack(stack);
        insertInSortedOrder(stack, temp);
    }

    void insertInSortedOrder(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() > element) {
            stack.push(element);
            return;
        }
    
        int temp = stack.pop();
        insertInSortedOrder(stack, element);
        stack.push(temp);
    }
}

class Main{
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);

        Sorting s = new Sorting();
        s.sortStack(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }
}