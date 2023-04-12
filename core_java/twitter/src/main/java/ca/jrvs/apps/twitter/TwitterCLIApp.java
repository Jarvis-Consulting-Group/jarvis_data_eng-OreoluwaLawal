package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class TwitterCLIApp {
   public static final String USAGE = "USAGE: TwitterCLIApp post|show|delete [options]";

   private Controller controller;

   @Autowired
    public TwitterCLIApp(Controller controller) {
        this.controller = controller;
    }

    public static void main(String[] args) {
        String consumerKey = System.getenv("consumerKey");
        String consumerSecret = System.getenv("consumerSecret");
        String accessToken = System.getenv("accessToken");
        String tokenSecret = System.getenv("tokenSecret");

        System.out.println("Keys: " + consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
        TwitterDao dao = new TwitterDao(httpHelper);
        Service service = new TwitterService(dao);
        Controller controller = new TwitterController(service);
        TwitterCLIApp twitterCLIApp = new TwitterCLIApp(controller);

        twitterCLIApp.run(args);
    }

    public void run(String[] args) {
        if(args.length == 0){
            throw new IllegalArgumentException(USAGE);
        }
        switch (args[0].toLowerCase()){
            case "post":
                printPostTweet(controller.postTweet(args));
                break;
            case "show":
                printShowTweet(controller.showTweet(args));
                break;
            case "delete":
                printDeleteTweet(controller.deleteTweet(args));
                break;
            default:
                throw new IllegalArgumentException(USAGE);

        }
    }

    private void printPostTweet(CreateTweet tweet ){
        try {
            System.out.println(JsonParser.toJson(tweet, true, true));
        }
        catch (JsonProcessingException e){
            throw new RuntimeException("Unable to convert tweet object to string ", e);
        }
    }
    private void printShowTweet(Tweet tweet){
        try {
            System.out.println(JsonParser.toJson(tweet, true, true));
        }
        catch (JsonProcessingException e){
            throw new RuntimeException("Unable to convert tweet object to string ", e);
        }
    }
    private void printDeleteTweet(DeleteTweet tweet){
        try {
            System.out.println(JsonParser.toJson(tweet, true, true));
        }
        catch (JsonProcessingException e){
            throw new RuntimeException("Unable to convert tweet object to string ", e);
        }
    }
}
