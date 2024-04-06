package com.photogram.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.photogram.domain.subscribe.SubscribeRepository;
import com.photogram.handler.ex.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SubscribeService {
	
	private final SubscribeRepository subscribeRepository; 
	
	@Transactional
	public void 구독하기(int fromUserId, int toUserId) {
		
		// 네이티브 쿼리로 짜는 것이 더 간소화함으로 이렇게 짜보겠다. 
		// subscribeRepository.save(null);
		try {
			subscribeRepository.mSubscribe(fromUserId, toUserId);
		} catch (Exception e) {
			throw new CustomApiException("이미 구독을 하였습니다."); 
		}
		
	}

	@Transactional
	public void 구독취소하기(int fromUserId, int toUserId) {
		subscribeRepository.mUnSubscribe(fromUserId, toUserId);
	}
}
