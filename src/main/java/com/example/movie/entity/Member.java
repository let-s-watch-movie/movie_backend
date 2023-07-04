package com.example.movie.entity;


import lombok.*;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
public class Member {
	public Integer movieId;
	public String account;
}
