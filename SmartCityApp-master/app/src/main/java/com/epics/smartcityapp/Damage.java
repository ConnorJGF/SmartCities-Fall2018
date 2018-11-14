package com.epics.smartcityapp;

/**
 * Created by kartikmittal on 2/26/18.
 */

public class Damage {
    String latitude;
    String longitude;
    String encodedImage;
    String type;
    String timeStamp;
    String description;
    String status = "submitted";

    public Damage(String latitude, String longitude, String encodedImage, String type, String timeStamp, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.encodedImage = encodedImage;
        this.type = type;
        this.timeStamp = timeStamp;
        this.description = description;
    }
}
