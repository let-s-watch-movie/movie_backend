package com.example.movie.controller;

import com.example.movie.entity.ChatHistory;
import com.example.movie.exception.ChatException;
import com.example.movie.service.impl.ChatServiceImpl;
import com.example.movie.util.Response;
import com.example.movie.vo.Message;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ChatController {
	@Autowired
	ChatServiceImpl chatService;
	
	@GetMapping("/chat/history/{user}/{fromUser}")
	public Response getChatHistory(@PathVariable("user") String user, @PathVariable("fromUser") String fromUser,@RequestParam Integer days){
		List<ChatHistory> chatHistories;
		List<Message> messages = new ArrayList<>();
		try{
			chatHistories = chatService.getHistoryMessage(fromUser,user,days);
			for (ChatHistory history : chatHistories){
				messages.add(new Message(history.getContent(),history.getTimeStamp()));
			}
		}catch (ChatException e){
			System.out.println(e.getMessage());
			return Response.Error(e.getTip());
		}
		return Response.Success(messages);
	}
}
