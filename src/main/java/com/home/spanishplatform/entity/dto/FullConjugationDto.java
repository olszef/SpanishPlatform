package com.home.spanishplatform.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullConjugationDto {

	private String modeText;
	private String tenseText;
	private String single1;
	private String single2;
	private String single3;
	private String plural1;
	private String plural2;
	private String plural3;
}
