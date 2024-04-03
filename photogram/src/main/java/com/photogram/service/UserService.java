package com.photogram.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.photogram.domain.user.User;
import com.photogram.domain.user.UserRepository;
import com.photogram.handler.ex.CustomException;
import com.photogram.handler.ex.CustomValidationApiException;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository; 
	private final BCryptPasswordEncoder bCryptPasswordEncoder; 
	
	public User 회원프로필(int userId) {
		// 해당 유저가 들고 있는 모든 사진을 가져옴
		// SELECT * FROM image WHERE userId =:userId; -> JPA이용
		// 만약 userId가 없다면 exception발생
		User userEntity = userRepository.findById(userId).orElseThrow(()-> {
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		
		
		return userEntity; 
	}
	
	@Transactional
	public User 회원수정(int id,User user) {
		// 1. 영속화
		// (1) get(): 무조건 찾았다.걱정마
		// (2) orElseThrow(): 못찾았으니 exception 발동 
		User userEntity = userRepository.findById(id)
			    .orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 id입니다."));

		
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
