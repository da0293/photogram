package com.photogram.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.photogram.domain.user.User;
import com.photogram.service.AuthService;
import com.photogram.web.dto.auth.SignupDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI 
@Controller // 1.IoC에 등록 2.파일을 리턴한는 컨트롤러
public class AuthController {
	
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;
	
//	public AuthController(AuthService authService) {
//		this.authService = authService; 
//	} -> private final AuthService authService와 @RequiredArgsConstructor로 바끔 
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin"; 
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup"; 
	}
	

	@PostMapping("/auth/signup")
	public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { //key=value(x-www-form-urlencoded)로 데이터받음
//		if(signupDto.getUsername().length()>20) {
//			System.out.println("너 길이 초과했어");
//		} 이것을 아래와 같이 바꾼다.
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println("=======================================");
				System.out.println(error.getDefaultMessage());
			}
		}
		log.info(signupDto.toString());
		User user = signupDto.toEntity(); 
		log.info(user.toString()); 
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin"; 
	}
}
