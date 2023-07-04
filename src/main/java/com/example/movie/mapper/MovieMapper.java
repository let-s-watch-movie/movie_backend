package com.example.movie.mapper;

import com.example.movie.entity.Movie;
import com.example.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface MovieMapper {
	@Transactional
	void insertMovie(Movie movie);
	
	@Transactional
	void updatePlayTime(Integer movieId);
	
	@Transactional
	Movie queryMovieById(Integer id);
	
	@Transactional
	List<Movie> queryMovieList();


	boolean  isExist(Integer movie_id,String account);
}
