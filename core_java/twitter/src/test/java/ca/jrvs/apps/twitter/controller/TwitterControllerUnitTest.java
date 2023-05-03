package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URISyntaxException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

    @Mock
    TwitterService service;
    @InjectMocks
    TwitterController twitterController;

    @Test
    public void createTweet() throws Exception {
        String[] text = {"post", "Happy Easter!!!!"};
        String tweetJsonStr = "{\n"
                + "  \"data\": {\n"
                + "  \"edit_history_tweet_ids\":[\"1645493943537213458\"],"
                + "  \"id\": \"1645493943537213458\","
                + "  \"text\": \"Happy Easter!!!!\"}}";
        CreateTweet createTweet = JsonParser.toObjectFromJson(tweetJsonStr, CreateTweet.class);
        when(service.postTweet(any())).thenReturn(createTweet);
        CreateTweet tweet = twitterController.postTweet(text);
        assertNotNull(tweet);
        assertNotNull(tweet.getData().getText());
    }

    @Test
    public void showTweet() throws Exception {
        String id = "1645538695204814848";
        String[] text = {"show", id, "created_at,id,id_str,text" };

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
        when(service.showTweet(any(),any())).thenReturn(tweet);
        Tweet expectedTweet = twitterController.showTweet(text);
        assertNotNull(expectedTweet);
        assertNotNull(expectedTweet.getText());
        assertNotNull(expectedTweet.getCreated_at());

    }

    @Test
    public void deleteTweet() throws Exception{
        String id ="1645492897532321817";
        String[] text = {"delete", id};
        String tweetJsonStr = "{\n"
                + "\"data\": {\n"
                + "\"deleted\": true \n"
                + "}}";
        DeleteTweet tweet = JsonParser.toObjectFromJson(tweetJsonStr, DeleteTweet.class);
        when(service.deleteTweet(any())).thenReturn(tweet);
        DeleteTweet expectedTweet = twitterController.deleteTweet(text);
        assertNotNull(expectedTweet);
        assertTrue(expectedTweet.getData().isDeleted());
    }
}
