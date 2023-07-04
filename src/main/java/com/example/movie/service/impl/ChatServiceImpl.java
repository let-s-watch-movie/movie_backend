package com.example.movie.service.impl;
import com.example.movie.entity.ChatHistory;
import com.example.movie.entity.ChatRequest;
import com.example.movie.mapper.ChatMapper;
import com.example.movie.mapper.UserMapper;
import com.example.movie.service.ChatService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;


@Service(value = "chatService")
public class ChatServiceImpl implements ChatService {
    @Autowired
    private final ChatMapper chatMapper;


    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    @Override
    public void sendChatRequest(ChatHistory chatHistory) {
        String inviterAccount = chatHistory.getSendingAccount();
        String receiverAccount = chatHistory.getReceiverAccount();
        // 创建chat_request记录
        ChatRequest chatRequest = ChatRequest.builder()
                .inviterAccount(inviterAccount)
                .receiverAccount(receiverAccount)
                .status(0)
                .inviteTime(LocalDateTime.now())
                .build();
        // 调用对应的数据访问层方法，插入chat_request记录
        System.out.println(inviterAccount);
        chatMapper.insertChatRequest(chatRequest);
    }
    @Override
    public Integer queryChatRequest(ChatHistory chatHistory){
        return chatMapper.queryChatRequest(chatHistory);

    }


//    public void acceptChatRequest(Long chatId) {
//        // 更新chat_request表的记录，将状态更新为已接受（1）
//        chatMapper.updateChatRequestStatus(chatId, 1);
//    }
    @Override
    public void acceptChatRequest(ChatRequest chatRequest) {
        chatRequest.setStatus(1);
        // 更新chat_request表的记录，将状态更新为已接受（1）
        chatMapper.updateChatRequestStatus(chatRequest);
    }

    @Override
    public void refuseChatRequest(ChatRequest chatRequest) {
        // 更新chat_request表的记录，将状态更新为已拒绝（2）
        chatRequest.setStatus(2);
        chatMapper.updateChatRequestStatus(chatRequest);
    }
//    @Override
//    public void terminateChat(Long chatId, String account) throws NotFoundException {
//        // 判断当前操作用户是发起者还是接收者
//        ChatRequest chatRequest = chatMapper.selectChatRequestById(chatId);
//        if (chatRequest == null) {
//            throw new NotFoundException("Chat request not found");
//        }
//
//        if (account.equals(chatRequest.getInviterAccount())) {
//            // 发起者终止
////            if (chatRequest.getStatus() == 1) {
////                throw new BadRequestException("Cannot terminate an accepted chat");
////            }
//            chatMapper.updateChatRequestStatus(chatId, 3);
//        } else if (account.equals(chatRequest.getReceiverAccount())) {
//            // 接收者终止
////            if (chatRequest.getStatus() == 1) {
////                throw new BadRequestException("Cannot terminate an accepted chat");
////            }
//            chatMapper.updateChatRequestStatus(chatId, 4);
//        }
////        else {
////            throw new ForbiddenException("Not authorized to terminate this chat");
////        }
//    }
    @Override
    public void inviterTerminateChat(ChatRequest chatRequest){
        chatRequest.setStatus(3);
        chatMapper.updateChatRequestStatus(chatRequest);
    }
    @Override
    public void receiverTerminateChat(ChatRequest chatRequest){
        chatRequest.setStatus(4);
        chatMapper.updateChatRequestStatus(chatRequest);
    }

    @Override
    public void sendChatHistory(ChatHistory chatHistory) {
        chatMapper.insertChatHistory(chatHistory);
    }

    public List<ChatHistory> queryChatHistory(ChatHistory chatHistory){
        return chatMapper.queryChatHistory(chatHistory);
    }
    public void setSent(Integer id){
        chatMapper.setSent(id);
    }
}
