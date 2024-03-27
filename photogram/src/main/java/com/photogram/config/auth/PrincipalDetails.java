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
		Collection<GrantedAuthority> collector = new ArrayList<>(); 
		collector.add(()-> {return user.getRole();});
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	
	@Override
	public boolean isAccountNonExpired() {
		return true; 
	}

	// 해당 계정이 잠겼는가
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true; 
	}

}
