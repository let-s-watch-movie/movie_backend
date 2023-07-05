package com.example.movie.controller;

import com.example.movie.entity.ChatHistory;
import com.example.movie.entity.ChatRequest;
import com.example.movie.mapper.ChatMapper;
import com.example.movie.service.ChatService;
import com.example.movie.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private Response response;
    private final ChatMapper chatMapper;

    @Autowired
    public ChatController(ChatService chatService, ChatMapper chatMapper) {
        this.chatService = chatService;

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
    public Response sendChatRequest(@RequestBody ChatHistory chatHistory) {
//        Integer hasSent = chatService.queryChatRequest(chatHistory).getStatus();
        //获取当前时间给timeStamp
        ChatHistory chatHistory1 = chatService.queryChatRequest(chatHistory);
        if(chatHistory1 == null){
            chatHistory.setTimeStamp(LocalDateTime.now());
            chatService.sendChatRequest(chatHistory);
//            chatService.sendChatHistory(chatHistory);
            return response.Success("send successfully");
        }

//        return ResponseEntity.ok().build();
        return response.Error("send failed");
    }


//    @PostMapping("/queryRequest")
//    public int queryChatRequest(@RequestBody ChatHistory chatHistory) {
//        return chatService.queryChatRequest(chatHistory);
//    }
    @PostMapping("/queryRequestByReceiver")
    public Response queryChatRequestByReceiver(@RequestBody ChatHistory chatHistory) {
//        return chatService.queryChatRequestByReceiver(chatHistory);
        List<ChatRequest>chatRequestList = chatService.queryChatRequestByReceiver(chatHistory);
        return response.Success(chatRequestList);
    }


    //    @PostMapping("/request/{chatId}/accept")
//    public ResponseEntity<Object> acceptChatRequest(@PathVariable Long chatId) {
//        chatService.acceptChatRequest(chatId);
//        return ResponseEntity.ok().build();
//    }
    @PostMapping("/accept")
    public Response acceptChatRequest(@RequestBody ChatRequest chatRequest) {
        chatService.acceptChatRequest(chatRequest);
//        return ResponseEntity.ok().build();
        return response.Success("accept successfully");
    }



    @PostMapping("/refuse")
    public Response refuseChatRequest(@RequestBody ChatRequest chatRequest) {
        chatService.refuseChatRequest(chatRequest);
//        return ResponseEntity.ok().build();
        return response.Success("refuse successfully");
    }


    @PostMapping("/inviterTerminate")
    public Response inviterTerminateChat(@RequestBody ChatRequest chatRequest) {
        chatService.inviterTerminateChat(chatRequest);
//        return ResponseEntity.ok().build();
        return response.Success("inviterTerminate successfully");
    }

    @PostMapping("/receiverTerminate")
    public Response receiverTerminateChat(@RequestBody ChatRequest chatRequest) {
        chatService.receiverTerminateChat(chatRequest);
//        return ResponseEntity.ok().build();
        return response.Success("receiverTerminate successfully");
    }

    //    @PostMapping("/{chatId}/message")
//    public ResponseEntity<Object> sendMessage(@PathVariable Long chatId, @RequestBody ChatMessage chatMessage) {
//        // 根据chatId判断是否为合法的聊天，可以查询chat_request表来验证聊天是否存在并且状态是否允许发送消息
//        ChatRequest chatRequest = chatMapper.selectChatRequestById(chatId);
//        if (chatRequest == null || chatRequest.getStatus() != 1) {
////            throw new BadRequestException("Invalid or inactive chat");
//            return ResponseEntity.badRequest().build();
//        }
//
//        // 根据chatId和当前发送消息的用户（使用Principal获取）向chat_history表中插入聊天记录
//        ChatHistory chatHistory = ChatHistory.builder()
//                .chatHistoryId(null) // chat_history_id为自增字段，不用设置值
//                .sendingAccount(chatMessage.getSender())
//                .receiverAccount(chatMessage.getReceiver())
//                .timeStamp(LocalDateTime.now())
//                .content(chatMessage.getContent())
//                .build();
//
//        chatMapper.insertChatHistory(chatHistory);
//
//        // 使用messagingTemplate发送聊天消息到特定的WebSocket订阅路径，如"/topic/chat/{chatId}"
//        String topic = "/topic/chat/" + chatId;
//        messagingTemplate.convertAndSend(topic, chatMessage);
//
//        return ResponseEntity.ok().build();
//    }
    @PostMapping("/sendMessage")
    public Response sendMessage(@RequestBody ChatHistory chatHistory) {
        Integer status = chatService.queryChatRequest(chatHistory).getStatus();
        if (status == 1) {
            chatHistory.setTimeStamp(LocalDateTime.now());
            chatService.sendChatHistory(chatHistory);
            return new Response().Success("Message sent successfully.");
        }

        return new Response().Error("Invalid or inactive chat.");
    }

    @PostMapping("queryMessage")
    public Response queryMassage(@RequestBody ChatHistory chatHistory) {
        List<ChatHistory> chatHistoryList = chatService.queryChatHistory(chatHistory);
//        ArrayList chatHistoryId = new ArrayList<>();
        for(ChatHistory chat:chatHistoryList){
//            chatHistoryId.add(chat.getChatHistoryId());
            Integer id = chat.getChatHistoryId();
//            chatService.setSent(id);
        }

        return response.Success(chatHistoryList);

    }



}

