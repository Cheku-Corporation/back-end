package com.cheku.cheku.data_representation;

public class FuelConsumption {
    
    private Double consumption;
    private Long trip_id;

    public FuelConsumption() {}

    public FuelConsumption(Double fuel, Long trip_id) {
        consumption = (double) Math.round(fuel * 100d) / 100d;
        this.trip_id = trip_id;
    }

    public Double getConsumption() {
        return consumption;
    }

    public Long getTrip_id() {
        return trip_id;
    }
}
