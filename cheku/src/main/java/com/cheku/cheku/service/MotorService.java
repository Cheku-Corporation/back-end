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

    /** Returns a list of all motors in the database */
    public List<Motor> getAllMotors() {
        return motorRepository.findAll();
    }

    /** Creates a new motor in the database
     * @param motor The motor to be created
     * @return The newly created motor
     */
    public Motor addMotor(Motor motor)  throws ResourceNotFoundException {

        // Check if a motor with the same power, displacement, and model already exists
        if (motorRepository.findByPowerAndDisplacementAndModel(motor.getPower(), motor.getDisplacement(), motor.getModel()) != null) {
            throw new ResourceNotFoundException("Motor already exists");
        }
        try {
            return motorRepository.save(motor);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving motor");
        }
    }

    /** Returns a motor by id
     * @param id The id of the motor to be returned
     * @return The motor with the given id
     */
    public Motor getMotor(Long id) {
        return motorRepository.findById(id).get();
    }

    /** Updates a motor in the database
     * @param id The id of the motor to be updated
     * @param motor The motor to be updated
     * @return The updated motor
     */
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

    /** Deletes a motor from the database
     * @param id The id of the motor to be deleted
     */
    public void deleteMotor(Long id) throws ResourceNotFoundException {
        Motor motor = motorRepository.findById(id).get();
        if (motor == null) {
            throw new ResourceNotFoundException("Motor not found");
        }
        motorRepository.delete(motor);
    }
}
