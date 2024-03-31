package com.photogram.domain.user;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(
	uniqueConstraints = {
		@UniqueConstraint(
			name="subscribe_uk",
			columnNames = {"fromUserId", "toUserId"}	
		)
	}			
)
public class Subscribe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@JoinColumn(name = "fromUserId")// 컬럼명을 만듬 
	@ManyToOne
	private User fromUser;
	
	@JoinColumn(name = "toUserId")
	@ManyToOne
	private User toUser; 
	
	private LocalDateTime createDate;
	
	@PrePersist //DB에 INSERT되기직전에 실행
	public void createDate() {
		this.createDate=LocalDateTime.now(); 
	}
	
}
