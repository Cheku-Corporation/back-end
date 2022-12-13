package com.cheku.cheku.api;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private PneusHistoryService pneusHistoryService;

    @Autowired
    private LightsService luzesService;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private CarService carService;

    @PostMapping("register")
    public String createRegister(@RequestBody String data) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //verificar se é criar ou entrar num grupo
            String groupId = mapper.readTree(data).get("groupId").asText();
            String groupName = mapper.readTree(data).get("groupName").asText();

            //verificar se o grupo existe
            if (!groupName.isEmpty() && groupService.findGroupByName(groupName)) {
                throw new RuntimeException("The group already exists");
            }

            if (!groupId.isEmpty() && groupService.findGroupById(Long.parseLong(groupId))) {
                throw new RuntimeException("The group not exists");
            }

            if (groupId.isEmpty() && !groupName.isEmpty()) {

                //Criar um new user
                UserCreateRequest user = mapper.readValue(data, UserCreateRequest.class);
                userService.createUser(user);

                //criar grupo
                Optional<ApiUser> admin = userService.getUserByEmail(user.getEmail());
                GroupCreateRequest group = mapper.readValue(data, GroupCreateRequest.class);
                group.setAdmin(admin.get().getId());
                groupService.createGroup(group);
                return "Group created";
            } else if (!groupId.isEmpty() && groupName.isEmpty()) {

                //Criar um new user
                UserCreateRequest user = mapper.readValue(data, UserCreateRequest.class);
                userService.createUser(user);

                //entrar num grupo
                Optional<ApiUser> user1 = userService.getUserByEmail(user.getEmail());
                groupService.addUserToGroup(user1.get().getId(), Long.parseLong(groupId));

                return "User added to group";
            } else {
                throw new RuntimeException("Erro ao criar o registo");
            }
    }



    @PostMapping("car")
    public Car createCar(@Valid @RequestBody Car car) throws ResourceNotFoundException {
        // verificar se não existe um carro com a mesma matricula
        if (carService.existsByMatricula(car.getMatricula())) {
            System.out.println("Car already exists");
            throw new ResourceNotFoundException("Car already exists");
        }
        return carService.addCar(car);
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
}

