package com.example.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

// 指定对应application.yml配置文件中的的值前缀并绑定
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String account;
	private String password;
	private String avatar;
	private String sex;
	private Double longitude;
	private Double latitude;
	private Date registerTime;
}
