package com.example.movie.service;

import com.example.movie.exception.MemberException;

public interface MemberService {
	void joinMovieGroup(String Account,Integer MovieId) throws MemberException;
	void quitMovieGroup(String Account,Integer MovieId) throws MemberException;
}
