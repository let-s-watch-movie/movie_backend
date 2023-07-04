package com.example.movie.exception;

import lombok.Getter;

@Getter
public class MemberException extends Exception {
	private String message;
	private String Tip;
	private Integer Code = 500501;
	public MemberException(String message,String tip){
		super(message);
		this.message = message;
		this.Tip = tip;
	}
}
