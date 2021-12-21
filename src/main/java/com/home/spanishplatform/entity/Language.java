package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="language")
public class Language {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="language_id")
	private int languageId;
	
	@Column(name="language_code")
	private String languageCode;
	
	@Column(name="language_text")
	private String languageText;
	
	public Language() {}

	public Language(int languageId, String languageCode, String languageText) {
		super();
		this.languageId = languageId;
		this.languageCode = languageCode;
		this.languageText = languageText;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getLanguageText() {
		return languageText;
	}

	public void setLanguageText(String languageText) {
		this.languageText = languageText;
	}

	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", languageCode=" + languageCode + ", languageText="
				+ languageText + "]";
	}
	
}
