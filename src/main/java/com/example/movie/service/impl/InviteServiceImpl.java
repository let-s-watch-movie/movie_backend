package com.example.movie.service.impl;

import com.example.movie.entity.InviteRequest;
import com.example.movie.mapper.InviteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.movie.service.InviteService;

import java.time.LocalDate;
import java.util.List;

@Service(value = "inviteService")
public class InviteServiceImpl implements InviteService{
    public InviteServiceImpl(InviteMapper inviteMapper) {
        this.inviteMapper = inviteMapper;
    }

    @Autowired
    private final InviteMapper inviteMapper;
    public void sendInviteRequest(InviteRequest inviteRequest){
        inviteRequest.setInviteTime(LocalDate.now());
        inviteMapper.insertInviteRequest(inviteRequest);

    }
    public int queryInviteRequest(InviteRequest inviteRequest){

        return inviteMapper.queryInviteRequest(inviteRequest);
    }
    public void acceptChatRequest(InviteRequest inviteRequest){
        inviteRequest.setStatus((1));
        inviteMapper.updateInviteRequest(inviteRequest);
    }
    public void refuseChatRequest(InviteRequest inviteRequest){
        inviteRequest.setStatus((2));
        inviteMapper.updateInviteRequest(inviteRequest);
    }
    public List<InviteRequest> queryInviteRequestByReceiver(InviteRequest inviteRequest){

        return inviteMapper.queryInviteRequestByReceiver(inviteRequest);
    }
}
