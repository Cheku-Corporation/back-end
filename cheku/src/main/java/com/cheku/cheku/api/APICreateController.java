package com.cheku.cheku.api;

import javax.validation.Valid;

import com.cheku.cheku.auxiliar_classes.NamesGroup;
import com.cheku.cheku.auxiliar_classes.ProcessedUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Set;


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
    private MotorHistoryService motorHistoryService;

    @Autowired
    private PneusHistoryService pneusHistoryService;

    @Autowired
    private LuzesService luzesService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private FluidService fluidService;

    //(REMOVER EM PRINCIPIO)
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

    //DONE
    @PostMapping("api/user")
    public User createUser(@Valid @RequestBody User user ) throws ResourceNotFoundException {
        return userService.addUser(user);
    }

    //DONE
    @PostMapping("api/group")
    public Group createGroup(@Valid @RequestBody Group group) {
        return groupService.addGroup(group);
    }

    @PostMapping("api/group/{group_id}/car")
    public List<Car> addCarToGroup(@PathVariable Long group_id, @Valid @RequestBody Car car) throws ResourceNotFoundException {
        Car new_car = carService.addCar(car);
        if (new_car == null){
            return null;
        }
        return groupService.addCarToGroup(group_id, new_car.getId());
    }






    //-----------------------------REMOVER MAIS TARDE--------------------------------
    @PostMapping("api/velocity")
    public Velocity_history createVelocityRecord (@Valid @RequestBody Velocity_history velocity) throws ResourceNotFoundException{
        return velocityService.addVelocity(velocity);
    }

    @PostMapping("api/localization")
    public Localization createLocalizationRecord (@Valid @RequestBody Localization localization) throws ResourceNotFoundException{
        return localizationService.addLocalization(localization);
    }

    @PostMapping("api/fluid")
    public Fluid createFluidRecord (@Valid @RequestBody Fluid fluid) throws ResourceNotFoundException{
        //Water and oil beetwen 0 and 1
        if( (fluid.getWater() >= 0 && fluid.getWater() <= 1) && (fluid.getOil() >= 0 && fluid.getOil() <= 1) &&
        fluid.getPercentagem() >= 0 && fluid.getPercentagem() <= 1){
            fluid.setFuel(fluid.getPercentagem()* fluid.getCar().getTankCapacity());
            return fluidService.addFluid(fluid);
        }
        else{
            throw new ResourceNotFoundException("Water, oil and Percentagem must be beetwen 0 and 1");
        }
    }

    @PostMapping("api/motorHistory")
    public MotorHistory createMotorHistoryRecord (@Valid @RequestBody MotorHistory motorHistory) throws ResourceNotFoundException{
        return motorHistoryService.saveMotorHistory(motorHistory);
    }

    @PostMapping("api/pneusHistory")
    public PneusHistory createPneusHistoryRecord (@Valid @RequestBody PneusHistory pneusHistory) throws ResourceNotFoundException{
        return pneusHistoryService.savePneusHistory(pneusHistory);
    }

    @PostMapping("api/luzes")
    public Luzes createLuzesRecord (@Valid @RequestBody Luzes luzes) throws ResourceNotFoundException{
        return luzesService.save(luzes);
    }

    // --------------------end -----------------

}

