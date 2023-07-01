package com.example.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

// 指定对应application.yml配置文件中的的值前缀并绑定
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ConfigurationProperties(prefix = "user")
public class User {
	@Id
	private String account;
	private String password;
	private String avatar;
	private String sex;
	private String	description;
	private int age;
	private Double longitude;
	private Double latitude;
	private Date registerTime;
	
//	@Value("2023-06-26 17:35:11")
//	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//	public Date create_time;
}
