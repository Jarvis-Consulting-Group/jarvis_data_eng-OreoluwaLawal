package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class TwitterDaoIntTest {
    private TwitterDao dao;
    private String id = "";
    @Before
    public void setup(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        System.out.println("Keys: " + consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.dao = new TwitterDao(httpHelper);
    }
    @Test
    public void create(){
        String text = "Hi!!!!";
        CreateTweet tweet = dao.create(text);
        id = tweet.getData().getId();
        assertEquals(text, tweet.getData().getText());
    }

    @Test
    public void show(){
        BigInteger id = new BigInteger("1643727613033054209");
        Tweet tweet = dao.findById(id);
        System.out.println(tweet.toString());
        assertEquals(id, tweet.getId());
    }

//    @Test
//    public void delete(){
//        //BigInteger id = new BigInteger("1645493943537213458");
//        DeleteTweet tweet = dao.deleteById(new BigInteger(id));
//        System.out.println(tweet.toString());
//        assertEquals(true, tweet.getData().isDeleted());
//    }
}
