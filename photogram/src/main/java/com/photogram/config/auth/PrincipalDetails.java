package com.photogram.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.photogram.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails {

	private static final long serialVersionUID = 1L; 
	
	private User user; 
	
	public PrincipalDetails(User user) {
		this.user = user; 
	}
	
	// User user가 들고있는 role(권한)을 
	// Collection타입인 이유: 권한이 1개가 아니라 여러개일 수 있기 때문 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한 정보 반환
		Collection<GrantedAuthority> collector = new ArrayList<>(); 
		collector.add(()-> {return user.getRole();});
		return collector;
	}

	// 사용자의 암호 반환
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// 사용자의 이름 반환
	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 사용자 계정이 만료되지 않았는지 여부 반환
	@Override
	public boolean isAccountNonExpired() {
		return true; 
	}

	// 사용자 계정이 잠겼는지 여부 반환
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 사용자의 인증 정보가 만료되지 않았는지 여부 반환 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 사용자 계정이 활성화되었는지 여부 반환
	@Override
	public boolean isEnabled() {
		return true; 
	}

}
