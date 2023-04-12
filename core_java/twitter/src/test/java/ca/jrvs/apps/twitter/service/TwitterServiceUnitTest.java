package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterDao;
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
import java.net.URISyntaxException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
    @Mock
    TwitterDao dao;
    @InjectMocks
    TwitterService service;

    @Test
    public void createTweet() throws Exception {
        String text = "Happy Easter!!!!";

        String tweetJsonStr = "{\n"
                + "  \"data\": {\n"
                + "  \"edit_history_tweet_ids\":[\"1645493943537213458\"],"
                + "  \"id\": \"1645493943537213458\","
                + "  \"text\": \"Happy Easter!!!!\"}}";
        CreateTweet createTweet = JsonParser.toObjectFromJson(tweetJsonStr, CreateTweet.class);
        when(dao.create(any())).thenReturn(createTweet);
        CreateTweet tweet = service.postTweet(text);
        assertNotNull(tweet);
        assertNotNull(tweet.getData().getText());
    }
     @Test(expected = IllegalArgumentException.class)
    public void postLongTweet() throws URISyntaxException {
        String text = "Lorem Ipsum is simply dummy text of the printing and typesetting " +
                "industry. Lorem Ipsum has been the industry's standard dummy text ever since" +
                " the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book." +
                " It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. " +
                "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with" +
                " desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
        service.postTweet(text);
     }


    @Test
    public void showTweet() throws Exception {
       // BigInteger id = new BigInteger("1645492897532321817");
        String id = "1645538695204814848";
        String[] field = {"created_at"};
                //,"id","id_str","text","entities", "coordinates","retweet_count","favorite_count","favorited","retweeted"};

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
        Tweet tweet = JsonParser.toObjectFromJson(tweetJsonStr, Tweet.class);
        when(dao.findById(any())).thenReturn(tweet);
        Tweet expectedTweet = service.showTweet(id, field);
        assertNotNull(expectedTweet);
       // assertNotNull(expectedTweet.getText());
        assertNotNull(expectedTweet.getCreated_at());

    }

    @Test
    public void deleteTweet() throws Exception{
        String id ="1645492897532321817";

        String tweetJsonStr = "{\n"
                + "\"data\": {\n"
                + "\"deleted\": true \n"
                + "}}";
        DeleteTweet tweet = JsonParser.toObjectFromJson(tweetJsonStr, DeleteTweet.class);
        when(dao.deleteById(any())).thenReturn(tweet);
        DeleteTweet expectedTweet = service.deleteTweet(id);
        assertNotNull(expectedTweet);
        assertTrue(expectedTweet.getData().isDeleted());
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTweetWithInvalidId(){
        String id = "455858444hjd999";
        service.deleteTweet(id);
    }
}
