package com.home.spanishplatform.POJO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
