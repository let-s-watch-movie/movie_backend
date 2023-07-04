package com.example.movie.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Component
public class ChatHistory {
	String sendingAccount;
	String receiverAccount;
	Date timeStamp;
	String content;
	Integer chatHistoryId;
	Integer status;
}
