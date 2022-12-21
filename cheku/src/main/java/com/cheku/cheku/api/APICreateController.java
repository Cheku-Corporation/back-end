package com.cheku.cheku.api;

import java.util.Optional;

import javax.validation.Valid;

import com.cheku.cheku.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.ApiUser;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.request.GroupCreateRequest;
import com.cheku.cheku.model.request.UserCreateRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A controller for creating various types of resources.
 */
@RestController
@RequestMapping("/api")
public class APICreateController {

    private final VelocityService velocityService;
    private final LocalizationService localizationService;
    private final TiresHistoryService pneusHistoryService;
    private final LightsService luzesService;
    private final UserService userService;
    private final GroupService groupService;
    private final CarService carService;


    @Autowired
    public APICreateController(VelocityService velocityService, LocalizationService localizationService,TiresHistoryService pneusHistoryService, LightsService luzesService, UserService userService,GroupService groupService, CarService carService) {
        this.velocityService = velocityService;
        this.localizationService = localizationService;
        this.pneusHistoryService = pneusHistoryService;
        this.luzesService = luzesService;
        this.userService = userService;
        this.groupService = groupService;
        this.carService = carService;
    }

    /**Creates a new registration.*/
    @PostMapping("register")
    public Object createRegister(@RequestBody String data) throws JsonProcessingException, ResourceNotFoundException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (data.contains("ADMIN")) {
            // Create user
            UserCreateRequest userCreateRequest = mapper.readValue(data, UserCreateRequest.class);
            userService.createUser(userCreateRequest);
            Object response = new Object() {
                public final String success = "Admin created";
            };
            return response;
        }
        // Check if creating or joining a group
        String groupId = mapper.readTree(data).get("groupId").asText();
        String groupName = mapper.readTree(data).get("groupName").asText();

        // Check if group exists
        if (!groupName.isEmpty() && groupService.findGroupByName(groupName)) {
            throw new ResourceNotFoundException("The group already exists");
        }
        if (!groupId.isEmpty() && !groupService.findGroupById(Long.parseLong(groupId))) {
            throw new ResourceNotFoundException("The group does not exist");
        }
        if (groupId.isEmpty() && !groupName.isEmpty()) {
            // Create new user
            UserCreateRequest user = mapper.readValue(data, UserCreateRequest.class);
            userService.createUser(user);
            // Create group
            Optional<ApiUser> admin = userService.getUserByEmail(user.getEmail());
            GroupCreateRequest group = mapper.readValue(data, GroupCreateRequest.class);
            group.setAdmin(admin.get().getId());
            groupService.createGroup(group);
            // Return success
            Object response = new Object() {
                public final String success = "Group created";
            };
            return response;
        } else if (!groupId.isEmpty() && groupName.isEmpty()) {
            // Create new user
            UserCreateRequest user = mapper.readValue(data, UserCreateRequest.class);
            userService.createUser(user);
            // Join group
            Optional<ApiUser> user1 = userService.getUserByEmail(user.getEmail());
            groupService.addUserToGroup(user1.get().getId(), Long.parseLong(groupId));
            Object response = new Object() {
                public final String success = "User added to group";
            };
            return response;
        } else {
            throw new ResourceNotFoundException("Error creating registration");
        }
    }


    @PostMapping("car")
    public Car createCar(@Valid @RequestBody String data) throws ResourceNotFoundException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Car car = mapper.readValue(data, Car.class);
        Long userId = mapper.readTree(data).get("userId").asLong();


        // verificar se não existe um carro com a mesma matricula
        if (carService.existsByMatricula(car.getMatricula())) {
            throw new ResourceNotFoundException("Car already exists");
        }
        //validação que o user é admin
        return carService.addCar(car, userId);

    }

}

