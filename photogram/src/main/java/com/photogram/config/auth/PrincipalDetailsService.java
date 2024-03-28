package com.photogram.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.photogram.domain.user.User;
import com.photogram.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository; 
	
	// 1. 패스워드는 알아서 체킹하므로 신경쓸 필요 없다.
	// 2. 리턴이 잘되면 자동으로 UserDetails타입을 세션이 만든다. 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userEntity =userRepository.findByUsername(username);
		if(userEntity==null) {
			// 사용자 정보가 없는 경우 예외 처리
			throw new UsernameNotFoundException(username + " not found");
		} else {
			// 사용자 정보가 있는 경우 PrincipalDetails 반환
			// return userEntity;
			return new PrincipalDetails(userEntity);  
		}
	}
	
}
