package com.example.movie.service.impl;

import com.example.movie.entity.User;
import com.example.movie.mapper.UserMapper;
import com.example.movie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    String currentWorkingDir = System.getProperty("user.dir");
    String defaultAvatar = currentWorkingDir + '/' + "src/main/resources/static/avatar/default.jpg";

    @Override
    public boolean registerUser(String account, String password) {

        // 检查用户名是否已存在
        if (userMapper.queryUserByAccount(account) != null) {
            return false; // 用户名已存在，注册失败
        }
        // 创建新用户
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setRegisterTime(new Date());
        user.setAvatar(defaultAvatar);

        // 插入用户数据
        userMapper.insertUser(user);
        return true;


    }


    @Override
    public boolean loginUser(String username, String password, Double longitude, Double latitude) {
        User user = userMapper.queryUserByAccount(username);
        user.setLongitude(longitude);
        user.setLatitude(latitude);
        if (user != null && user.getPassword().equals(password)) {
            userMapper.updateLocation(user);
            return true;
        }
        // 检查用户是否存在并验证密码
        return false;

    }


    @Override
    public User getUserInfo(String account) {
        // 调用相应的 Mapper 方法来查询数据库，获取用户信息
        User user = queryUserByAccount(account);

        return user;
    }

    public boolean updateUserInfo(String account, String avatar, String sex, Integer age, String description) {
        User user = userMapper.queryUserByAccount(account);

        if (user != null) {
            // 更新用户信息字段
//            user.setPassword(password);
            user.setAvatar(avatar);
            user.setSex(sex);
            user.setDescription(description);
            user.setAge(age);

            userMapper.updateUserInfo(user);
            return true;
        }
        return false;
    }

    public boolean updateUserPassword(String account, String old_password, String new_password) {

        User user = userMapper.queryUserByAccount(account);

        if (user != null && user.getPassword().equals(old_password)) {
            // 替换密码
            user.setPassword(new_password);
            userMapper.updateUserPassword(account, new_password);
            return true;
        }
        return false;
    }

    @Override
    public User queryUserByAccount(String account) {
        return userMapper.queryUserByAccount(account);
    }

    @Override
    public List<User> getUsersWithinFiveKilometers(String account, int movieId, double userLongitude, double userLatitude) {
        return userMapper.getUsersWithinFiveKilometers(account, movieId, userLongitude, userLatitude);
    }


}