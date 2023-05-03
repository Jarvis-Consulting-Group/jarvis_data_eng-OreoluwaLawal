package ca.jrvs.practice.algorithms;

import java.util.*;
import java.util.stream.Collectors;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int ans = getTarget(arr, 0, arr.length -1, 5);
        int ansIterative = iterativeBinarySearch(arr, 5);
        System.out.println("Found recursive: " + ans);
        System.out.println("Found iterative: " + ansIterative);

        List<Integer> arr2 = Arrays.stream(arr).boxed().collect(Collectors.toList());
        System.out.println(Collections.binarySearch(arr2, 5));
    }

    public static int getTarget(int[] arr, int start, int end, int target) {
        return recursionBinarySearch(arr, start, end, target);
    }

    public  static int recursionBinarySearch(int[] arr, int start, int end, int target) {

        if (start <= end) {
            int mid = (start + end) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (target < arr[mid]) {
                end = mid - 1;
               return recursionBinarySearch(arr, start, end, target);
            }
            if (target > arr[mid]) {
                start = mid + 1;
               return recursionBinarySearch(arr, start, end, target);
            }

        }
      return -1;
    }

    public static int iterativeBinarySearch(int[] arr,int target){
        int start = 0;
        int end = arr.length -1;
        while(start <= end){
            int mid = (start + end) / 2;
            if(arr[mid] == target){
                return mid;
            }
            if(target < arr[mid]){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }

        return -1;

    }

}