package com.example.movie.controller;

import com.example.movie.entity.ChatHistory;
import com.example.movie.exception.ChatException;
import com.example.movie.service.impl.ChatServiceImpl;
import com.example.movie.vo.Message;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@ServerEndpoint(value = "/websocket/{fromUser}/{toUser}")
@RestController
@Component
public class WebSocketController {
	
	static ChatServiceImpl chatService;
	
	public static void setChatService(ChatServiceImpl chatService){
		WebSocketController.chatService = chatService;
	}
	
	public static Map<String, WebSocketController> map = new HashMap<>();
	
	Session session;
	String fromUser;
	String toUser;
	
	@OnOpen
	public void onOpen(@PathParam("toUser") String toUser, @PathParam("fromUser") String fromUser, Session session){
		map.put(fromUser,this);
		System.out.println(fromUser + "connect successfully!");
		this.session = session;
		this.fromUser = fromUser;
		this.toUser = toUser;
	}
	
	@OnClose
	public void onClose(){
		map.remove(this.fromUser);
		System.out.println(this.fromUser+"disconnect from server!");
	}
	
	@OnError
	public void onError(Throwable error){
		error.printStackTrace();
		System.out.println(fromUser +"occurred to error");
	}
	
	@OnMessage
	public void onMessage(String message){
		if (map.containsKey(this.toUser)){
			this.sendMessage(new Message(message, new Date()));
		} else{
			saveMessage(new Message(message,null));
		}
	}
	
	public void sendMessage(Message message){
		if (session.isOpen()){
			try{
				session.getBasicRemote().sendText(message.getContent());
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void saveMessage(Message message){
		ChatHistory chatHistory = new ChatHistory(this.fromUser,this.toUser,
				new Date(), message.getContent(),0,0);
		try{
			chatService.saveMessage(chatHistory);
		} catch (ChatException e){
			System.out.println(e.getMessage());
			return;
		}
	}




//	public Boolean isFirstChat(String fromUser,String toUser){
//		//  调用数据库，查询是否存在这样的chat
//		//  存在就读取历史记录，查看是否有信息
//		//  不存在就跳过，直接进入OnOpen
//		return true;
//	}

}
