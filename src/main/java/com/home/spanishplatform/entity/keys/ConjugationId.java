package com.home.spanishplatform.entity.keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ConjugationId implements Serializable {
	private int verbId;
	private int modeId;	
	private int tenseId;
}
