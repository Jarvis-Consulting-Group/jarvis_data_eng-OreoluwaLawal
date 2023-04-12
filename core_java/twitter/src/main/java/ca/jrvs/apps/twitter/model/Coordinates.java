package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinates {
    private List<BigDecimal> coordinates = null;
    private String type;

    public Coordinates() {
    }

    public Coordinates(List<BigDecimal> coordinates, String type) {
        this.coordinates = coordinates;
        this.type = type;
    }

    public List<BigDecimal> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<BigDecimal> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "coordinates=" + coordinates +
                ", type='" + type + '\'' +
                '}';
    }
}
