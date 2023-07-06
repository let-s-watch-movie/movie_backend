package com.example.movie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.example.movie.mapper")
public class MovieApplication {
	public static void main(String[] args) {
//		System.out.println("hello");
//		System.out.println("2t34");
//		System.out.println("3t34");
		SpringApplication.run(MovieApplication.class, args);
		
	}
	
}

