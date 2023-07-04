package com.example.movie.vo;

import com.example.movie.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDistance {
	private String account;
	private String password;
	private String avatar;
	private String sex;
	private Double longitude;
	private Double latitude;
	private Date registerTime;
	private double distance;
	
}
