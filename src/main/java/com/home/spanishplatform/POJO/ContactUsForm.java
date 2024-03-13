package com.home.spanishplatform.POJO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ContactUsForm {
	
	@NotBlank(message = "Name cannot be empty")
	private String contactFullName;
	
	@NotBlank(message = "Subject cannot be empty")
	private String contactSubject;
	
	@NotBlank(message = "Incorrect language picked")
	private String contactLanguage;
	
	@NotNull
	private Boolean contactEmailCopy;
	
	@NotBlank(message = "Message cannot be empty")
	private String contactMessageText;

	public String getContactFullName() {
		return contactFullName;
	}

	public void setContactFullName(String contactFullName) {
		this.contactFullName = contactFullName;
	}

	public String getContactSubject() {
		return contactSubject;
	}

	public void setContactSubject(String contactSubject) {
		this.contactSubject = contactSubject;
	}

	public String getContactLanguage() {
		return contactLanguage;
	}

	public void setContactLanguage(String contactLanguage) {
		this.contactLanguage = contactLanguage;
	}

	public Boolean getContactEmailCopy() {
		return contactEmailCopy;
	}

	public void setContactEmailCopy(Boolean contactEmailCopy) {
		this.contactEmailCopy = contactEmailCopy;
	}

	public String getContactMessageText() {
		return contactMessageText;
	}

	public void setContactMessageText(String contactMessageText) {
		this.contactMessageText = contactMessageText;
	}
}
