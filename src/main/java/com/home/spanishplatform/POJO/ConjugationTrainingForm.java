package com.home.spanishplatform.POJO;

public class ConjugationTrainingForm {
	private String conjugationTrainingOption;
	private String spanishVerb;
	private String searchVerb;
	private int searchLanguageId;
	private int searchModeId;
	private int searchTenseId;

	public String getConjugationTrainingOption() {
		return conjugationTrainingOption;
	}

	public void setConjugationTrainingOption(String conjugationTrainingOption) {
		this.conjugationTrainingOption = conjugationTrainingOption;
	}

	public String getSpanishVerb() {
		return spanishVerb;
	}
	
	public void setSpanishVerb(String spanishVerb) {
		this.spanishVerb = spanishVerb;
	}
	
	public String getSearchVerb() {
		return searchVerb;
	}
	
	public void setSearchVerb(String searchVerb) {
		this.searchVerb = searchVerb;
	}
	
	public int getSearchLanguageId() {
		return searchLanguageId;
	}
	
	public void setSearchLanguageId(int searchLanguageId) {
		this.searchLanguageId = searchLanguageId;
	}

	public int getSearchModeId() {
		return searchModeId;
	}

	public void setSearchModeId(int searchModeId) {
		this.searchModeId = searchModeId;
	}

	public int getSearchTenseId() {
		return searchTenseId;
	}

	public void setSearchTenseId(int searchTenseId) {
		this.searchTenseId = searchTenseId;
	}

	public ConjugationTrainingForm() {
		this.conjugationTrainingOption = "random";
	}
	
}
