package ca.jrvs.apps.twitter.model;

public class DeleteTweetData {
    private boolean deleted;

    public DeleteTweetData() {
    }

    public DeleteTweetData(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "DeleteTweetData{" +
                "deleted=" + deleted +
                '}';
    }
}
