package com.home.spanishplatform.entity.keys;

import java.io.Serializable;

public class ConjugationId implements Serializable {
	private int verbId;
	private int modeId;	
	private int tenseId;
	
	public ConjugationId() {
		super();
	}

	public ConjugationId(int verbId, int modeId, int tenseId) {
		super();
		this.verbId = verbId;
		this.modeId = modeId;
		this.tenseId = tenseId;
	}
}
