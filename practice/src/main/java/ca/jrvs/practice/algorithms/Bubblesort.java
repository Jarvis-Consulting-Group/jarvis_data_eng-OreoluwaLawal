package ca.jrvs.practice.algorithms;

import java.util.stream.IntStream;

public class Bubblesort {
    public static void main(String[] args){
        int[] val = {10,9,18,7,15,4,3,20,1};
        int[] sortedArr = sort(val);
        IntStream.of(sortedArr).boxed().forEach(i ->
                System.out.println(i));
    }

    public static int[] sort(int[] val){
           for(int i = 0; i < val.length - 1; i++){
               for(int j = 0; j < val.length - 1 - i; j++){
                   if(val[j] > val[j + 1]){
                       swap(val, j, j+1);
                   }
               }
           }
         return val;
    }
    public static int[] swap (int[] val, int fir, int sec){
        int temp = val[sec];
        val[sec] = val[fir];
        val[fir] = temp;

        return val;

    }
}
