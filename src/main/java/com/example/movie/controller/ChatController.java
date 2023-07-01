package com.example.movie.controller;

import com.example.movie.entity.ChatHistory;
import com.example.movie.entity.ChatMessage;
import com.example.movie.entity.ChatRequest;
import com.example.movie.mapper.ChatMapper;
import com.example.movie.service.ChatService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate, ChatMapper chatMapper) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
        this.chatMapper = chatMapper;
    }

    //    @PostMapping("/request")
//    public ResponseEntity<Object> sendChatRequest(@RequestParam("inviter_account") String inviterAccount,
//                                                  @RequestParam("receiver_account") String receiverAccount,
//                                                  @RequestParam("content") String content) {
//        chatService.sendChatRequest(inviterAccount, receiverAccount);
//        return ResponseEntity.ok().build();
//    }
    @PostMapping("/request")
    public ResponseEntity<Object> sendChatRequest(@RequestBody ChatHistory chatHistory) {
        chatHistory.setTimeStamp(LocalDateTime.now());
        chatService.sendChatRequest(chatHistory);
        //获取当前时间给timeStamp

        chatService.sendChatHistory(chatHistory);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/queryRequest")
    public int queryChatRequest(@RequestBody ChatHistory chatHistory) {
        return chatService.queryChatRequest(chatHistory);
    }


    @PostMapping("/request/{chatId}/accept")
    public ResponseEntity<Object> acceptChatRequest(@PathVariable Long chatId) {
        chatService.acceptChatRequest(chatId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request/{chatId}/refuse")
    public ResponseEntity<Object> refuseChatRequest(@PathVariable Long chatId) {
        chatService.refuseChatRequest(chatId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{chatId}/terminate")
    public ResponseEntity<Object> terminateChat(@PathVariable Long chatId, Principal principal) throws NotFoundException {
        String account = principal.getName();
        chatService.terminateChat(chatId, account);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{chatId}/message")
    public ResponseEntity<Object> sendMessage(@PathVariable Long chatId, @RequestBody ChatMessage chatMessage) {
        // 根据chatId判断是否为合法的聊天，可以查询chat_request表来验证聊天是否存在并且状态是否允许发送消息
        ChatRequest chatRequest = chatMapper.selectChatRequestById(chatId);
        if (chatRequest == null || chatRequest.getStatus() != 1) {
//            throw new BadRequestException("Invalid or inactive chat");
            return ResponseEntity.badRequest().build();
        }

        // 根据chatId和当前发送消息的用户（使用Principal获取）向chat_history表中插入聊天记录
        ChatHistory chatHistory = ChatHistory.builder()
                .chatHistoryId(null) // chat_history_id为自增字段，不用设置值
                .sendingAccount(chatMessage.getSender())
                .receiverAccount(chatMessage.getReceiver())
                .timeStamp(LocalDateTime.now())
                .content(chatMessage.getContent())
                .build();

        chatMapper.insertChatHistory(chatHistory);

        // 使用messagingTemplate发送聊天消息到特定的WebSocket订阅路径，如"/topic/chat/{chatId}"
        String topic = "/topic/chat/" + chatId;
        messagingTemplate.convertAndSend(topic, chatMessage);

        return ResponseEntity.ok().build();
    }
}