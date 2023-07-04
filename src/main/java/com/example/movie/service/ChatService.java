package com.example.movie.service;

import com.example.movie.entity.ChatHistory;
import com.example.movie.exception.ChatException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ChatService {
	void saveMessage(ChatHistory chatHistory) throws ChatException;
	List<ChatHistory> getHistoryMessage(String fromUser,String toUser,Integer days) throws ChatException;
	
}
