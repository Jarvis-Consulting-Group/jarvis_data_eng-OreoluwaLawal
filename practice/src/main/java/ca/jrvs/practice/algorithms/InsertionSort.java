package ca.jrvs.practice.algorithms;

import java.util.stream.IntStream;

public class InsertionSort {
    public static void main(String[] args){
        int[] val = {10,9,18,70,15,4,3,20,1};
        int[] sortedArr = sort(val);
        IntStream.of(sortedArr).boxed().forEach(i ->
                System.out.println(i));
    }
    public static int[] sort(int[] val){
        for(int i = 1; i <= val.length - 1; i++){
                   for(int j = i; j >= 1; j--){
                       if(val[j] < val[j - 1]){
                           int temp = val[j-1];
                           val[j-1] = val[j];
                           val[j] = temp;
                       }
                   }
               }
             return val;
    }
}
