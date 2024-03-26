package com.photogram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.photogram.domain.user.User;
import com.photogram.domain.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service// 1. IoC 2. 트랜잭션 관리
public class AuthService {
	
	private final UserRepository userRepository; 
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	
	//@Transactional():이 함수가 종료될때까지 트랜잭션관리를 해준다.
	// Write할때 쓴다.(Insert, Update, Delete.
	@Transactional()
	public User 회원가입(User user) {

		String rawPassword = user.getPassword(); 
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);//암호화된 패스워드
		user.setPassword(encPassword);
		user.setRole("ROLE_USER"); // 기본 권한
		User userEntity = userRepository.save(user); 
		return userEntity; 
	}
}
