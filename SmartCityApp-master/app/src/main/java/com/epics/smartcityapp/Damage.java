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
<<<<<<< HEAD
    String status = "submitted";
=======
    //String status;
>>>>>>> 118fc9beb173a00af83661cfce9e04291dc665a2

    public Damage(String latitude, String longitude, String encodedImage, String type, String timeStamp, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.encodedImage = encodedImage;
        this.type = type;
        this.timeStamp = timeStamp;
        this.description = description;
<<<<<<< HEAD
=======
        //this.status = status;
>>>>>>> 118fc9beb173a00af83661cfce9e04291dc665a2
    }


}
