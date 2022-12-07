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
    public Motor addMotor(Motor motor) {

        // verificar se não existe um motor com o mesmo parâmetro
        if (motorRepository.findByPotenciaAndCilindradaAndModelo(motor.getPotencia(), motor.getCilindrada(), motor.getModelo()) != null) {
            return null;
        }
        try {
            return motorRepository.save(motor);
        } catch (Exception e) {
            System.out.println("Error saving motor");
            return null;
        }
    }

    public Boolean deleteMotor(Long id) {
        try {
            motorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error deleting motor");
            return false;
        }
    }

    public Motor getMotor(Long id) {
        return motorRepository.findById(id).get();
    }
}
