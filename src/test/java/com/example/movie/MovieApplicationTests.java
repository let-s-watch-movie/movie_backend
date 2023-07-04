package com.example.movie;

import com.example.movie.service.impl.MovieServiceImpl;
import com.example.movie.exception.GetMovieException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieApplicationTests {
//	@Autowired
//	private User user;
	
	@Autowired
	MovieServiceImpl movieService;
	@Test
	void contextLoads() {
		try{
			movieService.insertMovieList();
		} catch (GetMovieException e) {
			throw new RuntimeException(e);
		}
	}
	
}
