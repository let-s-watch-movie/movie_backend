package com.example.movie.service;

import com.example.movie.entity.*;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface ChatService {
    public void sendChatRequest(ChatHistory chatHistory);

//    public void acceptChatRequest(Long chatId);

    public void acceptChatRequest(ChatRequest chatRequest);
    public void refuseChatRequest(ChatRequest chatRequest);

//    public void terminateChat(Long chatId, String account) throws NotFoundException;
    public void inviterTerminateChat(ChatRequest chatRequest);
    public void receiverTerminateChat(ChatRequest chatRequest);

    //发送一条聊天记录
    public void sendChatHistory(ChatHistory chatHistory);

    ChatHistory queryChatRequest(ChatHistory chatHistory);
    public List<ChatHistory> queryChatHistory(ChatHistory chatHistory);
    public void setSent(Integer id);

    List<ChatRequest> queryChatRequestByReceiver(ChatHistory chatHistory);
}
