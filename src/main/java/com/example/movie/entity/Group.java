package com.example.movie.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class Group {
	public Integer movieId;
	public Date groupTime;
	public Integer totalNum;
}
