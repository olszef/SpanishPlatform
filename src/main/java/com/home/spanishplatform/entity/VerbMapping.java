package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.home.spanishplatform.entity.keys.VerbMappingId;

@Entity
@IdClass(VerbMappingId.class)
@Table(name="verb_mapping")
public class VerbMapping {
	
	@Id
	@Column(name="word_id")
	private int wordId;
	
	@Id
	@Column(name="verb_id")
	private int verbId;

	public VerbMapping() {
	}

	public VerbMapping(int wordId, int verbId) {
		super();
		this.wordId = wordId;
		this.verbId = verbId;
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public int getVerbId() {
		return verbId;
	}

	public void setVerbId(int verbId) {
		this.verbId = verbId;
	}
}
