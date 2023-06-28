package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping("/user")
	public void getUser(){
		User user = userService.queryUserByAccount("2021213990");
		System.out.println(user);
	}
}
