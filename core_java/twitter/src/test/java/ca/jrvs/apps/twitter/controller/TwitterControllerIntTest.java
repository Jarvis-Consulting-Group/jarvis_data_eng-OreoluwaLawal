package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Before;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TwitterControllerIntTest {
    private TwitterDao dao;
    private TwitterService twitterService;
    private TwitterController twitterController;
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
        this.twitterService = new TwitterService(this.dao);
        this.twitterController = new TwitterController(this.twitterService);
    }
    @Test
    public void postTweet() throws URISyntaxException {
        String[] text = {"post", "It's a good day!!!!"};
        CreateTweet tweet = twitterController.postTweet(text);
        id = tweet.getData().getId();
        assertEquals(text[1], tweet.getData().getText());
    }
    @Test
    public void show(){
        String id = "1643727613033054209";
        String[] field = {"created_at","id","id_str","text","entities", "coordinates","retweet_count","favorite_count","favorited","retweeted"};
        String[] text = {"show", id, "created_at,id,id_str,text" };
        Tweet tweet = twitterController.showTweet(text);
        System.out.println(tweet.toString());
        assertEquals(id, tweet.getId().toString());
        assertNotNull(tweet.getText());
        assertNull(tweet.getCoordinates());
    }

//    @Test
//    public void delete(){
//        String id = "1645824578264899584";
//        String[] text = {"delete", id,};
//        DeleteTweet tweet = twitterController.deleteTweet(text);
//        System.out.println(tweet.toString());
//        assertTrue(tweet.getData().isDeleted());
//        assertEquals(true, tweet.getData().isDeleted());
//    }
}
