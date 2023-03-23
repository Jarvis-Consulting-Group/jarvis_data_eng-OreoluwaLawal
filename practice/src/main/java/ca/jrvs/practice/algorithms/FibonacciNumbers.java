package ca.jrvs.practice.algorithms;

public class FibonacciNumbers {
    public static void main(String[] args){
        System.out.println(generate(4));
    }
    public static int generate(int n){
        if(n <= 1){
            return n;
        }
        return generate(n - 1) + generate(n - 2);
    }
}
