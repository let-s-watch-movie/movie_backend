package com.example.movie.service;

import com.example.movie.entity.User;
import com.example.movie.exception.GroupException;
import com.example.movie.vo.UserDistance;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GroupService {
	List<UserDistance> queryMovieGroup(Integer movieId,String account) throws GroupException;
	List<UserDistance> toUserDistance(String account,List<User> user);
}
