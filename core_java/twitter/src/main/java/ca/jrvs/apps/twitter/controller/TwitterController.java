package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URISyntaxException;
import java.util.List;

@org.springframework.stereotype.Controller
public class TwitterController implements Controller{
    private static final String COORD_SEP = ":";
    private static final String COMMA = ",";
    private Service service;

    @Autowired
    public TwitterController(Service service) {
        this.service = service;
    }

    @Override
    public CreateTweet postTweet(String[] args)  {
        if(args.length != 2){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_test\"");
        }
        String text = args[1];

        return (CreateTweet) service.postTweet(text);
    }

    @Override
    public Tweet showTweet(String[] args) {
        if(args.length != 2 && args.length != 3){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp show \"tweet_id\" \"fields(optional)\"");
        }
        Tweet tweet;
        if(args.length == 2){
            tweet = service.showTweet(args[1], null);
        }
        else{
            String[] fields = args[2].toLowerCase().split(COMMA);
            tweet = service.showTweet(args[1], fields);
        }
        return tweet;
    }

    @Override
    public DeleteTweet deleteTweet(String[] args) {
        if(args.length != 2){
            throw new IllegalArgumentException("USAGE: TwitterCLIApp delete \"tweet_id\"");
        }
        return service.deleteTweet(args[1]);
    }
}
