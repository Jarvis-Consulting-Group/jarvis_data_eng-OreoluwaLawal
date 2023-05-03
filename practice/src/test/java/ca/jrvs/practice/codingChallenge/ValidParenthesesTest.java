package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidParenthesesTest {

    @Test
    public void check(){
        ValidParentheses validParentheses = new ValidParentheses();
         assertTrue(validParentheses.isValid("(fhf(vf)vfv)"));
         assertFalse(validParentheses.isValid("{ddsd{]s)"));
    }
}
