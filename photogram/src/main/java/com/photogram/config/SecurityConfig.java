package com.photogram.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //Spring IoC 컨테이너에 의해 관리됨
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable());
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**")
				.authenticated().anyRequest().permitAll());
		
		http.formLogin(form -> form.loginPage("/auth/signin").loginProcessingUrl("/auth/signin").defaultSuccessUrl("/"));

		return http.build();
		
	}
	
}
