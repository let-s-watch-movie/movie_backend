package com.example.movie.mapper;

import com.example.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
	User queryUserByAccount(String account);
	void insertUser(User user);
	void updateLocation(User user);
	void updateUserInfo(User user);
	void updateUserPassword(String account,String new_password);
	List<User> getUsersWithinFiveKilometers(@Param("account") String account,@Param("movie_id") int movieId, @Param("longitude") double longitude, @Param("latitude") double latitude);

	void updateStatus(User user);
}
