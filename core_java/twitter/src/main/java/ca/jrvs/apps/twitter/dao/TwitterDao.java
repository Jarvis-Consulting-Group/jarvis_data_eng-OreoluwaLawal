package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.example.JsonParser;
import ca.jrvs.apps.twitter.model.CreateTweet;
import ca.jrvs.apps.twitter.model.DeleteTweet;
import ca.jrvs.apps.twitter.model.Tweet;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;

@Repository
public class TwitterDao implements CreateDao<CreateTweet, String>, DeleteDao<DeleteTweet, BigInteger>, FindDao<Tweet, BigInteger> {

    private static final String API_BASE_URI = "https://api.twitter.com";
    private static final String POST_PATH = "/2/tweets";
    private static final String SHOW_PATH = "/1.1/statuses/show.json";
    private static final String DELETE_PATH = "/2/tweets/";
    private static final String QUERY_SYM = "?";
    private static final String AMPERSAND = "&";
    private static final String EQUAL = "=";
    private static final int HTTP_OK = 201;
    private HttpHelper httpHelper;
    @Autowired
    public TwitterDao(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }


    @Override
    public DeleteTweet deleteById(BigInteger bigInteger) {
        HttpResponse httpResponse;
        try{
            httpResponse = httpHelper.httpDelete(new URI(API_BASE_URI + DELETE_PATH + bigInteger));
        }catch (URISyntaxException ex){
            throw new IllegalArgumentException("Invalid URI", ex);
        }
        return parseDeleteTweetResponse(httpResponse, 200);
    }

    @Override
    public Tweet findById(BigInteger bigInteger) {

        HttpResponse httpResponse;
        try{
            httpResponse = httpHelper.httpGet(new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + bigInteger));
        }catch (URISyntaxException ex){
            throw new IllegalArgumentException("Invalid URI", ex);
        }
        return parseShowTweetResponse(httpResponse, 200);
    }

    @Override
    public CreateTweet create(String entity){
        String status = entity;
        JSONObject json_string = new JSONObject();
        json_string.put("text", status);
        HttpResponse httpResponse;

    try {
        httpResponse = httpHelper.httpPost(getUri(API_BASE_URI + POST_PATH), new StringEntity(json_string.toJSONString()));
    }catch (OAuthException | IOException ex){
        throw new IllegalArgumentException("Invalid URI", ex);
    }
        return parseCreateTweetResponse(httpResponse, HTTP_OK);
    }

    private URI getUri(String str){
        URI uri;
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Invalid URI", e);
        }

        return uri;
    }

    public CreateTweet parseCreateTweetResponse(HttpResponse response, Integer
                                                 expectedStatusCode){
        CreateTweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if(status != expectedStatusCode){
            try{
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e){
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: " + status);
        }
        if(response.getEntity() == null){
            throw new RuntimeException("Empty response body");
        }
        String jsonStr = null;
        try{
            jsonStr = EntityUtils.toString(response.getEntity());
            System.out.println(jsonStr);
        }catch (IOException ex){
            throw new RuntimeException("Failed to convert entity to string ", ex);
        }

        try{
            tweet = JsonParser.toObjectFromJson(jsonStr, CreateTweet.class);
        }catch (IOException e){
            throw new RuntimeException("Unable to convert JSON str to Object ", e);
        }
        return tweet;

    }

    public Tweet parseShowTweetResponse(HttpResponse response, Integer
            expectedStatusCode){
        Tweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if(status != expectedStatusCode){
            try{
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e){
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: " + status);
        }
        if(response.getEntity() == null){
            throw new RuntimeException("Empty response body");
        }
        String jsonStr = null;
        try{
            jsonStr = EntityUtils.toString(response.getEntity());
            System.out.println(jsonStr);
        }catch (IOException ex){
            throw new RuntimeException("Failed to convert entity to string ", ex);
        }

        try{
            tweet = JsonParser.toObjectFromJson(jsonStr, Tweet.class);
        }catch (IOException e){
            throw new RuntimeException("Unable to convert JSON str to Object ", e);
        }
        return tweet;

    }
    public DeleteTweet parseDeleteTweetResponse(HttpResponse response, Integer
            expectedStatusCode){
        DeleteTweet tweet = null;

        int status = response.getStatusLine().getStatusCode();
        if(status != expectedStatusCode){
            try{
                System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (IOException e){
                System.out.println("Response has no entity");
            }
            throw new RuntimeException("Unexpected HTTP status: " + status);
        }
        if(response.getEntity() == null){
            throw new RuntimeException("Empty response body");
        }
        String jsonStr = null;
        try{
            jsonStr = EntityUtils.toString(response.getEntity());
            System.out.println(jsonStr);
        }catch (IOException ex){
            throw new RuntimeException("Failed to convert entity to string ", ex);
        }

        try{
            tweet = JsonParser.toObjectFromJson(jsonStr, DeleteTweet.class);
        }catch (IOException e){
            throw new RuntimeException("Unable to convert JSON str to Object ", e);
        }
        return tweet;

    }
}
