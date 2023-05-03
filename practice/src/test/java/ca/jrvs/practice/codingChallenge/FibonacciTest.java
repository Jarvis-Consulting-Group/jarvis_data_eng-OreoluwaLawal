package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {
    private FibonacciNumber fibonacciNumber;

    @Before
    public void setup(){
        this.fibonacciNumber = new FibonacciNumber();
    }

    @Test
    public void checkNumber(){
        assertEquals(5, fibonacciNumber.numberRecursion(4));
        assertEquals(5, fibonacciNumber.numberDp(4));
    }
}
