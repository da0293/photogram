package com.photogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Spring IoC 컨테이너에 의해 관리됨
public class SecurityConfig {
	
	// BCryptPasswordEncoder 빈 등록
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	// SecurityFilterChain 빈 설정
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// CSRF 보안 설정 비활성화
		http.csrf(c -> c.disable());
		// 권한 설정
		http.authorizeHttpRequests(authorize -> authorize
				// 인증이 필요한 페이지 설정
				.requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
				.authenticated().anyRequest().permitAll());
		// 로그인 설정
		http.formLogin(form -> form
				.loginPage("/auth/signin") // GET: 로그인 페이지 경로 설정
				.loginProcessingUrl("/auth/signin") // POST: 로그인 성공 후 이동할 URL 설정
				.defaultSuccessUrl("/")); // 로그인 성공 후 이동할 URL 설정

		return http.build();
		
	}
	
}
