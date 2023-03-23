package ca.jrvs.practice.algorithms;

import java.util.stream.IntStream;

public class MergeSort {
    public static void main(String[] args){
        int[] val = {10,9,18,70,25,4,3,20,1};
        mergeSort(val);
        IntStream.of(val).boxed().forEach(i ->
                System.out.println(i));
    }

    public static void mergeSort(int[] val){
        if(val.length > 1) {
            int len = (val.length) / 2;
            int[] left = new int[len];
            int[] right = new int[val.length - len];
            int s = len;
            for (int i = 0; i < left.length; i++) {
                left[i] = val[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = val[s];
                s++;
            }
            IntStream.of(left).boxed().forEach(i ->
                    System.out.println("Left: " + i));
            IntStream.of(right).boxed().forEach(i ->
                    System.out.println("LRight: " + i));
            mergeSort(left);
            mergeSort(right);
            merge(val, left, right);
        }
    }
    public static void merge(int[] val, int[] left, int[] right){
        int j = 0;
        int k = 0;
        int i = 0;

        while(j < left.length && k < right.length){
            if(left[j] <= right[k]){
                val[i] = left[j];
                i++;
                j++;
            }
            else{
                val[i] = right[k];
                i++;
                k++;
            }
        }

        while(j < left.length){
            val[i] = left[j];
            i++;
            j++;
        }
        while(k < right.length){
            val[i] = right[k];
            i++;
            k++;
        }
    }
}
