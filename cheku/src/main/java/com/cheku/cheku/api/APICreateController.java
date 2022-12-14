package com.cheku.cheku.api;

import javax.persistence.OneToMany;
import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public Object createRegister(@RequestBody String data) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            if (data.contains("ADMIN")){
                //criar user
                UserCreateRequest userCreateRequest = mapper.readValue(data, UserCreateRequest.class);
                userService.createUser(userCreateRequest);
                Object response = new Object() {
                    public final String success = "true";
                    public final String message = "Admin created";
                };
                return response;
            }
            //verificar se é criar ou entrar num grupo
            String groupId = mapper.readTree(data).get("groupId").asText();
            String groupName = mapper.readTree(data).get("groupName").asText();

            //verificar se o grupo existe
            if (!groupName.isEmpty() && groupService.findGroupByName(groupName)) {
                Object response = new Object() {
                    public final String error = "The group already exists";
                };
                return response;
            }

            if (!groupId.isEmpty() && !groupService.findGroupById(Long.parseLong(groupId))) {
                Object response = new Object() {
                    public final String error = "The group not exists";
                };
                return response;
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
                //returnar json success
                Object response = new Object() {
                    public final String success = "true";
                    public final String message = "Group created";
                };
                return response;
            } else if (!groupId.isEmpty() && groupName.isEmpty()) {

                //Criar um new user
                UserCreateRequest user = mapper.readValue(data, UserCreateRequest.class);
                userService.createUser(user);

                //entrar num grupo
                Optional<ApiUser> user1 = userService.getUserByEmail(user.getEmail());
                groupService.addUserToGroup(user1.get().getId(), Long.parseLong(groupId));

                Object response = new Object() {
                    public final String success = "true";
                    public final String message = "User added to group";
                };
                return response;

            } else {
                throw new RuntimeException("Erro ao criar o registo");
            }
    }


    @PostMapping("car")
    public Car createCar(@Valid @RequestBody String data) throws ResourceNotFoundException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Car car = mapper.readValue(data, Car.class);
        Long userId = mapper.readTree(data).get("userId").asLong();
        System.out.println(car);


        // verificar se não existe um carro com a mesma matricula
        if (carService.existsByMatricula(car.getMatricula())) {
            throw new ResourceNotFoundException("Car already exists");
        }
        //validação que o user é admin
        return carService.addCar(car, userId);

    }

}

