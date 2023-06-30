package com.example.movie.config;

import com.example.movie.handler.ChatWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.messaging.DefaultSimpUserRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatWebSocketHandler(), "/chat").setAllowedOrigins("*");
    }

    @Bean
    public ChatWebSocketHandler chatWebSocketHandler() {
        return new ChatWebSocketHandler(messagingTemplate());
    }

    @Bean
    public SimpMessagingTemplate messagingTemplate() {
        return new SimpMessagingTemplate(clientOutboundChannel());
    }

    @Bean
    public MessageChannel clientOutboundChannel() {
        return new ExecutorSubscribableChannel();
    }

    @Bean
    public DefaultSimpUserRegistry userRegistry() {
        return new DefaultSimpUserRegistry();
    }
}