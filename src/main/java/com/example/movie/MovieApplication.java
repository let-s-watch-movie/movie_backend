package com.example.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.example.movie.mapper")
public class MovieApplication {
	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(MovieApplication.class, args);
		
	}
	
}
