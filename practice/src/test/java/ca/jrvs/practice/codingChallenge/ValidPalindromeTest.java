package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidPalindromeTest {
    @Test
    public void twoPointers(){
        ValidPalindrome validPalindrome = new ValidPalindrome();

        assertTrue(validPalindrome.isPalindrome("Lawal"));
        assertFalse(validPalindrome.isPalindrome("Lawdkwal"));
    }
    @Test
    public void recursively(){
        ValidPalindrome validPalindrome = new ValidPalindrome();

        assertTrue(validPalindrome.isValid("Lawal"));
        assertFalse(validPalindrome.isValid("Lawdkwal"));
    }
}
