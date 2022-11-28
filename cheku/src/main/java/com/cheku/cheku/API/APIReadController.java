package com.cheku.cheku.api;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


public class APIReadController {
    // @Autowired
	// private MovieService movieService;

	@GetMapping("api/velocity")
	public List<Double> getCarVelocities() {
		//return movieService.getMovies();
		List<Double> vel = new ArrayList<Double>();
		vel.add(5.6);
		vel.add(6.7);
		vel.add(7.9);
		vel.add(9.3);
		return vel;
	}

	// @GetMapping("api/quotes")
	// public Quote quoteFromMovie(@RequestParam(value = "serial", defaultValue = "27") String id) {
	// 	Movie m = movieService.getMovieById(Long.parseLong(id));
	// 	List<Quote> qs = quoteService.getQuoteByMovieId(m);
	// 	return qs.get((int)(Math.random()*(qs.size() + 1 )));
	// }
}
