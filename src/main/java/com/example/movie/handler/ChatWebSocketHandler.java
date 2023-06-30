package com.example.movie.handler;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatWebSocketHandler implements WebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();

    public ChatWebSocketHandler(SimpMessagingTemplate simpMessagingTemplate) {

    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }



    @Override
    public boolean supportsPartialMessages() {
        return false;
    }



    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    // 其他WebSocketHandler接口中的方法，根据需要进行实现
}