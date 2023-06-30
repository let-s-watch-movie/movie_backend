package com.example.movie.service;


import com.example.movie.entity.User;

import java.util.List;

public interface UserService {

	public boolean registerUser(String account, String password);
	public boolean loginUser(String account, String password,Double longitude,Double latitude);
	public User getUserInfo(String account);
//	public boolean updateUserInfo(String account, String password, String avatar, String sex,Integer age,String description);
	public boolean updateUserInfo(String account,  String avatar, String sex,Integer age,String description);
	public boolean updateUserPassword(String account,String old_password,String new_password);
	User queryUserByAccount(String account);

	List<User> getUsersWithinFiveKilometers(int movieId, double userLongitude, double userLatitude);
}
