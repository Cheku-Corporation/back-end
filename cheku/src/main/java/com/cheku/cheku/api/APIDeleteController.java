package com.cheku.cheku.api;


import com.cheku.cheku.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.model.*;

@RestController
public class APIDeleteController {

    @Autowired
    private CarService carService;

    @Autowired
    private GroupService groupService;

    @DeleteMapping("api/user/{user_id}/group/{group_id}/car/{car_id}")
    public Object deleteCar(@PathVariable Long car_id, @PathVariable Long group_id, @PathVariable Long user_id ) throws ResourceNotFoundException {
        //verificar se o user_id é o admin do grupo
        if (groupService.verifyAdmin(user_id, group_id)) {
            carService.deleteCar(car_id);
            Object response = new Object() {
                public final String sucesso = "Carro apagado com sucesso";
            };
            return response;
        } else {
            Object response = new Object() {
                public final String message = "Não tem permissões para apagar o carro";
            };
            return response;
        }
    }

}
