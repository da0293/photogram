package com.photogram.domain.user;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//JPA -Java Persistence API(자바로 데이터를 영구적으로 저장(DB)할 수 있는 API제공)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //DB에 테이블 생성 
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //번호 증가 전략이 데이터베이스를 따라간다. 
	private int id; 
	
	@Column(length=20, unique=true) 
	private String username; 
	private String password;
	private String name; 
	private String website; //웹사이트
	private String bio; //자기소개
	private String email; 
	private String phone; 
	private String gender; 
	private String profileImageURl; //유저사진
	private String role;//권한 
	private LocalDateTime createDate; 
	
	@PrePersist //DB에 INSERT되기직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now(); 
	}
	
}
