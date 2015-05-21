package it.unisa.guardianhouse.models;

public class Apartment {

    // id
    private String aptId;

    // dettagli
    private String name;
    private Float rating;
    private Boolean featured;

    // indirizzo
    private String streetNumber;    // numero via (es. 6)
    private String route;           // via (es. Via Roma)
    private String locality;        // città (es. Campobasso)
    private String adminAreaLevel1; // regioni (es. Molise)
    private String adminAreaLevel2; // province (es. Campobasso)
    private String postalCode;      // codice postale (es. 86100)
    private String country;         // paese (es. Italia)

    //coordinate
    private Double latitude;
    private Double longitude;
    private Double distanceFromLocation;

    // photo thumbnail
    private String thumbnailUrl;

    public Apartment() {

    }

    public String getId() {
        return aptId;
    }

    public void setId(String aptId) {
        this.aptId = aptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Double getDistanceFromLocation() {
        return distanceFromLocation;
    }

    public void setDistanceFromLocation(Double distanceFromLocation) {
        this.distanceFromLocation = distanceFromLocation;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAdminAreaLevel1() {
        return adminAreaLevel1;
    }

    public void setAdminAreaLevel1(String adminAreaLevel1) {
        this.adminAreaLevel1 = adminAreaLevel1;
    }

    public String getAdminAreaLevel2() {
        return adminAreaLevel2;
    }

    public void setAdminAreaLevel2(String adminAreaLevel2) {
        this.adminAreaLevel2 = adminAreaLevel2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
