package com.home.spanishplatform.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.home.spanishplatform.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRoleName(String roleName);
}
