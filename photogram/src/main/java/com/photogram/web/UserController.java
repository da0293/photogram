package com.photogram.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.photogram.config.auth.PrincipalDetails;

@Controller
public class UserController {
	
	@GetMapping("/user/{id}")
	public String profile(@PathVariable int id) {
		return "user/profile";
	}
	

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, 
			// 해당 세션 접근 
			@AuthenticationPrincipal PrincipalDetails principalDetails ) {
		// 추천
		System.out.println("세션정보: " + principalDetails.getUser());
		// 극혐 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
		System.out.println("직접 찾은 세션정보: "+mPrincipalDetails.getUser());
		
		return "user/update";
	}
}
