package it.unisa.guardianhouse.model;

public class Apartment {

    // dettagli
    private String description;

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

    public Apartment() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
