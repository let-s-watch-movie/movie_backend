package com.example.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 指定对应application.yml配置文件中的的值前缀并绑定
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ConfigurationProperties(prefix = "user")
public class User {
	@Value("yyj")
	public String Nickname;
	
	public String Account;
	public String Password;
	
//	@Value("2023-06-26 17:35:11")
//	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
//	public Date create_time;
}
