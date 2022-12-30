package com.cheku.cheku.auxiliar_classes;

import java.util.ArrayList;


public class TripOverview {

    private Double averageSpeed;
    private Double totalDistance;
    private Double totalTime;
    private Double fuelSpent;
    private Double fuelConsumptionPer100km;
    private boolean previousHasData;
    private Double averageSpeedComparedToPrevious;
    private Double totalDistanceComparedToPrevious;
    private Double totalTimeComparedToPrevious;
    private Double fuelSpentComparedToPrevious;
    private Double fuelConsumptionPer100kmComparedToPrevious;

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

    public void setFuelSpent(Double fuelSpent) {
        this.fuelSpent = fuelSpent;
    }

    public void setAverageSpeedComparedToPrevious(Double averageSpeedComparedToPrevious) {
        this.averageSpeedComparedToPrevious = (double) Math.round(averageSpeedComparedToPrevious * 100d) / 100d;
    }

    public void setFuelSpentComparedToPrevious(Double fuelSpentComparedToPrevious) {
        this.fuelSpentComparedToPrevious = (double) Math.round(fuelSpentComparedToPrevious * 100d) / 100d;
    }

    public void setTotalDistanceComparedToPrevious(Double totalDistanceComparedToPrevious) {
        this.totalDistanceComparedToPrevious = (double) Math.round(totalDistanceComparedToPrevious * 100d) / 100d;
    }

    public void setTotalTimeComparedToPrevious(Double totalTimeComparedToPrevious) {
        this.totalTimeComparedToPrevious = (double) Math.round(totalTimeComparedToPrevious * 100d) / 100d;
    }

    public void setFuelConsumptionPer100km(Double fuelConsumptionPer100km) {
        this.fuelConsumptionPer100km = (double) Math.round(fuelConsumptionPer100km * 100d) / 100d;
    }

    public void setFuelConsumptionPer100kmComparedToPrevious(Double fuelConsumptionPer100kmComparedToPrevious) {
        this.fuelConsumptionPer100kmComparedToPrevious = (double) Math.round(fuelConsumptionPer100kmComparedToPrevious * 100d) / 100d;
    }

    public void setPreviousHasData(boolean previousHasData) {
        this.previousHasData = previousHasData;
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

    public Double getFuelSpent() {
        return fuelSpent;
    }

    public Double getAverageSpeedComparedToPrevious() {
        return averageSpeedComparedToPrevious;
    }

    public Double getFuelSpentComparedToPrevious() {
        return fuelSpentComparedToPrevious;
    }

    public Double getTotalDistanceComparedToPrevious() {
        return totalDistanceComparedToPrevious;
    }

    public Double getTotalTimeComparedToPrevious() {
        return totalTimeComparedToPrevious;
    }

    public Double getFuelConsumptionPer100km() {
        return fuelConsumptionPer100km;
    }

    public Double getFuelConsumptionPer100kmComparedToPrevious() {
        return fuelConsumptionPer100kmComparedToPrevious;
    }

    public boolean getPreviousHasData() {
        return previousHasData;
    }

    public ArrayList<MapPositions> getCoordinates() {
        return positions;
    }
}
