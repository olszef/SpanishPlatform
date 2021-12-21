package com.home.spanishplatform.entity.keys;

import java.io.Serializable;

public class VerbMappingId implements Serializable {
	private int wordId;
	private int verbId;
	
	public VerbMappingId() {
		super();
	}
	
	public VerbMappingId(int wordId, int verbId) {
		super();
		this.wordId = wordId;
		this.verbId = verbId;
	}
}
