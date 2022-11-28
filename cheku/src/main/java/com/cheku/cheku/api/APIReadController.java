package com.cheku.cheku.api;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cheku.cheku.model.Car;
import com.cheku.cheku.model.HistoryVelocity;
import com.cheku.cheku.service.VelocityService;

@RestController
public class APIReadController {
    
	@Autowired
	private VelocityService velocityService;

	@GetMapping("api/velocity")
	public List<HistoryVelocity> getCarVelocities() {
		//return movieService.getMovies();
		velocityService.save(new HistoryVelocity(Long.parseLong("4"), (Double) 10.0, 3, new Date(), new Car()));
		List<HistoryVelocity> vel = velocityService.getAllVelocity();
		return vel;
	}

	// @GetMapping("api/quotes")
	// public Quote quoteFromMovie(@RequestParam(value = "serial", defaultValue = "27") String id) {
	// 	Movie m = movieService.getMovieById(Long.parseLong(id));
	// 	List<Quote> qs = quoteService.getQuoteByMovieId(m);
	// 	return qs.get((int)(Math.random()*(qs.size() + 1 )));
	// }
}
