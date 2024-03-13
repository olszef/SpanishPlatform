package com.home.spanishplatform.POJO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordChangeForm {
	
	private String currentEncryptedPassword;
	
	@NotBlank(message = "Password cannot be empty")
	private String oldPassword;
	
	@Size(min = 4, max = 15, message = "Password must be between 4 and 15 characters")
	private String newPassword;
	
	private String confirmPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getCurrentEncryptedPassword() {
		return currentEncryptedPassword;
	}
	public void setCurrentEncryptedPassword(String currentPassword) {
		this.currentEncryptedPassword = currentPassword;
	}
}
