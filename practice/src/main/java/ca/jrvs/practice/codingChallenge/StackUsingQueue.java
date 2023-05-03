package ca.jrvs.practice.codingChallenge;

import java.util.*;

public class StackUsingQueue {
    public static class TwoQueues {
        public Queue<Integer> queue1 = new LinkedList<>();
        public Queue<Integer> queue2 = new LinkedList<>();

        /**
         *  Big O: O(1),
         * @param x
         */
        public void push(int x) {
            queue1.add(x);
        }

        /**
         *  Big O: O(n), iteration is done to remove from a list and add to another list
         */
        public int pop() {
            while (queue1.size() > 1) {
                queue2.add(queue1.remove());
            }
            int removed = queue1.remove();
            queue1 = queue2;
            queue2 = new LinkedList<>();

            return removed;
        }

    }
    public  static class OneQueue{
        public Queue<Integer> list = new LinkedList<>();
        int top;
        /**
         *  Big O: O(1),
         * @param x
         */
        public void push(int x){
            list.add(x);
        }

        /**
         *  Big O: O(n), iteration is done to remove from a list and add to another list
         */
        public int pop(){
            if(list.size() == 0){
                return -1;
            }
            int removed = list.peek();
            top = list.remove();
            while(list.peek() != removed){
                list.add(top);
                top = list.remove();
            }
            return top;
        }


    }

}
