package com.example.movie.mapper;

import com.example.movie.entity.ChatHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface ChatMapper {
	@Transactional
	List<ChatHistory> getHistoryMessage(String fromUser,String toUser,Integer days);
	@Transactional
	void saveMessage(ChatHistory chatHistory);
}
