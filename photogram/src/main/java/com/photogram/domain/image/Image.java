package com.photogram.domain.image;


import java.time.LocalDateTime;

import com.photogram.domain.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * 이미지는 한명의 유저가 업로드 가능하다. 
 * 한명의 유저는 여러개의 이미지를 만들어낼 수 있다. 
 * 
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity //DB에 테이블 생성 
public class Image {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String caption; // 오늘 나 너무 피곤해!!
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버에 특정폴더에 저장 - DB에 그 저장된 경로를 insert
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user; // 누가 업로드했는가. 
	// 데이터 베이스를 저장할 때 User와같은 오브젝트를 저장할 수 없다. 
	// 그래서 이대로 저장하게되면 DB에 foreign키로 저장하게된다. 
	
	// 이미지 좋아요 정보
	
	
	// 이미지에 댓글 첨부 
	
	private LocalDateTime createDate; 
	
	@PrePersist //DB에 INSERT되기직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now(); 
	}
	
}
