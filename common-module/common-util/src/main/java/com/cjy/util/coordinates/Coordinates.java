package com.cjy.util.coordinates;

import lombok.Data;

@Data
public class Coordinates {

    private double longitude;

    private double latitude;

    public Coordinates(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

}
