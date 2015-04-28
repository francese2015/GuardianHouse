package it.unisa.guardianhouse.model;

/**
 * Created by Nae on 13.04.2015.
 */
public class Apartment {

    private String name;
    private Double rating;

    public Apartment(String name, Double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
