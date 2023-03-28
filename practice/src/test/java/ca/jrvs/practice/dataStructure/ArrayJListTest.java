package ca.jrvs.practice.dataStructure;

import ca.jrvs.practice.dataStructure.list.ArrayJLists;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

public class ArrayJListTest {
    private ArrayJLists<String> jList;

    @Before
    public void setUp(){
        jList = new ArrayJLists<>();
    }

    @Test
    public void add() {
        MockitoAnnotations.initMocks(this);
        jList.add("hello");
        Assert.assertEquals(jList.get(0), "hello");
    }

}
