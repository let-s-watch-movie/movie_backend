package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.service.impl.GroupServiceImpl;
import com.example.movie.util.Response;
import com.example.movie.exception.GroupException;
import com.example.movie.vo.UserDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {
	@Autowired
	GroupServiceImpl groupService;
	
	@GetMapping("/group/user")
	public Response queryMovieGroup(@RequestParam Integer movieId,@RequestParam String account){
		List<UserDistance> users = new ArrayList<>();
		try{
			users = groupService.queryMovieGroup(movieId,account);
		} catch (GroupException e) {
			System.out.println(e.getMessage());
			return Response.Error(e.getTip());
		}
		
		return Response.Success(users);
	}
}

