package com.home.spanishplatform.POJO;

import javax.validation.constraints.NotBlank;

public class UserForm {
	
	@NotBlank(message = "Name cannot be empty")
	private String userFormName;
	
	private boolean isNameChangeable = true;
	
	@NotBlank(message = "Username cannot be empty")
	private String userFormUsername;
	
	private boolean isUsernameChangeable = true;
	
	@NotBlank(message = "Email cannot be empty")
	private String userFormEmail;
	
	private boolean isEmailChangeable = false;
	
	private Boolean userFormIsAdmin = false;

	public String getUserFormName() {
		return userFormName;
	}

	public void setUserFormName(String userFormName) {
		this.userFormName = userFormName;
	}

	public String getUserFormUsername() {
		return userFormUsername;
	}

	public void setUserFormUsername(String userFormUsername) {
		this.userFormUsername = userFormUsername;
	}

	public String getUserFormEmail() {
		return userFormEmail;
	}

	public void setUserFormEmail(String userFormEmail) {
		this.userFormEmail = userFormEmail;
	}

	public boolean isNameChangeable() {
		return isNameChangeable;
	}

	public void setNameChangeable(boolean isNameChangeable) {
		this.isNameChangeable = isNameChangeable;
	}

	public boolean isUsernameChangeable() {
		return isUsernameChangeable;
	}

	public void setUsernameChangeable(boolean isUsernameChangeable) {
		this.isUsernameChangeable = isUsernameChangeable;
	}

	public boolean isEmailChangeable() {
		return isEmailChangeable;
	}

	public void setEmailChangeable(boolean isEmailChangeable) {
		this.isEmailChangeable = isEmailChangeable;
	}

	public Boolean getUserFormIsAdmin() {
		return userFormIsAdmin;
	}

	public void setUserFormIsAdmin(Boolean userFormIsAdmin) {
		this.userFormIsAdmin = userFormIsAdmin;
	}
}
