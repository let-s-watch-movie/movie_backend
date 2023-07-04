package com.example.movie.mapper;

import com.example.movie.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InviteMapper {
    void insertInviteRequest(InviteRequest inviteRequest);

    int queryInviteRequest(InviteRequest inviteRequest);

    void updateInviteRequest(InviteRequest inviteRequest);
}
