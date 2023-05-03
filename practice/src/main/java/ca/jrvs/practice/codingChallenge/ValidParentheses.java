package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Stack;

public class ValidParentheses {

    /**
     * Big O: O(n), we have to iterate through each character in the string
     * @param val
     * @return
     */
    public boolean isValid(String val){
        Stack<Character> check = new Stack<>();

        for(int i = 0; i < val.length(); i++){
            if(val.charAt(i) == '(' || val.charAt(i) == '{' || val.charAt(i) == '['){
                check.push(val.charAt(i));
            } else if (val.charAt(i) == ')' && !check.isEmpty() && check.peek() == '(') {

                check.pop();
            }else if (val.charAt(i) == '}' && !check.isEmpty() && check.peek() == '{') {

                check.pop();
            }else if (val.charAt(i) == ']' && !check.isEmpty() && check.peek() == '[') {

                check.pop();
            }
        }

        return check.isEmpty();
    }

}
