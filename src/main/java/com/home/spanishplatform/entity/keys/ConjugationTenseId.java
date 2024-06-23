package com.home.spanishplatform.entity.keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ConjugationTenseId implements Serializable {
	private int modeId;	
	private int tenseId;
}
