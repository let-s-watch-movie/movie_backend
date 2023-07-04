package com.example.movie.service.impl;

import com.example.movie.entity.Member;
import com.example.movie.mapper.MemberMapper;
import com.example.movie.service.MemberService;
import com.example.movie.exception.MemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	MemberMapper memberMapper;
	
	@Override
	public void joinMovieGroup(String Account, Integer MovieId) throws MemberException {
		try{
			memberMapper.joinMovieGroup(new Member(MovieId,Account));
		}catch (Exception e){
			throw new MemberException(e.getMessage(),"加入群组失败");
		}
	}
	
	@Override
	public void quitMovieGroup(String Account, Integer MovieId) throws MemberException {
		try{
			memberMapper.quitMovieGroup(new Member(MovieId,Account));
		}catch (Exception e){
			throw new MemberException(e.getMessage(),"退出群组失败");
		}
	}
}
