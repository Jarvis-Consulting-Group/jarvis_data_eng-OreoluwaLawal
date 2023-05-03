package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.net.URI;

public class TwitterHttpHelperTest {

    @Test
    public void httpPost() throws Exception{
       String consumerKey = System.getenv("consumerKey");
       String consumerSecret = System.getenv("consumerSecret");
       String accessToken = System.getenv("accessToken");
       String tokenSecret = System.getenv("tokenSecret");

       System.out.println("Keys: " + consumerKey + " | " + consumerSecret + " | " + accessToken + " | " + tokenSecret);
        HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);

        String status = "What a wonderful day!";
        JSONObject json_string = new JSONObject();
        json_string.put("text", status);

        HttpResponse httpResponse = httpHelper.httpPost(new URI("https://api.twitter.com/2/tweets"), new StringEntity(json_string.toJSONString()));
        System.out.println(EntityUtils.toString(httpResponse.getEntity()));
    }
}
