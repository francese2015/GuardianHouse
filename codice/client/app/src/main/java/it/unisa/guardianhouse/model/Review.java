package it.unisa.guardianhouse.model;

/**
 * Created by thecomputerwhisper on 15/04/15.
 */
public class Review {

    private String applicanceStatus;
    private String thermicCapacity;
    private String landlordHonesty;
    private String securityLevel;
    private String busConnection;
    private String neighbours;
    private String distanceCC;
    private String fornitureQuality;
    private double feedbackRate;
    private String description;


    public Review() {
    }

    public String getApplicanceStatus() {
        return applicanceStatus;
    }

    public void setApplicanceStatus(String applicanceStatus) {
        this.applicanceStatus = applicanceStatus;
    }

    public String getThermicCapacity() {
        return thermicCapacity;
    }

    public void setThermicCapacity(String thermicCapacity) {
        this.thermicCapacity = thermicCapacity;
    }

    public String getLandlordHonesty() {
        return landlordHonesty;
    }

    public void setLandlordHonesty(String landlordHonesty) {
        this.landlordHonesty = landlordHonesty;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getBusConnection() {
        return busConnection;
    }

    public void setBusConnection(String busConnection) {
        this.busConnection = busConnection;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(String neighbours) {
        this.neighbours = neighbours;
    }

    public String getDistanceCC() {
        return distanceCC;
    }

    public void setDistanceCC(String distanceCC) {
        this.distanceCC = distanceCC;
    }

    public String getFornitureQuality() {
        return fornitureQuality;
    }

    public void setFornitureQuality(String fornitureQuality) {
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
}
