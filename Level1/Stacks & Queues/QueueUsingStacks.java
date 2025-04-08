import java.util.Stack;

class QueuesUsingStacks{
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    void enqueue(int x){
        stack1.push(x);
    }

    int dequeue(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) return -1;
        return stack2.pop();
    }
}

class Main{
    public static void main(String[] args){
        QueuesUsingStacks q = new QueuesUsingStacks();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}