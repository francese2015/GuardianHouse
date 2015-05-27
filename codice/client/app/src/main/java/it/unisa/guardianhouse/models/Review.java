package it.unisa.guardianhouse.models;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setRewId(String rewId){this.rewId = rewId;}

    public String getRewId() {return rewId;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
