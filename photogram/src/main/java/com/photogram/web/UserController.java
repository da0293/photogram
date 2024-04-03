package com.photogram.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.photogram.config.auth.PrincipalDetails;
import com.photogram.service.UserService;
import com.photogram.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService; 
	
	
	@GetMapping("/user/{pageUserId}")
	public String profile(@PathVariable int pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserProfileDto dto = userService.회원프로필(pageUserId, principalDetails.getUser().getId()); // 해당 아이디가 없다면 exception발생 
		model.addAttribute("dto", dto);
		return "user/profile";
	}
	

	@GetMapping("/user/{id}/update")
	public String update(@PathVariable int id, 
			// 해당 세션 접근 
			@AuthenticationPrincipal PrincipalDetails principalDetails, 
			Model model) {
		// 추천
		// System.out.println("세션정보: " + principalDetails.getUser());
		// 극혐 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
		// System.out.println("직접 찾은 세션정보: "+mPrincipalDetails.getUser());
		model.addAttribute("principal", principalDetails.getUser());
		return "user/update";
	}
}
