package com.home.spanishplatform.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TranslationSearch {
	private String wordText;
	private int languageIdFrom;
	private int languageIdTo;

	public TranslationSearch() {
		languageIdFrom = 1;
		languageIdTo = 2;
	}
	
}
