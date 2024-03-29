package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserMention {
    private String name;
    private List<Integer> indices;
    private String screen_name;
    private BigInteger id;
    private String id_str;

    public UserMention() {
    }

    public UserMention(String name, List<Integer> indices, String screen_name, BigInteger id, String id_str) {
        this.name = name;
        this.indices = indices;
        this.screen_name = screen_name;
        this.id = id;
        this.id_str = id_str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public void setIndices(List<Integer> indices) {
        this.indices = indices;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getId_str() {
        return id_str;
    }

    public void setId_str(String id_str) {
        this.id_str = id_str;
    }
}
