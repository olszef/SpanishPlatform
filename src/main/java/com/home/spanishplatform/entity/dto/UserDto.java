package com.home.spanishplatform.entity.dto;

import java.time.format.DateTimeFormatter;

import com.home.spanishplatform.entity.User;

import lombok.Getter;

@Getter
public class UserDto {

	private int userId;
	private String name;
	private String username;
	private String email;
	private String lastLogin;
	private Boolean isAdmin;

    public UserDto(User user) {
    	this.userId = user.getUserId();
        this.name = user.getName();  
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.lastLogin = user.getLastLogin().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.isAdmin = (user.getRoles().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getRoleName())));
    }
}
