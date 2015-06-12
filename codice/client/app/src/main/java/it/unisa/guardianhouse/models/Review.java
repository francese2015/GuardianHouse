package it.unisa.guardianhouse.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thecomputerwhisper on 15/04/15.
 */
public class Review {

    private double applicanceStatus;
    private double thermicCapacity;
    private double landlordHonesty;
    private double securityLevel;
    private double busConnection;
    private double neighbours;
    private double distanceCC;
    private double fornitureQuality;
    private double feedbackRate;
    private String description;
    private String thumbnailUrl;
    private String rewId;
    private String username;


    public Review() {
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public double getApplicanceStatus() {
        return applicanceStatus;
    }

    public void setApplicanceStatus(double applicanceStatus) {
        this.applicanceStatus = applicanceStatus;
    }

    public double getThermicCapacity() {
        return thermicCapacity;
    }

    public void setThermicCapacity(double thermicCapacity) {
        this.thermicCapacity = thermicCapacity;
    }

    public double getLandlordHonesty() {
        return landlordHonesty;
    }

    public void setLandlordHonesty(double landlordHonesty) {
        this.landlordHonesty = landlordHonesty;
    }

    public double getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(double securityLevel) {
        this.securityLevel = securityLevel;
    }

    public double getBusConnection() {
        return busConnection;
    }

    public void setBusConnection(double busConnection) {
        this.busConnection = busConnection;
    }

    public double getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(double neighbours) {
        this.neighbours = neighbours;
    }

    public double getDistanceCC() {
        return distanceCC;
    }

    public void setDistanceCC(double distanceCC) {
        this.distanceCC = distanceCC;
    }

    public double getFornitureQuality() {
        return fornitureQuality;
    }

    public void setFornitureQuality(double fornitureQuality) {
        this.fornitureQuality = fornitureQuality;
    }

    public double getFeedbackRate() {
        return feedbackRate;
    }

    public void setFeedbackRate(double feedbackRate) {
        this.feedbackRate = feedbackRate;
    }

    public String getRewId() {
        return rewId;
    }

    public void setRewId(String rewId) {
        this.rewId = rewId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //    public Review(Parcel in) {
//        description = in.readString();
//        thumbnailUrl = in.readString();
//        username = in.readString();
//        rewId = in.readString();
//        applicanceStatus = in.readDouble();
//        thermicCapacity = in.readDouble();
//        landlordHonesty = in.readDouble();
//        securityLevel = in.readDouble();
//        busConnection = in.readDouble();
//        neighbours = in.readDouble();
//        distanceCC = in.readDouble();
//        fornitureQuality = in.readDouble();
//        feedbackRate = in.readDouble();
//        thumbnailUrl = in.readString();
//        username = in.readString();
//    }
//
//
//
//    public void writeToParcel(Parcel dest, int flags) {
//
//        dest.writeString(description);
//        dest.writeString(thumbnailUrl);
//        dest.writeString(username);
//        dest.writeString(rewId);
//        dest.writeDouble(applicanceStatus);
//        dest.writeDouble(thermicCapacity);
//        dest.writeDouble(landlordHonesty);
//        dest.writeDouble(securityLevel);
//        dest.writeDouble(busConnection);
//        dest.writeDouble(neighbours);
//        dest.writeDouble(distanceCC);
//        dest.writeDouble(fornitureQuality);
//        dest.writeDouble(feedbackRate);
//        dest.writeString(thumbnailUrl);
//    }
//
//    public static final Parcelable.Creator<Review> CREATOR = new Parcelable.Creator<Review>()
//    {
//        public Review createFromParcel(Parcel in)
//        {
//            return new Review(in);
//        }
//        public Review[] newArray(int size)
//        {
//            return new Review[size];
//        }
//    };

}
