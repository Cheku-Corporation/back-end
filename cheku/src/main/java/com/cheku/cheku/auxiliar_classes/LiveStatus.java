package com.cheku.cheku.auxiliar_classes;

import com.cheku.cheku.model.PneusHistory;
import com.cheku.cheku.model.SpeedHistory;
import com.cheku.cheku.model.Trip;
import com.cheku.cheku.model.enums.LightState;

public class LiveStatus {
    
    private Double currentSpeed;
    private Double averageSpeed;
    private Double currentRPM;
    private int currentGear;
    private Double relativeDistance;
    private Double totalDistance;
    private LightState lightsState;
    private Double tiresPressure;
    private Double tiresTemperature;



    private LiveStatus(LiveStatusBuilder builder) {
        currentRPM = builder.currentRPM;
        currentSpeed = builder.currentSpeed;
        currentGear = builder.currentGear;
        totalDistance = builder.totalDistance;
        tiresPressure = builder.tiresPressure;
        tiresTemperature = builder.tiresTemperature;
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

    public LightState getLightdsState() {
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

    public static class LiveStatusBuilder {
        
        private Double currentSpeed;
        private Double averageSpeed;
        private Double currentRPM;
        private int currentGear;
        private Double relativeDistance;
        private Double totalDistance;
        private LightState lightsState;
        private Double tiresPressure;
        private Double tiresTemperature;
    

        public LiveStatusBuilder setSpeed(SpeedHistory speed) {
            currentSpeed = speed.getVelocity();
            return this;
        }
        
        public LiveStatusBuilder setRPM(SpeedHistory rpm) {
            currentRPM = rpm.getRPM();
            return this;
        }

        public LiveStatusBuilder setAverageSpeed(Double rpm) {
            currentRPM = rpm;
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
            System.out.println("tire temperature: " + tire.getTemperature());
            tiresTemperature = tire.getTemperature();
            return this;
        }

        public LiveStatusBuilder setTotalDistance(Trip trip) {
            totalDistance = trip.getTraveledDistance();
            return this;
        }

        // public LiveStatusBuilder setRPM(Double rpm) {
        //     currentRPM = rpm;
        //     return this;
        // }

        // public LiveStatusBuilder setRPM(Double rpm) {
        //     currentRPM = rpm;
        //     return this;
        // }

        public LiveStatus build() {
            return new LiveStatus(this);
        }
    }
}
