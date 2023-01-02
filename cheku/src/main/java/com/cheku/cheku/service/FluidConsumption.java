package com.cheku.cheku.service;

import com.cheku.cheku.repository.FluidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FluidConsumption {

    @Autowired
    private FluidRepository fluidRepository;

    public Double getFuelInLitersByTimestamp(Long trip_id, Date time) {
        return fluidRepository.getFromTimestamp(trip_id, time).getFuel() * 100;
    }
}
