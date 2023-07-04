package com.example.movie.controller;

import com.example.movie.service.impl.MemberServiceImpl;
import com.example.movie.util.Response;
import com.example.movie.exception.MemberException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class MemberData{
	public String account;
	public Integer movieId;
}

@RestController
public class MemberController {
	@Autowired
	MemberServiceImpl memberService;
	
	@PostMapping("/group")
	public Response joinMovieGroup(@RequestBody MemberData memberData){
		try{
			memberService.joinMovieGroup(memberData.getAccount(),memberData.getMovieId());
		} catch (MemberException e){
			System.out.println(e.getMessage());
			return Response.Error(e.getTip());
		}
		
		return Response.Success(null);
	}
	
	@DeleteMapping("/group")
	public Response quitMovieGroup(@RequestParam String account,Integer movieId){
		try{
			memberService.quitMovieGroup(account,movieId);
		}catch (MemberException e){
			System.out.println(e.getMessage());
			return Response.Error(e.getTip());
		}
		
		return Response.Success(null);
	}

}
