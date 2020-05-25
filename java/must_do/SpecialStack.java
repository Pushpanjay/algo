package amzn.must_do;

import java.util.Stack;

/**
 * @author pushpanjay.kumar created on 23/3/20
 */
public class SpecialStack extends Stack<Integer> {
    Stack<Integer> min = new Stack<>();

    public void push(int data){
        if(isEmpty() == true){
            super.push(data);
            min.push(data);
        } else{
            super.push(data);
            int y = min.peek();
            if(data<y){
                min.push(data);
            } else{
                min.push(y);
            }
        }
    }

    public Integer pop(){
        int x = super.pop();
        min.pop();
        return x;
    }

    public void pushSpaceOptimized(int data){
        if(isEmpty() == true){
            super.push(data);
            min.push(data);
        } else{
            super.push(data);
            int y = min.peek();
            if(data<=y){
                min.push(data);
            }
        }
    }

    public Integer popSpaceOptimized(){
        int y = super.pop();
        if(min.peek() == y){
            min.pop();
        }
        return y;
    }

    int getMin(){
        return min.peek();
    }

    public static void main(String[] args) {
        SpecialStack s = new SpecialStack();

        /*s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.getMin());
        s.push(5);
        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());
         */


        s.pushSpaceOptimized(10);
        s.pushSpaceOptimized(20);
        s.pushSpaceOptimized(30);
        System.out.println(s.getMin());
        s.pushSpaceOptimized(5);
        System.out.println(s.getMin());
        s.popSpaceOptimized();
        System.out.println(s.getMin());


    }
}
