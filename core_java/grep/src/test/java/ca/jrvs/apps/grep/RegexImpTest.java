package ca.jrvs.apps.grep;

import ca.jrvs.apps.practice.RegexExc;
import ca.jrvs.apps.practice.RegexExcImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RegexImpTest {
    @Test
    public void matchJpeg(){
        RegexExc regexExc = new RegexExcImp();

        assertEquals(true, regexExc.matchJpeg("oreo.jpg"));
    }

    @Test
    public void matchIp(){
        RegexExc regexExc = new RegexExcImp();
        assertEquals(false, regexExc.matchIp("0.0"));
    }

    @Test
    public void isEmptyLine(){
        RegexExc regexExc = new RegexExcImp();
        assertEquals(true, regexExc.isEmptyLine(""));
    }
}
