package it.unisa.guardianhouse.models;

import android.media.Image;

/**
 * Created by thecomputerwhisper on 11/06/15.
 */
public class Feedback {

    private String feedback_id;

    private String username;
    private Float rating;
    private String feed_text;
    private Image profileImage;

    public Feedback() {

    }

    public String getFeed_text() {
        return feed_text;
    }

    public void setFeed_text(String feed_text) {
        this.feed_text = feed_text;
    }

    public String getFeedback_id() {
        return feedback_id;
    }

    public void setFeedback_id(String feedback_id) {
        this.feedback_id = feedback_id;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }
}
