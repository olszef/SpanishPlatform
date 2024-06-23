package com.home.spanishplatform.POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConjugationTrainingForm {
	private String conjugationTrainingOption;
	private String spanishVerb;
	private String searchVerb;
	private int searchLanguageId;
	private int searchModeId;
	private int searchTenseId;

	public ConjugationTrainingForm() {
		this.conjugationTrainingOption = "random";
	}
	
}
