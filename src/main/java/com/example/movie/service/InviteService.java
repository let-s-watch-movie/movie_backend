package com.example.movie.service;

import com.example.movie.entity.InviteRequest;

import java.util.List;

public interface InviteService {
    void sendInviteRequest(InviteRequest inviteRequest);

    int queryInviteRequest(InviteRequest inviteRequest);

    void acceptChatRequest(InviteRequest inviteRequest);

    void refuseChatRequest(InviteRequest inviteRequest);

    List<InviteRequest> queryInviteRequestByReceiver(InviteRequest inviteRequest);
}
