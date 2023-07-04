package com.example.movie.controller;


import com.example.movie.entity.ChatHistory;
import com.example.movie.entity.ChatRequest;
import com.example.movie.entity.InviteRequest;

import com.example.movie.mapper.InviteMapper;

import com.example.movie.service.InviteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/invite")
public class InviteController {
    @Autowired
    private final InviteService inviteService;
    private final InviteMapper inviteMapper;


    @PostMapping("/request")
    public ResponseEntity<Object> sendInviteRequest(@RequestBody InviteRequest inviteRequest) {
        inviteService.sendInviteRequest(inviteRequest);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/queryRequest")
    public int queryChatRequest(@RequestBody InviteRequest inviteRequest) {
        return inviteService.queryInviteRequest(inviteRequest);

    }
    @PostMapping("/accept")
    public ResponseEntity<Object> acceptChatRequest(@RequestBody InviteRequest inviteRequest) {
        inviteService.acceptChatRequest(inviteRequest);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/refuse")
    public ResponseEntity<Object> refuseChatRequest(@RequestBody InviteRequest inviteRequest) {
        inviteService.refuseChatRequest(inviteRequest);
        return ResponseEntity.ok().build();
    }




}
