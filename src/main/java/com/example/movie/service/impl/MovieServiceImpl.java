package com.example.movie.service.impl;

import com.example.movie.entity.Movie;
import com.example.movie.mapper.GroupMapper;
import com.example.movie.mapper.MovieMapper;
import com.example.movie.service.MovieService;
import com.example.movie.util.MovieUtil;
import com.example.movie.exception.GetMovieException;
import com.example.movie.exception.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service(value = "movieService")
public class MovieServiceImpl implements MovieService {
	@Autowired
	MovieMapper movieMapper;
	@Autowired
	GroupMapper groupMapper;
	
	@Override
	public void insertMovieList() throws GetMovieException {
		MovieUtil movieUtil = new MovieUtil();
		
		List<Movie> movies = movieUtil.getHotMovie();
		List<Integer> movieIds = new ArrayList<>();
		
		try{
			for (Movie movie : movies){
				Movie one = movieMapper.queryMovieById(movie.movieId);
				if (one == null || !Objects.equals(one.movieId, movie.movieId)){
					movieMapper.insertMovie(movie);
					movieIds.add(movie.movieId);
					continue;
				}
				movieMapper.updatePlayTime(movie.movieId);
			}
			if (movieIds.size() <= 0){
				return;
			}
			groupMapper.createGroup(movieIds);
			System.out.println("已更新"+(movies.size() - movieIds.size()) + "条数据");
			System.out.println("已插入"+movieIds.size()+"条数据");
		}catch (Exception e){
			throw new GetMovieException(e.getMessage());
		}
		
	}
	
	@Override
	public List<Movie> queryMovieList() throws MovieException {
		List<Movie> movies;
		try{
			movies =  movieMapper.queryMovieList();
		} catch (Exception e){
			throw new MovieException(e.getMessage(),"查询电影列表失败");
		}
		
		return movies;
	}
	
	@Override
	public Movie queryMovieById(Integer id) throws MovieException {
		Movie movie ;
		
		try{
			movie = movieMapper.queryMovieById(id);
		}catch (Exception e){
			throw new MovieException(e.getMessage(),"查看电影详情失败");
		}
		
		return movie;
	}
	public boolean isExist(Integer id, String account){
        return movieMapper.isExist(id, account);
    }
}

