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

    public Damage(String latitude, String longitude, String encodedImage, String type, String timeStamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.encodedImage = encodedImage;
        this.type = type;
        this.timeStamp = timeStamp;
    }
}
