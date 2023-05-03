package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CreateDao;
import ca.jrvs.apps.twitter.dao.DeleteDao;
import ca.jrvs.apps.twitter.dao.FindDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.List;

@org.springframework.stereotype.Service
public class TwitterService implements Service{

    private TwitterDao dao;

    @Autowired
    public TwitterService( TwitterDao dao){
        this.dao = dao;
    }
    @Override
    public CreateTweet postTweet(String tweet){
        if(tweet.length() > 140){
            throw new IllegalArgumentException("Tweet must not exceed 140 characters");
        }
        return (CreateTweet) dao.create(tweet) ;
    }

    @Override
    public Tweet showTweet(String id, String[] fields) {

        validateIdTweet(id);
        Tweet tweet = (Tweet) dao.findById(new BigInteger(id));
        Tweet setTweet = new Tweet();
        if(fields == null){
            return tweet;
        }
        for(String field: fields){
            if(field.equals("created_at"))
                setTweet.setCreated_at(tweet.getCreated_at());
            else if (field.equals("id"))
                setTweet.setId(tweet.getId());
            else if (field.equals("id_str"))
                setTweet.setId_str(tweet.getId_str());
            else if (field.equals("text"))
                setTweet.setText(tweet.getText());
            else if (field.equals("entities"))
                setTweet.setEntities(tweet.getEntities());
            else if (field.equals("coordinates"))
                setTweet.setCoordinates(tweet.getCoordinates());
            else if (field.equals("retweet_count"))
                setTweet.setRetweet_count(tweet.getRetweet_count());
            else if (field.equals("favorite_count"))
                setTweet.setFavorite_count(tweet.getFavorite_count());
            else if (field.equals("favorited"))
                setTweet.setFavorited(tweet.getFavorited());
            else if (field.equals("retweeted"))
                setTweet.setRetweeted(tweet.getRetweeted());
            else throw new IllegalArgumentException("Tweet field is invalid " + field );
        }
        return setTweet;
    }

    @Override
    public DeleteTweet deleteTweet(String id) {
        validateIdTweet(id);
        return (DeleteTweet) dao.deleteById(new BigInteger(id));
    }

    private void validateIdTweet(String id){
        try{
            new BigInteger(id);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("Tweet ID is not valid", e);
        }
    }
}
