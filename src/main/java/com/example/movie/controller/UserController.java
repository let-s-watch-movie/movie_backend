package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.service.UserService;
import com.example.movie.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private final UserService userService;
    private Response response;
    String currentWorkingDir = System.getProperty("user.dir");
//    public String uploadPath = "./src/main/resources/static/avatar";
public String uploadPath = "http://localhost:8080/avatar/";



    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Response registerUser(@RequestBody User user) {
//        return userService.registerUser(user.getAccount(), user.getPassword());
        boolean isRegistered = userService.registerUser(user.getAccount(), user.getPassword());
        if(isRegistered){
            return response.Success("User registered successfully.");
        }
        return response.Error("User already exists.");
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public Response loginUser(@RequestBody User user) {
        boolean loginSuccessful = userService.loginUser(user.getAccount(), user.getPassword(), user.getLongitude(), user.getLatitude());
//        return userService.loginUser(user.getAccount(), user.getPassword(), user.getLongitude(), user.getLatitude());
//        return ResponseEntity.ok(loginSuccessful);
        if(loginSuccessful){
            return response.Success("User logged in successfully.");

        }
        return response.Error("User does not exist or password is incorrect.");
    }
    @PostMapping("/logout")
    public Response logoutUser(@RequestBody User user) {
        boolean logoutSuccessful = userService.logoutUser(user.getAccount());
//        return ResponseEntity.ok(logoutSuccessful);
        if(logoutSuccessful){
            return response.Success("User logged out successfully.");
        }
        return response.Error("User does not exist.");
    }

    @PostMapping("/getUserInfo")
    public Response getUserInfo(@RequestBody User user) {
        User userInfo;
        try{
        userInfo = userService.getUserInfo(user.getAccount());}catch (Exception e){
            System.out.println(e.getMessage());
            return response.Error("User does not exist.");
        }


//        return userInfo;
        return response.Success(userInfo);
    }


    @PostMapping("/updateUserInfo")
    public Response updateUserInfo(@RequestParam("account") String account,
//								  @RequestParam("password") String password,
                                  @RequestParam("avatar") MultipartFile avatarFile,
                                  @RequestParam("sex") String sex,
                                  @RequestParam("description") String description,
                                  @RequestParam("age") Integer age
    ) throws IOException {
//		System.out.println("age="+age);
        String avatar = "";
        if (!avatarFile.isEmpty()) {
            // 保存头像文件到指定路径
            String fileName = UUID.randomUUID() + "." + getFileExtension(avatarFile.getOriginalFilename());
//            String filePath = currentWorkingDir + "/" + uploadPath + "/" + fileName;
//            String filePath = uploadPath + "/" + fileName;
            String temp =  currentWorkingDir  + "/src/main/resources/static/avatar/" + fileName;
            String filePath = uploadPath + fileName;
            avatarFile.transferTo(new File(temp));
//			user.setAvatar(filePath);
            avatar = filePath;
        }

//		boolean isUpdated = userService.updateUserInfo(account, password, avatar, sex,age,description);
        boolean isUpdated = userService.updateUserInfo(account, avatar, sex, age, description);
        if(isUpdated)
            return response.Success("User info updated successfully.");
        return response.Error("User does not exist.");
    }

    @GetMapping("/updateUserPassword")
    public Response updateUserPassword(@RequestParam("account") String account,
                                      @RequestParam("old_password") String old_password,
                                      @RequestParam("new_password") String new_password) {
        userService.updateUserPassword(account, old_password, new_password);
        return response.Success("User password updated successfully.");


    }

    //	@PostMapping("/within-five-kilometers")
//	public List<User> getUsersWithinFiveKilometers(@RequestParam("movie_id") int movie_id,
//												   @RequestParam("longitude") double longitude,
//												   @RequestParam("latitude") double latitude) {
//		return userService.getUsersWithinFiveKilometers(movie_id, longitude, latitude);
//	}
    @PostMapping("/within-five-kilometers")
    public List<User> getUsersWithinFiveKilometers(@RequestParam("account") String account,
                                                   @RequestParam("movie_id") int movie_id
                                                   ) {
//        return userService.getUsersWithinFiveKilometers(movie_id, longitude, latitude);
		User userInfo = userService.queryUserByAccount(account);
		Double longitude = userInfo.getLongitude();
		Double latitude = userInfo.getLatitude();
		return userService.getUsersWithinFiveKilometers(account, movie_id,longitude, latitude);

    }


    private String getFileExtension(String originalFilename) {
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

}

