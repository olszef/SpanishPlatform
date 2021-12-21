package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="conjugation_tense")
public class ConjugationTense {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tense_id")
	private int tenseId;

	@Column(name="tense_text")
	private String tenseText;
	
	public ConjugationTense() {}

	public ConjugationTense(int tenseId, String tenseText) {
		super();
		this.tenseId = tenseId;
		this.tenseText = tenseText;
	}

	public int getTenseId() {
		return tenseId;
	}

	public void setTenseId(int tenseId) {
		this.tenseId = tenseId;
	}

	public String getTenseText() {
		return tenseText;
	}

	public void setTenseText(String tenseText) {
		this.tenseText = tenseText;
	}

	@Override
	public String toString() {
		return "ConjugationTense [tenseId=" + tenseId + ", tenseText=" + tenseText + "]";
	}

}
