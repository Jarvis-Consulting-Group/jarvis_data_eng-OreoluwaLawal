package ca.jrvs.practice.codingChallenge;

public class NumberIsEvenOdd {
    /**
     * Big-O: O(1) constant time: arithmetic operation
     * @param num
     * @return
     */
    public String isOddOrEven(int num){

        return num % 2 == 0 ? "even" : "odd";

    }
}
