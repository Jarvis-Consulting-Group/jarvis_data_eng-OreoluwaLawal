package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

public class QueueUsingStack {
    public static class TwoStacks{

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        /**
         *  Big O: O(n), iteration is done to remove from a list and add to another list
         */
        public void push(int x){
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            stack1.push(x);
            while(!stack2.isEmpty()){
                stack1.push(stack2.pop());
            }
        }
        /**
         *  Big O: O(1),
         */
        public int pop(){
            return stack1.pop();
        }
    }

    public static class OneStack {

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        /**
         *  Big O: O(1),
         */
        public void push(int x){

            stack1.push(x);

        }

        /**
         *  Big O: O(1): we first check if the stack2 is empty then we load it ,
         */
        public int pop(){
            if(stack2.isEmpty()){
                while(!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

    }
}
