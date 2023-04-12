package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class TwitterServiceIntTest {
    private TwitterDao dao;
    private TwitterService twitterService;
    @Before
    public void setup(){
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        System.out.println("Keys: " + consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        this.dao = new TwitterDao(httpHelper);
        this.twitterService = new TwitterService(this.dao);
    }
    @Test
    public void postTweet() throws URISyntaxException {
        String text = "Happy Easter!!!!";
        CreateTweet tweet = twitterService.postTweet(text);
        assertEquals(text, tweet.getData().getText());
    }
    @Test
    public void show(){
        String id = "1643727613033054209";
        String[] field = {"created_at","id","id_str","text","entities", "coordinates","retweet_count","favorite_count","favorited","retweeted"};

        Tweet tweet = twitterService.showTweet(id, field);
        System.out.println(tweet.toString());
        assertEquals(id, tweet.getId().toString());
        assertNotNull(tweet.getText());
    }

    @Test
    public void delete(){
        String id = "1645538695204814848";
        DeleteTweet tweet = twitterService.deleteTweet(id);
        System.out.println(tweet.toString());
        assertTrue(tweet.getData().isDeleted());
        assertEquals(true, tweet.getData().isDeleted());
    }
}
