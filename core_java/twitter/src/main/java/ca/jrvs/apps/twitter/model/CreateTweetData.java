package ca.jrvs.apps.twitter.model;

import java.util.List;

public class CreateTweetData {
    private List<String> edit_history_tweet_ids;
    private String id;
    private String text;

    public CreateTweetData() {
    }

    public CreateTweetData(List<String> edit_history_tweet_ids, String id, String text) {
        this.edit_history_tweet_ids = edit_history_tweet_ids;
        this.id = id;
        this.text = text;
    }

    public List<String> getEdit_history_tweet_ids() {
        return edit_history_tweet_ids;
    }

    public void setEdit_history_tweet_ids(List<String> edit_history_tweet_ids) {
        this.edit_history_tweet_ids = edit_history_tweet_ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "CreateTweetData{" +
                "edit_history_tweet_ids=" + edit_history_tweet_ids +
                ", id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
