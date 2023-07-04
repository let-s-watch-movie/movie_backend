package com.example.movie.config;

import com.example.movie.controller.WebSocketController;
import com.example.movie.service.impl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
	@Bean
	ServerEndpointExporter serverEndpointExporter(){
		return new ServerEndpointExporter();
	}
	@Autowired
	private ChatServiceImpl chatService;
	
	@Bean
	public WebSocketController webSocketController() {
		WebSocketController webSocketController = new WebSocketController();
		WebSocketController.setChatService(chatService);
		return webSocketController;
	}
}
