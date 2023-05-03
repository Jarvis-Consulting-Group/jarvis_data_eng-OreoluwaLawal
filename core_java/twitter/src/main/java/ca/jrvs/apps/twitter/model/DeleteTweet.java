package ca.jrvs.apps.twitter.model;

public class DeleteTweet {
    private DeleteTweetData data;

    public DeleteTweet() {
    }

    public DeleteTweet(DeleteTweetData data) {
        this.data = data;
    }

    public DeleteTweetData getData() {
        return data;
    }

    public void setData(DeleteTweetData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeleteTweet{" +
                "data=" + data +
                '}';
    }
}
