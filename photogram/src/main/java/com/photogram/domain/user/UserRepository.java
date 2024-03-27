package com.photogram.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	// JPA Query Methods 사용
	User findByUsername(String username);
	
	
}
