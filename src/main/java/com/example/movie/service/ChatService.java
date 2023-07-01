package com.example.movie.service;
import com.example.movie.entity.*;
import org.apache.ibatis.javassist.NotFoundException;

public interface ChatService {
    public void sendChatRequest(ChatHistory chatHistory);

    public void acceptChatRequest(Long chatId);

    public void refuseChatRequest(Long chatId);

    public void terminateChat(Long chatId, String account) throws NotFoundException;

    //发送一条聊天记录
    public void sendChatHistory(ChatHistory chatHistory);

    int queryChatRequest(ChatHistory chatHistory);
}
