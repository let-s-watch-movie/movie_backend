package com.example.movie.service;

import com.example.movie.entity.InviteRequest;

public interface InviteService {
    void sendInviteRequest(InviteRequest inviteRequest);

    int queryInviteRequest(InviteRequest inviteRequest);

    void acceptChatRequest(InviteRequest inviteRequest);

    void refuseChatRequest(InviteRequest inviteRequest);
}
