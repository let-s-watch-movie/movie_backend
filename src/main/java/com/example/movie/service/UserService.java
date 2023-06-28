package com.example.movie.service;


import com.example.movie.entity.User;

public interface UserService {
	User queryUserByAccount(String Account);
}
