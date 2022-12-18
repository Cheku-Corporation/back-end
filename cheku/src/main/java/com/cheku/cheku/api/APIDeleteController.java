package com.cheku.cheku.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.exception.ResourceNotFoundException;
import com.cheku.cheku.model.*;

@RestController
public class APIDeleteController {

    @Autowired
    private CarService carService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TripService tripService;

//    @DeleteMapping("api/user/{user_id}/group/{group_id}/car/{car_id}")
//    public String  deleteCar(@PathVariable Long user_id, @PathVariable Long group_id, @PathVariable Long car_id) {
//        //verificar se o user_id é o admin do grupo
//        if (groupService.verifyAdmin(user_id, group_id)) {
//            carService.deleteCar(car_id);
//            return "Carro apagado com sucesso";
//        }
//        return "Não tem permissões para apagar o carro";
//    }

    @DeleteMapping("api/user/{user_id}/group/{group_id}")
    public void  deleteCar(@PathVariable Long user_id, @PathVariable Long group_id) throws RuntimeException {
        //verificar se o user_id é o admin do grupo
        if (groupService.verifyAdmin(user_id, group_id)) {
            groupService.deleteGroup(group_id);
        }
        else{
            throw new RuntimeException("Não tem permissões para apagar o grupo");
        }
    }

    //remover e user de group
    //remover um carro 

    @DeleteMapping("api/trips")
    public void  deleteTrips() throws ResourceNotFoundException {
        tripService.deleteTrips();
    }
}
