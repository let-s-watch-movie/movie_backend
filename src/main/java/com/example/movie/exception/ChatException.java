package com.example.movie.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatException extends Exception {
	private String message;
	private String Tip;
	
	public ChatException(String message,String tip){
		super(message);
		this.message = message;
		this.Tip = tip;
	}
}
