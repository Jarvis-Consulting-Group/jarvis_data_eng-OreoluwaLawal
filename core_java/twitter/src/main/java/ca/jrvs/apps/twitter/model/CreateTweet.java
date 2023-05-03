package ca.jrvs.apps.twitter.model;

public class CreateTweet {
    private CreateTweetData data;

    public CreateTweet() {
    }

    public CreateTweet(CreateTweetData data) {
        this.data = data;
    }

    public CreateTweetData getData() {
        return data;
    }

    public void setData(CreateTweetData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CreateTweet{" +
                "data=" + data +
                '}';
    }
}
