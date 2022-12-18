package com.cheku.cheku.auxiliar_classes;

import java.util.ArrayList;


public class TripOverview {

    private Double averageSpeed;
    private Double totalDistance;
    private Double totalTime;
    private ArrayList<MapPositions> positions;

    public TripOverview() { 
        positions = new ArrayList<MapPositions>();
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = (double) Math.round(averageSpeed * 100d) / 100d;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = (double) Math.round(totalDistance * 100d) / 100d;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void addCoordinate(MapPositions pos) {
        positions.add(pos);
    }

    public ArrayList<MapPositions> getCoordinates() {
        return positions;
    }
}
