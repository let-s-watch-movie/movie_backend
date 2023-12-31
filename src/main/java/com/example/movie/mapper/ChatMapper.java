package com.example.movie.mapper;


import com.example.movie.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ChatMapper {
    // 其他方法...

    void insertChatRequest(ChatRequest chatRequest);

//    void updateChatRequestStatus(Long chatId, Integer status);
    void updateChatRequestStatus(ChatRequest chatRequest);

    ChatRequest selectChatRequestById(Long chatId);

    void insertChatHistory(ChatHistory chatHistory);
    ChatHistory queryChatRequest(ChatHistory chatHistory);
    List<ChatHistory> queryChatHistory(ChatHistory chatHistory);
    void setSent(Integer id);

    List<ChatRequest> queryChatRequestByReceiver(ChatHistory chatHistory);
}
