package com.cheku.cheku.service;

import com.cheku.cheku.exception.ResourceNotFoundException;
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
    public Motor addMotor(Motor motor)  throws ResourceNotFoundException {

        // verificar se não existe um motor com o mesmo parâmetro
        if (motorRepository.findByPowerAndDisplacementAndModel(motor.getPower(), motor.getDisplacement(), motor.getModel()) != null) {
            throw new ResourceNotFoundException("Motor already exists");
        }
        try {
            return motorRepository.save(motor);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving motor");
        }
    }

    public Motor getMotor(Long id) {
        return motorRepository.findById(id).get();
    }

    public Motor updateMotor(Long id, Motor motor) throws ResourceNotFoundException {
        Motor motor1 = motorRepository.findById(id).get();
        if (motor1 == null) {
            throw new ResourceNotFoundException("Motor not found");
        }
        motor1.setDisplacement(motor.getDisplacement());
        motor1.setModel(motor.getModel());
        motor1.setPower(motor.getPower());
        motorRepository.save(motor1);
        return motor1;
    }

    public void deleteMotor(Long id) throws ResourceNotFoundException {
        Motor motor = motorRepository.findById(id).get();
        if (motor == null) {
            throw new ResourceNotFoundException("Motor not found");
        }
        motorRepository.delete(motor);
    }
}
