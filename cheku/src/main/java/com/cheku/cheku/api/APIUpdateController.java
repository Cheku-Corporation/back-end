package com.cheku.cheku.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cheku.cheku.model.Car;
import com.cheku.cheku.service.CarService;



@RestController
public class APIUpdateController {

	// @PutMapping("api/quotes")
	// public Quote quoteFromMovie(@RequestParam(value = "serial", defaultValue = "27") String id) {
	// 	Movie m = movieService.getMovieById(Long.parseLong(id));
	// 	List<Quote> qs = quoteService.getQuoteByMovieId(m);
	// 	return qs.get((int)(Math.random()*(qs.size() + 1 )));
	// }

    @Autowired
    private CarService carService;

    @PutMapping("api/car")
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

}
