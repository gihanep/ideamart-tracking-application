package com.ideamart.sample.lbs;

/**
 * Created by tharinda on 11/18/16.
 */
public class ResponseBean {

    private String Latitude;
    private String Longitude;
    private String Status;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }


}
