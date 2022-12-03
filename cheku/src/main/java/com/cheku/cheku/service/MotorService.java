package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MotorService {

    @Autowired
    private MotorRepository motorRepository;

    //Done
    public List<Motor> getAllMotors() {
        return motorRepository.findAll();
    }

    //Done
    public Motor addMotor(Motor motor){

        return motorRepository.save(motor);
    }
}