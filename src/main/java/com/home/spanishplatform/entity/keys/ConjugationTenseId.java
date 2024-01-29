package com.home.spanishplatform.entity.keys;

import java.io.Serializable;

public class ConjugationTenseId implements Serializable {
	private int modeId;	
	private int tenseId;
	
	public ConjugationTenseId() {
		super();
	}

	public ConjugationTenseId(int modeId, int tenseId) {
		super();
		this.modeId = modeId;
		this.tenseId = tenseId;
	}
}
