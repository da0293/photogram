package com.photogram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.photogram.domain.user.User;
import com.photogram.domain.user.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository; 
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	@Transactional
	public User 회원수정(int id,User user) {
		// 1. 영속화
		// (1) get(): 무조건 찾았다.걱정마
		// (2) orElseThrow(): 못찾았으니 exception 발동 
		User userEntity = userRepository.findById(id).get();
		
		// 2. 영속화된 오브젝트 수정
		userEntity.setName(user.getName());
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity; 
		// 수정이 끝나면 더티체킹이 일어나서 업데이트 완료
	}
}
