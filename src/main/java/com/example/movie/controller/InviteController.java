package com.example.movie.controller;


import com.example.movie.entity.ChatHistory;
import com.example.movie.entity.ChatRequest;
import com.example.movie.entity.InviteRequest;

import com.example.movie.mapper.InviteMapper;

import com.example.movie.service.InviteService;
import com.example.movie.util.Response;
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

@RestController
@RequestMapping("/invite")
public class InviteController {

    private final InviteService inviteService;
    private final InviteMapper inviteMapper;
    private Response response;
    @Autowired
    public InviteController(InviteService inviteService, InviteMapper inviteMapper) {
        this.inviteService = inviteService;
        this.inviteMapper = inviteMapper;
    }

    @PostMapping("/request")
    public Response sendInviteRequest(@RequestBody InviteRequest inviteRequest) {
        inviteService.sendInviteRequest(inviteRequest);
//        return ResponseEntity.ok().build();
        return response.Success("sendInviteRequest successfully");
    }
    @PostMapping("/queryRequest")
    public Response queryChatRequest(@RequestBody InviteRequest inviteRequest) {
        int status = inviteService.queryInviteRequest(inviteRequest);
        return response.Success(status);

    }
    @PostMapping("/queryRequestByReceiver")
    public Response queryChatRequestByReceiver(@RequestBody InviteRequest inviteRequest) {
//        return inviteService.queryInviteRequestByReceiver(inviteRequest);
        List<InviteRequest>inviteRequestList = inviteService.queryInviteRequestByReceiver(inviteRequest);
        return response.Success(inviteRequestList);

    }
    @PostMapping("/accept")
    public Response acceptChatRequest(@RequestBody InviteRequest inviteRequest) {
        inviteService.acceptChatRequest(inviteRequest);
//        return ResponseEntity.ok().build();
        return response.Success("accept successfully");
    }

    @PostMapping("/refuse")
    public Response refuseChatRequest(@RequestBody InviteRequest inviteRequest) {
        inviteService.refuseChatRequest(inviteRequest);
//        return ResponseEntity.ok().build();
        return response.Success("refuse successfully");
    }




}
