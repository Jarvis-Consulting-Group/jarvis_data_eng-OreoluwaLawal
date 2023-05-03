package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TwoSumTest {
    private TwoSum twoSum;

    @Before
    public void setup(){
        this.twoSum = new TwoSum();
    }

    @Test
    public void twoSum(){
        int[] nums = new int[]{2,7,11,15};
        int[] ans = new int[]{0,1};
        int[] res = twoSum.bruteforce(nums, 9);
        assertTrue(ans[0] == res[0] );
    }

    @Test
    public void twoSum1(){
        int[] nums = new int[]{2,7,11,15};
        int[] ans = new int[]{0,1};
        int[] res = twoSum.twoSumLinear(nums, 9);
        assertTrue(ans[0] == res[0] );
    }

    @Test
    public void twoSum3(){
        int[] nums = new int[]{3,2,4};
        int[] ans = new int[]{1,2};
        int[] res = twoSum.bruteforce(nums, 6);
        assertTrue(ans[0] == res[0] );
    }
    @Test
    public void twoSum4(){
        int[] nums = new int[]{3,2,4};
        int[] ans = new int[]{1,2};
        int[] res = twoSum.twoSumLinear(nums, 6);
        assertTrue(ans[0] == res[0] );
    }
}
