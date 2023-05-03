package ca.jrvs.practice.algorithms;

import java.util.stream.IntStream;

public class QuickSort {
    public static void main(String[] args){
        int[] val = {10,9,18,70,25,4,3,20,1};
        int[] sortedArr = quickSort(val, 0, val.length -1);
        IntStream.of(sortedArr).boxed().forEach(i ->
                System.out.println(i));
    }
    public static int[] quickSort(int[] val, int start, int end){
        if(start < end){
            int part = partition(val, start, end);

            quickSort(val, start, part - 1);
            quickSort(val, part + 1, end);

        }
        return val;
    }
    public static int partition(int[] val, int start,int  end){
        int i = start - 1;
        int index = val[end];

        for(int j = start; j < end; j++){
            if(val[j] <= index){
                i++;
                int temp = val[i];
                val[i] = val[j];
                val[j] = temp;
            }
        }
        int temp = val[i+1];
        val[i+1] = val[end];
        val[end] = temp;

        return i+1;
    }
}
