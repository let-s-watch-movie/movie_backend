package com.example.movie.service;

import com.example.movie.entity.Movie;
import com.example.movie.exception.GetMovieException;
import com.example.movie.exception.MovieException;

import java.util.List;

public interface MovieService {
	
	public Movie queryMovieById(Integer id) throws MovieException;
	
	void insertMovieList() throws GetMovieException;
	
	public List<Movie> queryMovieList() throws MovieException;

	boolean isExist(Integer id,String account);
	
}
