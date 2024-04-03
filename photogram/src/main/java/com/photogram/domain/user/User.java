package com.photogram.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.photogram.domain.image.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@Column(length=20, unique=true) // 데이터베이스 어노테이션
	private String username; 
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name; 
	
	private String website; //웹사이트
	private String bio; //자기소개
	
	@Column(nullable = false)
	private String email; 
	
	private String phone; 
	private String gender; 
	private String profileImageURl; //유저사진
	private String role;//권한
	
	// mappedBy는 "나는 연관관계 주인이 아니다. 그러므로 테이블에 컬럼을 만들면 안된다."
	// User를 Select할 때 해당 User id로 등록된 image들을 다 가져와 
	// Lazy = User을 Select할 때 해당 User id로 등록된 iamge들을 가져오지마 
	// -> 대신 getImages() 함수의 image들이 호출될 때 가져와!
	// Eager = User를 Select할 때 해당 User id로 등록된 image들을 전부 Join해서 가져와!!
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)  
	@JsonIgnoreProperties({"user"}) 
	private List<Image> images; // 양방향 매핑 

	
	private LocalDateTime createDate; 
	
	@PrePersist //DB에 INSERT되기직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now(); 
	}
	
}
