package com.cheku.cheku.api;

import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.service.GroupService;
import com.cheku.cheku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.service.CarService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class APIUpdateController {

    @Autowired
    private CarService carService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @PutMapping("user/{user_id}/group/{group_id}/car/{car_id}")
    public Car updateCar(@PathVariable Long car_id, @PathVariable Long group_id, @PathVariable Long user_id, @Valid @RequestBody Car car) throws ResourceNotFoundException {
        //verificar se o user_id é o admin do grupo
        if (groupService.verifyAdmin(user_id, group_id) && carService.verifyCar(car_id, group_id)) {
            return carService.updateCar(car_id, car);
        }
        else{
            throw new ResourceNotFoundException("Não tem permissões para editar o carro");
        }
    }

//editar car
//user
//group
}
