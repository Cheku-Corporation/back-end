package com.cheku.cheku.service;

import com.cheku.cheku.data_representation.SimpleFluid;
import com.cheku.cheku.model.Fluid;
import com.cheku.cheku.repository.FluidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FluidService {

    @Autowired
    private FluidRepository fluidRepository;

    @Autowired
    private TripService tripService;

    public Fluid addFluid(Fluid fluid) {
        try{
            return fluidRepository.save(fluid);
        } catch (Exception e) {
            return null;
        }
    }

    public List<SimpleFluid> getAllFluids() {
        return fluidRepository.getAll();
    }

    public Double getLastOilPercentage(Long car_id) {
        Long trip_id = tripService.getCarCurrentTrip(car_id).getId();
        return fluidRepository.getLast(trip_id).getOil() * 100;
    }

    public Double getLastWaterPercentage(Long car_id) {
        Long trip_id = tripService.getCarCurrentTrip(car_id).getId();
        return fluidRepository.getLast(trip_id).getWater() * 100;
    }

    public Double getLastFuelInLiters(Long car_id) {
        Long trip_id = tripService.getCarCurrentTrip(car_id).getId();
        return fluidRepository.getLast(trip_id).getFuel() * 100;
    }

    public Double getFuelInLitersByTimestamp(Long trip_id, Date time) {
        return fluidRepository.getFromTimestamp(trip_id, time).getFuel() * 100;
    }

    public Fluid getFluid(Long id) {
        return fluidRepository.findById(id).get();
    }

}
