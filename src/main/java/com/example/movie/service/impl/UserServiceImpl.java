package com.example.movie.service.impl;

import com.example.movie.mapper.UserMapper;
import com.example.movie.entity.User;
import com.example.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User queryUserByAccount(String account){
		return userMapper.queryUserByAccount(account);
	}
}
