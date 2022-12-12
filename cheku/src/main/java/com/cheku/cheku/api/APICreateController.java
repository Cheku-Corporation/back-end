package com.cheku.cheku.api;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.*;
import com.cheku.cheku.model.request.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.exception.ResourceNotFoundException;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class APICreateController {

    @Autowired
    private VelocityService velocityService;

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
    private CarService carService;

    //DONE
    @PostMapping("user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateRequest user) throws ResourceNotFoundException {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    //DONE
    @PostMapping("group")
    public ResponseEntity<Group> createGroup(@Valid @RequestBody GroupCreateRequest group) {
        return ResponseEntity.ok(groupService.createGroup(group));
    }

    @PostMapping("Register")
    public ResponseEntity<User> createRegister(@RequestBody String data) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        //Criar um new user
        UserCreateRequest user = mapper.readValue(data,UserCreateRequest.class);
        System.out.println(user);
        userService.createUser(user);

        //verificar se é criar ou entrar num grupo
        String groupID  = mapper.readTree(data).get("groupID").asText();
        if(groupID.isEmpty() ){
            //criar grupo
            System.out.println("Criar grupo");
            Optional<ApiUser> admin = userService.getUserByEmail(user.getEmail());
            GroupCreateRequest group = mapper.readValue(data,GroupCreateRequest.class);
            group.setAdmin(admin.get().getId());
            groupService.createGroup(group);
        }else{
            System.out.println("Entrar num grupo");
            //entrar num grupo
            Optional<ApiUser> user1 = userService.getUserByEmail(user.getEmail());
            groupService.addUserToGroup(user1.get().getId(),Long.parseLong(groupID));
        }
        return ResponseEntity.ok().build();
    }



    @PostMapping("car")
    public Car createCar(@Valid @RequestBody Car car) throws ResourceNotFoundException {

        // verificar se não existe um carro com a mesma matricula
        if (carService.existsByMatricula(car.getMatricula())) {
            System.out.println("Car already exists");
            throw new ResourceNotFoundException("Car already exists");
        }
        try {
            return carService.addCar(car);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Error saving car");
        }
    }
//    @PostMapping("api/user/{user_id}/group/{group_id}/car")
//    public List<Car> addCarToGroup(@PathVariable Long group_id, @PathVariable Long user_id, @Valid @RequestBody Car car) throws ResourceNotFoundException {
//        //verificar que o user é o dono do grupo
//        if(!groupService.verifyAdmin(user_id, group_id)){
//            throw new ResourceNotFoundException("User is not admin of the group");
//        }
//        //criar o carro
//        Car new_car = carService.addCar(car);
//        //adicionar o carro ao grupo
//        return groupService.addCarToGroup(group_id, new_car.getId());
//    }






    //-----------------------------REMOVER MAIS TARDE--------------------------------
    @PostMapping("api/velocity")
    public SpeedHistory createVelocityRecord (@Valid @RequestBody SpeedHistory velocity) throws ResourceNotFoundException{
        return velocityService.addVelocity(velocity);
    }

    @PostMapping("api/localization")
    public Localization createLocalizationRecord (@Valid @RequestBody Localization localization) throws ResourceNotFoundException{
        return localizationService.addLocalization(localization);
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

