package ca.jrvs.practice.algorithms;

public class StairCase {
    public static void main(String[] args){
        System.out.println(solveRecursively(6));
        System.out.println(solveIterative(6));
    }
    public static int solveIterative(int n){
        if(n <= 2){
            return n;
        }
        int a = 1, b = 2;
        for(int i = 3; i <= n; i++){
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
    public static int solveRecursively(int n){
        if(n > 2){
           return solveRecursively(n - 1) + solveRecursively(n -2);
        }
        return n;
    }
}
