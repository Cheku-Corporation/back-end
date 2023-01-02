package com.cheku.cheku.data_representation;

import com.cheku.cheku.model.LightsHistory;
import com.cheku.cheku.model.TiresHistory;
import com.cheku.cheku.model.SpeedHistory;
import com.cheku.cheku.model.Trip;

public class LiveStatus {
    
    private boolean onTheRoad; 
    private long timeOnTravel;
    private Double currentSpeed;
    private Double averageSpeed;
    private int currentRPM;
    private int currentGear;
    private Double relativeDistance;
    private Double totalDistance;
    private String lightsState;
    private Double tiresPressure;
    private Double tiresTemperature;
    private int currentWaterPercentage;
    private int currentOilPercentage;
    private int currentFuelPercentage;
    private Coordinates localization;


    private LiveStatus(LiveStatusBuilder builder) {
        currentRPM = builder.currentRPM;
        currentSpeed = (double) Math.round(builder.currentSpeed * 100d) / 100d; 
        currentGear = builder.currentGear;
        relativeDistance = (double) Math.round(builder.relativeDistance * 100d) / 100d;
        totalDistance = (double) Math.round(builder.totalDistance * 100d) / 100d;
        tiresPressure = (double) Math.round(builder.tiresPressure * 100d) / 100d;
        tiresTemperature = (double) Math.round(builder.tiresTemperature * 100d) / 100d;
        lightsState = builder.lightsState;
        timeOnTravel = builder.timeOnTravel;
        averageSpeed = (Double) (double) Math.round((relativeDistance / timeOnTravel) * 100d) / 100d;
        currentFuelPercentage = builder.currentFuelPercentage; 
        currentOilPercentage = builder.currentOilPercentage; 
        currentWaterPercentage = builder.currentWaterPercentage;
        localization = builder.localization;
        onTheRoad = builder.onTheRoad;
    }

    public boolean getOnTheRoad() {
        return onTheRoad;
    }

    public int getCurrentRPM() {
        return currentRPM;
    }

    public Double getCurrentSpeed() {
        return currentSpeed;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public int getCurrentGear() {
        return currentGear;
    }

    public Double getRelativeDistance() {
        return relativeDistance;
    }

    public Double getTiresPressure() {
        return tiresPressure;
    }

    public Double getTiresTemperature() {
        return tiresTemperature;
    }

    public Double getTotalDistance() {
        return totalDistance;
    }

    public long getTimeOnTravel() {
        return timeOnTravel;
    }

    public String getLightsState() {
        return lightsState;
    }

    public int getCurrentFuelPercentage() {
        return currentFuelPercentage;
    }

    public int getCurrentOilPercentage() {
        return currentOilPercentage;
    }

    public int getCurrentWaterPercentage() {
        return currentWaterPercentage;
    }

    public Coordinates getLocalization() {
        return localization;
    }

    public static class LiveStatusBuilder {
        
        private boolean onTheRoad;
        private Double currentSpeed;
        private int currentRPM;
        private int currentGear;
        private Double relativeDistance;
        private Double totalDistance;
        private String lightsState;
        private Double tiresPressure;
        private Double tiresTemperature;
        private long timeOnTravel;
        private int currentWaterPercentage;
        private int currentOilPercentage;
        private int currentFuelPercentage;
        private Coordinates localization;
    

        public LiveStatusBuilder setSpeed(SpeedHistory speed) {
            currentSpeed = speed.getVelocity();
            return this;
        }
        
        public LiveStatusBuilder setRPM(SpeedHistory rpm) {
            currentRPM = Integer.parseInt(rpm.getRPM().toString());
            return this;
        }

        public LiveStatusBuilder setGear(SpeedHistory gear) {
            currentGear = gear.getGear();
            return this;
        }

        public LiveStatusBuilder setTiresPressure(TiresHistory tire) {
            tiresPressure = tire.getPressure();
            return this;
        }

        public LiveStatusBuilder setTiresTemperature(TiresHistory tire) {
            tiresTemperature = tire.getTemperature();
            return this;
        }

        public LiveStatusBuilder setLightsState(LightsHistory light) {
            lightsState = light.getState().name();
            return this;
        }

        public LiveStatusBuilder setRelativeDistance(Trip trip) {
            relativeDistance = trip.getTraveledDistance();
            return this;
        }

        public LiveStatusBuilder setTotalDistance(Double distance) {
            totalDistance = distance + relativeDistance;
            return this;
        }

        public LiveStatusBuilder setTime(long time) {
            timeOnTravel = time;
            return this;
        }

        public LiveStatusBuilder setOil(Double oil) {
            currentOilPercentage = oil.intValue();
            return this;
        }

        public LiveStatusBuilder setWater(Double water) {
            currentWaterPercentage = water.intValue();
            return this;
        }

        public LiveStatusBuilder setFuel(Double fuel) {
            currentFuelPercentage = fuel.intValue();
            return this;
        }

        public LiveStatusBuilder setLocalization(Coordinates coords) {
            localization = coords;
            return this;
        }

        public LiveStatusBuilder isOnTheRoad(Boolean onRide) {
            onTheRoad = onRide;
            return this;
        }

        public LiveStatus build() {
            return new LiveStatus(this);
        }
    }
}
