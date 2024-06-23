package com.home.spanishplatform.POJO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeForm {
	
	private String currentEncryptedPassword;
	
	@NotBlank(message = "Password cannot be empty")
	private String oldPassword;
	
	@Size(min = 4, max = 15, message = "Password must be between 4 and 15 characters")
	private String newPassword;
	
	private String confirmPassword;
}
