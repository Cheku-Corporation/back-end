package com.cheku.cheku.data_representation;


public class Coordinates {
    
    private Double latitude;
    private Double longitude;

    public Coordinates() {}

    public Coordinates(Double lat, Double longe) {
        latitude = lat;
        longitude = longe;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
