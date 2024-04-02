package com.photogram.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	// 직접 넣으므로 @PrePersist를 지원하지않는다. 
	// :은 매개변수로 받은 변수를 넣게다는 뜻
	@Modifying // INSERT, DELETE, UPDATE를 네이티브쿼리로 작성하렴녀 해당 어노테이션 필요!!
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId,:toUserId, now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId);
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId); 

}
