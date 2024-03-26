package com.photogram.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.photogram.domain.user.User;
import com.photogram.service.AuthService;
import com.photogram.web.dto.auth.SignupDto;

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
	
	// 회원가입 성공시 
	/* tooEntity(): SignupDto 객체의 필드 값을 읽어와서, 해당 정보를 사용하여 User 엔티티 객체를 생성
	 * DTO에는 비즈니스 로직이나 영속성과 관련된 정보가 없으며, 단순 데이터 전송을 위한 용도
	 * 엔티티를 생성하기 위해서는 DTO에서 필요한 데이터를 추출하여 엔티티 객체의 필드에 설정해주는 작업이 필요
	 */
	@PostMapping("/auth/signup")
	public String signup(SignupDto signupDto) { //key=value(x-www-form-urlencoded)로 데이터받음
		log.info(signupDto.toString());
		User user = signupDto.toEntity(); 
		log.info(user.toString()); 
		User userEntity = authService.회원가입(user);
		System.out.println(userEntity);
		return "auth/signin"; 
	}
}
