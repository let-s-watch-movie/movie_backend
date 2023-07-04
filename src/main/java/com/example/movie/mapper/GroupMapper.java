package com.example.movie.mapper;

import com.example.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface GroupMapper {
	@Transactional
	void createGroup(List<Integer> movieIds);
	
	@Transactional
	List<User> queryMovieGroup(Integer MovieId);
}
