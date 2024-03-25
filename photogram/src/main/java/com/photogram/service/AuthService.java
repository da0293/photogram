package com.photogram.service;

import org.springframework.stereotype.Service;

import com.photogram.domain.user.User;
import com.photogram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service// 1. IoC 2. 트랜잭션 관리
public class AuthService {
	
	private final UserRepository userRepository; 
	
	public User 회원가입(User user) {
		//회원가입 진행
		/*
		 * 매개변수의 user는 외부에서 통신으로 온 데이터를 User객체를 담음
		 * userEntity는 데이터베이스의 데이터를 User객체에 담음
		 */
		User userEntity = userRepository.save(user); 
		return userEntity; 
	}

}
