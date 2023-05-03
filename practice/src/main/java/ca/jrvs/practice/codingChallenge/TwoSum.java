package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSum {
    /**
     * Big O: O(n^2): looping through 2 times
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] bruteforce(int[] nums, int target){

        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length; j++ ){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    /**
     * Big O: O(n): looping through the array is only done once.
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumLinear(int[] nums, int target){
        List numsList = new ArrayList();

        for(int i = 0; i < nums.length; i++){
            numsList.add(nums[i]);
        }
        for(int i = 0; i < nums.length; i++){
            int val = target - nums[i];
            if(numsList.contains(val)){
               int k = numsList.indexOf(val);
               if(k != i){
                   return new int[]{i, k};
               }
            }
        }

        return null;
    }
}
