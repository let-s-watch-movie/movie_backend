package com.example.movie.util;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	@JSONField(name = "message")
	String Message;
	
	@JSONField(name = "data")
	Object Data;
	
	@JSONField(name = "code")
	Integer Code;
	
	public static Response Success(Object data){
		return new Response("操作成功",data,200);
	}
	
	public static Response Error(String message){
		return new Response(message,null,500);
	}
	
	public static Response Custom(String message,Object data){
		return new Response(message,data,200);
	}
}
