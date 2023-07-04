package com.example.movie.controller;

import com.example.movie.entity.Movie;
import com.example.movie.exception.GetMovieException;
import com.example.movie.service.impl.MovieServiceImpl;
import com.example.movie.util.Response;
import com.example.movie.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
	@Autowired
	MovieServiceImpl movieService;
	
	@GetMapping("/movie/list")
	public Response getMovieList(){
		List<Movie> movies;
		try{
			movies = movieService.queryMovieList();
		}catch (MovieException e){
			System.out.println(e.getMessage());
			return Response.Error(e.getTip());
		}
		
		return Response.Success(movies);
	}
	
	@GetMapping("/movie/info")
	public Response getMovieInfo(@RequestParam Integer movie_id){
		Movie movie;
		try{
			movie = movieService.queryMovieById(movie_id);
		}catch (MovieException e){
			System.out.println(e.getMessage());
			return Response.Error(e.getTip());
		}
		
		return Response.Success(movie);
	}
	
	@Scheduled(initialDelay = 24*60*60*1000, fixedRate = 24 * 60 * 60 * 1000) // 每24小时执行一次
	public void GetHotMovie(){
		System.out.println("start get movie list...");
		try{
			movieService.insertMovieList();
		} catch (GetMovieException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("get movie list over!");
	}
}
