package it.unisa.guardianhouse.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Apartment implements Parcelable{

    // id
    private String aptId;

    // dettagli
    private String name;
    private Float rating;
    private boolean featured;

    // indirizzo
    private String completeAddress;
    private String internId;
    private String streetNumber;    // numero via (es. 6)
    private String route;           // via (es. Via Roma)
    private String locality;        // città (es. Campobasso)
    private String adminAreaLevel1; // regioni (es. Molise)
    private String adminAreaLevel2; // province (es. CB)
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

    public Apartment(Parcel in) {
        aptId = in.readString();
        name = in.readString();
        rating = in.readFloat();
        featured = in.readByte() != 0;
        completeAddress = in.readString();
        internId = in.readString();
        streetNumber = in.readString();
        route = in.readString();
        locality = in.readString();
        adminAreaLevel1 = in.readString();
        adminAreaLevel2 = in.readString();
        postalCode = in.readString();
        country = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        distanceFromLocation = in.readDouble();
        thumbnailUrl = in.readString();
    }

    public String getCompleteAddress() {
        String completeAddress = route + " " + streetNumber + ", " + postalCode + " " +
                locality + " (" + adminAreaLevel2 + ")";
        return completeAddress;
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

    public boolean getFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
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

    public String getInternId() {
        return internId;
    }

    public void setInternId(String internId) {
        this.internId = internId;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(aptId);
        dest.writeString(name);
        dest.writeFloat(rating);
        dest.writeByte((byte) (featured ? 1 : 0));
        dest.writeString(completeAddress);
        dest.writeString(internId);
        dest.writeString(streetNumber);
        dest.writeString(route);
        dest.writeString(locality);
        dest.writeString(adminAreaLevel1);
        dest.writeString(adminAreaLevel2);
        dest.writeString(postalCode);
        dest.writeString(country);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeDouble(distanceFromLocation);
        dest.writeString(thumbnailUrl);
    }

    public static final Parcelable.Creator<Apartment> CREATOR = new Parcelable.Creator<Apartment>()
    {
        public Apartment createFromParcel(Parcel in)
        {
            return new Apartment(in);
        }
        public Apartment[] newArray(int size)
        {
            return new Apartment[size];
        }
    };
}
