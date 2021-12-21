package com.home.spanishplatform.POJO;

public class TranslationSearch {
	private String wordText;
	private int languageIdFrom;
	private int languageIdTo;
	public String getWordText() {
		return wordText;
	}
	public void setWordText(String wordText) {
		this.wordText = wordText;
	}
	public int getLanguageIdFrom() {
		return languageIdFrom;
	}
	public void setLanguageIdFrom(int languageIdFrom) {
		this.languageIdFrom = languageIdFrom;
	}
	public int getLanguageIdTo() {
		return languageIdTo;
	}
	public void setLanguageIdTo(int languageIdTo) {
		this.languageIdTo = languageIdTo;
	}
	public TranslationSearch() {
		languageIdFrom = 1;
		languageIdTo = 2;
	}
	
}
