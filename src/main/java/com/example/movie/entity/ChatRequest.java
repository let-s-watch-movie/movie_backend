package com.example.movie.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class ChatRequest {
	Integer chatId;
	String inviterAccount;
	String receiverAccount;
	Integer status;
	Date inviteTime;
}
