package com.example.movie.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	@JSONField(name = "id")
	public Integer movieId;
	
	@JSONField(name = "img")
	public String poster;
	
	@JSONField(name = "nm")
	public String movieName;
	
	@JSONField(name = "rt",format = "yyyy-MM-dd")
	public Date releasedTime;
	
	@JSONField(name = "star")
	public String star;
	
	@JSONField(name = "sc")
	public Float score;
	
	@JSONField(name = "dra")
	public String summary;
	
	@JSONField(name = "dir")
	public String director;
	
	@JSONField(name = "oriLang")
	public String language;
	
	@JSONField(name = "dur")
	public Integer duration;
	
	@JSONField(name = "scm")
	public String shortComment;
	
	@JSONField(name = "src")
	public String srcLocation;
	
	@JSONField(name = "cat")
	public String category;
	
	@JSONField(name = "vd")
	public String video;
}
