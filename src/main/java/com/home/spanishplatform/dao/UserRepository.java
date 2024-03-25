package com.home.spanishplatform.dao;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.home.spanishplatform.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	
	@Modifying
    @Query("UPDATE User c SET c.lastLogin = :lastLogin WHERE c.userId = :userId")
	@Transactional
    void updateLastLoginByUserId(@Param("userId") int userId, @Param("lastLogin") LocalDateTime lastLogin);
	
	@Modifying
    @Query("UPDATE User c SET c.password = :newPassword WHERE c.userId = :userId")
	@Transactional
    void changeUserPassword(@Param("userId") int userId, @Param("newPassword") String newPassword);
	
	@Modifying
    @Query("UPDATE User c SET c.name = :newName, c.username = :newUsername WHERE c.userId = :userId")
	@Transactional
    void changeUserPassword(@Param("userId") int userId, @Param("newName") String newName, @Param("newUsername") String newUsername);
	
	Page<User> findAll(Pageable pageable);
}
