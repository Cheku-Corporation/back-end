package com.cheku.cheku.auxiliar_classes;

import com.cheku.cheku.model.LightsHistory;
import com.cheku.cheku.model.PneusHistory;
import com.cheku.cheku.model.SpeedHistory;
import com.cheku.cheku.model.Trip;

public class LiveStatus {
    
    private long timeOnTravel;
    private Double currentSpeed;
    private Double averageSpeed;
    private Double currentRPM;
    private int currentGear;
    private Double relativeDistance;
    private Double totalDistance;
    private String lightsState;
    private Double tiresPressure;
    private Double tiresTemperature;
    private Double currentWaterPercentage;
    private Double currentOilPercentage;
    private Double currentFuelPercentage;


    private LiveStatus(LiveStatusBuilder builder) {
        currentRPM = builder.currentRPM;
        currentSpeed = builder.currentSpeed;
        currentGear = builder.currentGear;
        relativeDistance = (double) Math.round(builder.relativeDistance * 100d) / 100d;
        totalDistance = (double) Math.round(builder.totalDistance * 100d) / 100d;
        tiresPressure = builder.tiresPressure;
        tiresTemperature = builder.tiresTemperature;
        lightsState = builder.lightsState;
        timeOnTravel = builder.timeOnTravel;
        averageSpeed = (Double) (double) Math.round((relativeDistance / timeOnTravel) * 100d) / 100d;
        currentFuelPercentage = builder.currentFuelPercentage;
        currentOilPercentage = builder.currentOilPercentage;
        currentWaterPercentage = builder.currentWaterPercentage;
    }

    public Double getCurrentRPM() {
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

    public String getLightdsState() {
        return lightsState;
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

    public Double getCurrentFuelPercentage() {
        return currentFuelPercentage;
    }

    public Double getCurrentOilPercentage() {
        return currentOilPercentage;
    }

    public Double getCurrentWaterPercentage() {
        return currentWaterPercentage;
    }

    public static class LiveStatusBuilder {
        
        private Double currentSpeed;
        private Double currentRPM;
        private int currentGear;
        private Double relativeDistance;
        private Double totalDistance;
        private String lightsState;
        private Double tiresPressure;
        private Double tiresTemperature;
        private long timeOnTravel;
        private Double currentWaterPercentage;
        private Double currentOilPercentage;
        private Double currentFuelPercentage;
    

        public LiveStatusBuilder setSpeed(SpeedHistory speed) {
            currentSpeed = speed.getVelocity();
            return this;
        }
        
        public LiveStatusBuilder setRPM(SpeedHistory rpm) {
            currentRPM = rpm.getRPM();
            return this;
        }

        public LiveStatusBuilder setGear(SpeedHistory gear) {
            currentGear = gear.getGear();
            return this;
        }

        public LiveStatusBuilder setTiresPressure(PneusHistory tire) {
            tiresPressure = tire.getPressure();
            return this;
        }

        public LiveStatusBuilder setTiresTemperature(PneusHistory tire) {
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
            currentOilPercentage = oil;
            return this;
        }

        public LiveStatusBuilder setWater(Double water) {
            currentWaterPercentage = water;
            return this;
        }

        public LiveStatusBuilder setFuel(Double fuel) {
            currentFuelPercentage = fuel;
            return this;
        }

        public LiveStatus build() {
            return new LiveStatus(this);
        }
    }
}
