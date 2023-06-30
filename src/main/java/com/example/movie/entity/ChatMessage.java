package com.example.movie.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ChatMessage {
    private String sender;
    private String content;

    // Getters and setters
}
