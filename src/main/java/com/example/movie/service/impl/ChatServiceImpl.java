package com.example.movie.service.impl;

import com.example.movie.entity.ChatHistory;
import com.example.movie.exception.ChatException;
import com.example.movie.mapper.ChatMapper;
import com.example.movie.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	@Autowired
	ChatMapper chatMapper;
	
	@Override
	public void saveMessage(ChatHistory chatHistory) throws ChatException {
		try{
			chatMapper.saveMessage(chatHistory);
		}catch (Exception e){
			throw new ChatException(e.getMessage(),"保存记录失败");
		}
	}
	
	@Override
	public List<ChatHistory> getHistoryMessage(String fromUser, String toUser,Integer days) throws ChatException {
		List<ChatHistory> messages;
		try{
			messages = chatMapper.getHistoryMessage(fromUser,toUser,days);
		}catch (Exception e){
			throw new ChatException(e.getMessage(),"获取历史记录失败");
		}
		return messages;
	}
}
