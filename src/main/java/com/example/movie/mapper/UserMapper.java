package com.example.movie.mapper;

import com.example.movie.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
	User queryUserByAccount(String account);
}
