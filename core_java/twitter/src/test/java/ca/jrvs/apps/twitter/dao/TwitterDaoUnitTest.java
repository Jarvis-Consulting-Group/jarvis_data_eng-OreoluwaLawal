package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigInteger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TwitterDaoUnitTest {
    @Mock
    HttpHelper mockHelper;
    @InjectMocks
    TwitterDao dao;

    @Test
    public void createTweet() throws Exception {
        String text = "I am new to twitter!";
        when(mockHelper.httpPost(isNotNull(), isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.create(text);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "  \"data\": {\n"
                + "  \"edit_history_tweet_ids\":[\"1645493943537213458\"],"
                + "  \"id\": \"1645493943537213458\","
                + "  \"text\": \"Happy Easter!!!!\"}}";

        when(mockHelper.httpPost(isNotNull(), isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        CreateTweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, CreateTweet.class);
        doReturn(expectedTweet).when(spyDao).parseCreateTweetResponse(any(), anyInt());
        CreateTweet tweet = spyDao.create(text);
        assertNotNull(tweet);
        assertNotNull(tweet.getData().getText());
    }

    @Test
    public void showTweet() throws Exception {
        BigInteger id = new BigInteger("1645492897532321817");
        when(mockHelper.httpGet(isNotNull())).thenThrow(new RuntimeException("mock"));
        try {
            dao.findById(id);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "  \"created_at\":\"Mon Apr 10 18:24:13 +0000 2023\",\n"
                + "  \"id\":1645492897532321817, \n"
                + "  \"id_str\":\"1645492897532321817\",\n"
                + "  \"text\":\"Happy Easter\",\n"
                + "  \"entities\": {\n" +
                "\"hashtags\":[], \"user_mentions\":[]},\n" +
                "\"coordinates\":null," +
                "\"retweet_count\":0,\n" +
                "\"favorite_count\":0, \n" +
                "\"favorited\":false,\n" +
                "\"retweeted\":false\n" +
                "}";

        when(mockHelper.httpGet(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        Tweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        doReturn(expectedTweet).when(spyDao).parseShowTweetResponse(any(), anyInt());
        Tweet tweet = spyDao.findById(id);
        assertNotNull(tweet);
        assertNotNull(tweet.getText());
    }

    @Test
    public void deleteTweet() throws Exception{
        BigInteger id = new BigInteger("1645492897532321817");
        when(mockHelper.httpDelete(isNotNull())).thenThrow(new RuntimeException("mock"));
        try{
            dao.deleteById(id);
            fail();
        }catch (RuntimeException e){
            assertTrue(true);
        }

        String tweetJsonStr = "{\n"
                + "\"data\": {\n"
                + "\"deleted\": true \n"
                + "}}";

        when(mockHelper.httpDelete(isNotNull())).thenReturn(null);
        TwitterDao spyDao = Mockito.spy(dao);
        DeleteTweet expectedTweet = JsonParser.toObjectFromJson(tweetJsonStr, DeleteTweet.class);
        doReturn(expectedTweet).when(spyDao).parseDeleteTweetResponse(any(), anyInt());
        DeleteTweet tweet = spyDao.deleteById(id);
        assertNotNull(tweet);
        assertNotNull(tweet.getData().isDeleted());
    }
}

