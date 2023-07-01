package com.example.movie.service;
import com.example.movie.entity.*;
import org.apache.ibatis.javassist.NotFoundException;

public interface ChatService {
    public void sendChatRequest(String inviterAccount, String receiverAccount);

    public void acceptChatRequest(Long chatId);

    public void refuseChatRequest(Long chatId);

    public void terminateChat(Long chatId, String account) throws NotFoundException;
}
