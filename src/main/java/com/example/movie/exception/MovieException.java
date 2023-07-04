package com.example.movie.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MovieException extends Exception{
	private String message;
	private String Tip;
	private Integer Code = 500501;
	
	public MovieException(String message, String tip){
		super(message);
		this.message = message;
		this.Tip = tip;
	}
}
