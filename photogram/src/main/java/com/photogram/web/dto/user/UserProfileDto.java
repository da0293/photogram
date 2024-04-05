package com.photogram.web.dto.user;

import com.photogram.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private boolean pageOwnerState; // 이 페이지의 주인인지 여부 
	private int imageCount; 
	private boolean subscribeState; // 구독 상태
	private int subscribeCount; // 내가 구독한 사람 수
	private User user; 
}
