package com.cheku.cheku.service;

import com.cheku.cheku.model.*;
import com.cheku.cheku.repository.CarRepository;
import com.cheku.cheku.repository.MotorRepository;
import com.cheku.cheku.repository.PneusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MotorRepository motorRepository;

    @Autowired
    private PneusRepository pneusRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car addCar(Car car) {
        // verificar se não existe um carro com a mesma matricula
        if (carRepository.findByMatricula(car.getMatricula()) != null) {
            System.out.println("Carro já existe");
            return null;
        }
        // verificar se o motor existe
        try {
            Motor motor = motorRepository.findByPotenciaAndCilindradaAndModelo(car.getMotor().getPotencia(), car.getMotor().getCilindrada(), car.getMotor().getModelo());
            if (motor != null) {
                car.setMotor(motor);
            }
            if (motor == null) {
                motorRepository.save(car.getMotor());
            }
        } catch (Exception e) {
            try {
                motorRepository.findById(car.getMotor().getId());
                car.setMotor(motorRepository.findById(car.getMotor().getId()).get());
            } catch (Exception ex) {
                System.out.println("Motor não existe");
                return null;
            }
        }
        // verificar se os pneus existem
        try {
            Pneus pneus = pneusRepository.findByBrandAndModel(car.getPneus().getBrand(), car.getPneus().getModel());
            if (pneus != null) {
                car.setPneus(pneus);
            }
            if (pneus == null) {
                pneusRepository.save(car.getPneus());
            }
        } catch (Exception e) {
            try {
                pneusRepository.findById(car.getPneus().getId());
                car.setPneus(pneusRepository.findById(car.getPneus().getId()).get());

            } catch (Exception ex) {
                System.out.println("Pneus não existem");
                return null;
            }
        }
        try {
            return carRepository.save(car);
        } catch (Exception e) {
            System.out.println("Erro ao guardar carro");
            return null;
        }
    }

    public Car getCar(Long id) {
        try{
            return carRepository.findById(id).get();
        } catch (Exception e) {
            System.out.println("Carro não existe");
            return null;
        }
    }

    public String deleteCar(Long id) {
        try {
            carRepository.deleteById(id);
            return "Carro eliminado com sucesso";
        } catch (Exception e) {
            System.out.println("Erro ao apagar carro");
            return "Erro ao apagar carro";
        }
    }

    public Car updateCar(Car car){
        try{
            carRepository.findById(car.getId()).get();
        } catch (Exception e) {
            System.out.println("Carro não existe");
            return null;
        }

        // verificar se o motor existe
        try {
            Motor motor = motorRepository.findByPotenciaAndCilindradaAndModelo(car.getMotor().getPotencia(), car.getMotor().getCilindrada(), car.getMotor().getModelo());
            if (motor != null) {
                car.setMotor(motor);
            }
            if (motor == null) {
                motorRepository.save(car.getMotor());
            }
        } catch (Exception e) {
            try {
                motorRepository.findById(car.getMotor().getId());
                car.setMotor(motorRepository.findById(car.getMotor().getId()).get());
            } catch (Exception ex) {
                System.out.println("Motor não existe");
                return null;
            }
        }
        // verificar se os pneus existem
        try {
            Pneus pneus = pneusRepository.findByBrandAndModel(car.getPneus().getBrand(), car.getPneus().getModel());
            if (pneus != null) {
                car.setPneus(pneus);
            }
            if (pneus == null) {
                pneusRepository.save(car.getPneus());
            }
        } catch (Exception e) {
            try {
                pneusRepository.findById(car.getPneus().getId());
                car.setPneus(pneusRepository.findById(car.getPneus().getId()).get());

            } catch (Exception ex) {
                System.out.println("Pneus não existem");
                return null;
            }
        }
        try {
            return carRepository.save(car);
        } catch (Exception e) {
            System.out.println("Erro ao guardar carro");
            return null;
        }
    }
}
