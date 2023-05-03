package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CompareTwoMapaTest {
    private CompareTwoMaps compareTwoMaps;

    @Before
    public void setup(){
        this.compareTwoMaps = new CompareTwoMaps();
    }

    @Test
    public void compare1(){
        Map map1 = new HashMap();
        map1.put(1, "one");
        map1.put(2, "two");

        Map map2 = new HashMap();
        map2.put(3, "one");
        map2.put(4, "two");

        assertFalse(compareTwoMaps.compareMaps(map1, map2));

    }

    @Test
    public void compare2(){
        Map map1 = new HashMap();
        map1.put(1, "one");
        map1.put(2, "two");

        Map map2 = new HashMap();
        map2.put(1, "one");
        map2.put(2, "two");

        assertTrue(compareTwoMaps.compareMaps(map1, map2));

    }

    @Test
    public void compare3(){
        Map map1 = new HashMap();
        map1.put(1, "one");
        map1.put(2, "two");

        Map map2 = new HashMap();
        map2.put(3, "one");
        map2.put(4, "two");

        assertFalse(compareTwoMaps.compareMaps2(map1, map2));

    }

    @Test
    public void compare4(){
        Map map1 = new HashMap();
        map1.put(1, "one");
        map1.put(2, "two");

        Map map2 = new HashMap();
        map2.put(1, "one");
        map2.put(2, "two");

        assertTrue(compareTwoMaps.compareMaps2(map1, map2));

    }
}
