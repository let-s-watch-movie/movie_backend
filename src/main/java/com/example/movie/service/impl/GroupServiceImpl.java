package com.example.movie.service.impl;

import com.example.movie.entity.User;
import com.example.movie.mapper.GroupMapper;
import com.example.movie.service.GroupService;
import com.example.movie.exception.GroupException;
import com.example.movie.util.DistanceUtil;
import com.example.movie.vo.UserDistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("groupService")
public class GroupServiceImpl implements GroupService {
	@Autowired
	GroupMapper groupMapper;
	
	@Override
	public List<UserDistance> queryMovieGroup(Integer movieId, String account) throws GroupException {
		List<UserDistance> users;
		try{
			users = toUserDistance(account, groupMapper.queryMovieGroup(movieId));
		 }catch (Exception e){
			 throw new GroupException(e.getMessage(),"获取群组用户失败");
		 }
		
		return users;
	}
	
	public List<UserDistance> toUserDistance(String account,List<User> users){
		List<UserDistance> userDistanceList = new ArrayList<>();
		User belong = null;
		
		for (User one : users){
			if (one.getAccount().equals(account)){
				belong = one;
			}
		}
		
		if (belong == null){
			return null;
		}
		
		for (User user : users){
			if (user.getAccount() == account){
				continue;
			}
			UserDistance userDistance = new UserDistance();
			userDistance.setAccount(user.getAccount());
			userDistance.setPassword(user.getPassword());
			userDistance.setAvatar(user.getAvatar());
			userDistance.setSex(user.getSex());
			userDistance.setRegisterTime(user.getRegisterTime());
			userDistance.setLatitude(user.getLatitude());
			userDistance.setLongitude(user.getLongitude());
			userDistance.setDistance(DistanceUtil.calculateDistance(belong,user));
			userDistanceList.add(userDistance);
		}
		
		return userDistanceList;
	}
}
