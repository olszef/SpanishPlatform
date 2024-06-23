package com.home.spanishplatform.POJO;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	
	@NotBlank(message = "Name cannot be empty")
	private String userFormName;
	
	private boolean isNameChangeable = true;
	
	@NotBlank(message = "Username cannot be empty")
	private String userFormUsername;
	
	private boolean isUsernameChangeable = true;
	
	@NotBlank(message = "Email cannot be empty")
	private String userFormEmail;
	
	@NotBlank(message = "Password cannot be empty")
	private String userFormPassword;
	
	private boolean isEmailChangeable = false;
	
	private Boolean userFormIsAdmin = false;
}
