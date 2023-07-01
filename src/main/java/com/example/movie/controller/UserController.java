package com.example.movie.controller;

import com.example.movie.entity.User;
import com.example.movie.service.UserService;
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
    String currentWorkingDir = System.getProperty("user.dir");
    public String uploadPath = "src/main/resources/static/avatar";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user.getAccount(), user.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody User user) {
        boolean loginSuccessful = userService.loginUser(user.getAccount(), user.getPassword(), user.getLongitude(), user.getLatitude());
        return ResponseEntity.ok(loginSuccessful);
    }

    @PostMapping("/getUserInfo")
    public User getUserInfo(@RequestBody User user) {
        User userInfo = userService.getUserInfo(user.getAccount());
        return userInfo;
    }

    @PostMapping("/updateUserInfo")
    public boolean updateUserInfo(@RequestParam("account") String account,
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
            String filePath = currentWorkingDir + "/" + uploadPath + "/" + fileName;
            avatarFile.transferTo(new File(filePath));

//			user.setAvatar(filePath);
            avatar = filePath;
        }

//		boolean isUpdated = userService.updateUserInfo(account, password, avatar, sex,age,description);
        boolean isUpdated = userService.updateUserInfo(account, avatar, sex, age, description);

        return isUpdated;
    }

    @PostMapping("/updateUserPassword")
    public boolean updateUserPassword(@RequestParam("account") String account,
                                      @RequestParam("old_password") String old_password,
                                      @RequestParam("new_password") String new_password) {
        return userService.updateUserPassword(account, old_password, new_password);


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

