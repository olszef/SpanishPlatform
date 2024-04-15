package com.home.spanishplatform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.home.spanishplatform.entity.dto.UserDto;
import com.home.spanishplatform.security.MyUserPrincipal;

public interface UserService {

	public boolean checkIfOldPasswordValid(final String formOldPassword, final String dbEncodedOldPassword);
	public void changeUserPassword(final int userId, final String newPassword);
	public void updateUserData(final MyUserPrincipal myUserPrincipal, final String newName, final String newUsername);
	public void createNewUser(final String newName, final String newUsername, final String newEmail, final boolean isAdmin);
	public Page<UserDto> findAllUsers(Pageable pageable);
	public void removeUser(int userId);

}
