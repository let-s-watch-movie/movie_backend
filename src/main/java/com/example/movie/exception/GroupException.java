package com.example.movie.exception;

public class GroupException  extends Exception{
	private String message;
	private String Tip;
	private Integer Code = 500501;
	
	public GroupException(String message,String tip){
		super(message);
		this.Tip = tip;
	}
	
	public Integer getCode() {
		return Code;
	}
	
	public String getTip(){
		return Tip;
	}
}
