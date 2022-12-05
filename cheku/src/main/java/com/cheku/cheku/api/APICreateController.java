package com.cheku.cheku.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.exception.ResourceNotFoundException;

@RestController
public class APICreateController {
    
    @Autowired
	private CarService carService;

    @Autowired
    private MotorService motorService;

    @Autowired
	private VelocityService velocityService;

    @Autowired
    private PneusService pneusService;

    @Autowired
    private LocalizationService localizationService;

    @Autowired
    private CombustivelService combustivelService;

    //DONE
	@PostMapping("api/car")
    public Car createCar(@Valid @RequestBody Car car) throws ResourceNotFoundException {
        return carService.addCar(car);
	}

    //DONE
    @PostMapping("api/motor")
    public Motor createMotor(@Valid @RequestBody Motor motor) throws ResourceNotFoundException {
        return motorService.addMotor(motor);
    }

    //DONE
    @PostMapping("api/pneus")
    public Pneus createPneus(@Valid @RequestBody Pneus pneus) throws ResourceNotFoundException {
        return pneusService.addPneus(pneus);
    }

    //-----------------------------REMOVER MAIS TARDE--------------------------------
    @PostMapping("api/velocity")
    public HistoryVelocity createVelocityRecord (@Valid @RequestBody HistoryVelocity velocity) throws ResourceNotFoundException{
        return velocityService.addVelocity(velocity);
    }

    @PostMapping("api/localization")
    public Localization createLocalizationRecord (@Valid @RequestBody Localization localization) throws ResourceNotFoundException{
        return localizationService.addLocalization(localization);
    }

    @PostMapping("api/combustivel")
    public Combustivel createCombustivelRecord (@Valid @RequestBody Combustivel combustivel) throws ResourceNotFoundException{
        return combustivelService.addCombustivel(combustivel);
    }
    // --------------------end -----------------

}

