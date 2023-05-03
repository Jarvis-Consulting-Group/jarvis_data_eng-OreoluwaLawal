package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueueUsingStackTest {
    private QueueUsingStack queueUsingStack;

    @Test
    public void twoStacksPushPop(){
        QueueUsingStack.TwoStacks twoStacks = new  QueueUsingStack.TwoStacks();
        twoStacks.push(1);
        twoStacks.push(2);
        twoStacks.push(3);
        twoStacks.push(4);
        twoStacks.push(5);
        int result = twoStacks.pop();

        assertEquals(1, result);
    }

    @Test
    public void oneQueuePushPop(){
        QueueUsingStack.OneStack oneStack = new QueueUsingStack.OneStack();
        oneStack.push(1);
        oneStack.push(2);
        oneStack.push(3);
        oneStack.push(4);
        oneStack.push(5);
        int result = oneStack.pop();

        assertEquals(1, result);
    }
}
