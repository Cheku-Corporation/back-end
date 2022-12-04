package com.cheku.cheku.api;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cheku.cheku.service.*;
import com.cheku.cheku.model.*;

@RestController
public class APIDeleteController {

    @Autowired
    private CarService carService;

    @DeleteMapping("api/car/{car_id}")
    public String  deleteCar(@PathVariable Long car_id) {
         return carService.deleteCar(car_id);
    }
}
