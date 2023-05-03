package ca.jrvs.practice.codingChallenge;

public class FibonacciNumber {
    /**
    * Big O: O(n^2) time complexity, O(n) space complexity
    * Limitations: each call to the function results in two more calls to the
    function and there is excessive repetition involved resulting in this
    approach being unsuitable.
    * Stackoverflow issues: a function calls itself so many times that the space
    needed to store the variables and information associated with each call is more than can fit on the stack.
     */
    public int numberRecursion(int num){
        if(num <= 2){
            return num;
        }

        return numberRecursion(num - 1) + numberRecursion(num - 2);
    }

    /**
     * Big O: O(n) time complexity, O(n) space complexity
     * @param num
     * @return
     */

    public int numberDp(int num){
        if(num <= 2){
            return num;
        }
        int a = 1, b = 2;
        for(int i = 3; i <= num; i++){
            int sum = a + b;
            a = b;
            b = sum;
        }

        return b;
    }
}
