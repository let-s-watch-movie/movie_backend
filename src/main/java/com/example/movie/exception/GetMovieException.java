package com.example.movie.exception;


import lombok.Getter;

@Getter
public class GetMovieException extends Exception{
	private String message;
	private String Tip;
	
	public GetMovieException(String message){
		super(message);
		this.message = message;
		this.Tip = "获取热门电影失败";
	}
}
