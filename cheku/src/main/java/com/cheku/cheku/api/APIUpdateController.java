package com.cheku.cheku.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIUpdateController {
    // @Autowired
	// private MovieService movieService;


	// @PutMapping("api/movies")
	// public List<Movie> allMovies(String name) {
	// 	return movieService.getMovies();
	// }

	// @PutMapping("api/quotes")
	// public Quote quoteFromMovie(@RequestParam(value = "serial", defaultValue = "27") String id) {
	// 	Movie m = movieService.getMovieById(Long.parseLong(id));
	// 	List<Quote> qs = quoteService.getQuoteByMovieId(m);
	// 	return qs.get((int)(Math.random()*(qs.size() + 1 )));
	// }
}
