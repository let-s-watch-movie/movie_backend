package com.example.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InviteRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inviteId;
    private int movieId;
    private String inviterAccount;
    private String receiverAccount;
    private int status;
    private LocalDate inviteTime;
}
