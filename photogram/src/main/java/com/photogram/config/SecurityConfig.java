package com.photogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Spring IoC 컨테이너에 의해 관리됨
public class SecurityConfig {
	
	// 해당 파일로 시큐리티를 활성화
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		// 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨
		http.csrf(c -> c.disable());
		
		/*
		 * .requestMatchers() : 괄호안에 들어오는 URL주소로 시작하게되면
		 * .authenticated() : 인증이 필요하다
		 * .anyRequest().permitAll()) : 그게 아닌 모든 요청은 허용하겠다.
		 */
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
				.authenticated().anyRequest().permitAll());
		
		/*
		 * 인증이 필요한 페이지는 아무나 들어가지 못한다.
		 * 따라서 인증이 필요한 요청이 오면 formLogin을 할 것이다. 
		 * form.loginPage("") : form 로그인 페이지 경로설정
		 * .loginProcessingUrl("") : .requestMatchers()안이 주소들을 요청하면 괄호안 주소("")로 오게한다.
		 * .defaultSuccessUrl("/") : 로그인이 정상처리되면 괄호안 주소로 가게한다. 
		 */
		
		http.formLogin(form -> form.loginPage("/auth/signin").loginProcessingUrl("/auth/signin").defaultSuccessUrl("/"));

		return http.build();
		
	}
	
}
