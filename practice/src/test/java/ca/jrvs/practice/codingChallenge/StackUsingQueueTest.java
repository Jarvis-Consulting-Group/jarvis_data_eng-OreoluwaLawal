package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackUsingQueueTest {
    private StackUsingQueue stackUsingQueue;
    @Before
    public void setup(){
        this.stackUsingQueue= new StackUsingQueue();
    }

    @Test
    public void twoQueuesPushPop(){
        StackUsingQueue.TwoQueues twoQueues = new StackUsingQueue.TwoQueues();
        twoQueues.push(1);
        twoQueues.push(2);
        twoQueues.push(3);
        twoQueues.push(4);
        twoQueues.push(5);
        int result = twoQueues.pop();

        assertEquals(5, result);
    }

    @Test
    public void oneQueuePushPop(){
        StackUsingQueue.OneQueue oneQueue = new StackUsingQueue.OneQueue();
        oneQueue.push(1);
        oneQueue.push(2);
        oneQueue.push(3);
        oneQueue.push(4);
        oneQueue.push(5);
        int result = oneQueue.pop();

        assertEquals(5, result);
    }

}
