package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EvenOddTest {

    private NumberIsEvenOdd numberIsEvenOdd;
    @Before
    public void setup(){
        this.numberIsEvenOdd = new NumberIsEvenOdd();
    }


    @Test
    public void isEvenOdd1(){
        String ans = numberIsEvenOdd.isOddOrEven(1);

        assertEquals("odd", ans);
    }

    @Test
    public void isEvenOdd2(){
        String ans = numberIsEvenOdd.isOddOrEven(8);

        assertEquals("even", ans);
    }
}
