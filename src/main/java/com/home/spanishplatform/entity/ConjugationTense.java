package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.home.spanishplatform.entity.keys.ConjugationTenseId;

@Entity
@IdClass(ConjugationTenseId.class)
@Table(name="conjugation_tense")
public class ConjugationTense {
	
	@Id
	@Column(name="tense_id")
	private int tenseId;
	
	@Id
	@Column(name="mode_id")
	private int modeId;	

	@Column(name="tense_text")
	private String tenseText;
	
	public ConjugationTense() {}

	public ConjugationTense(int modeId, int tenseId, String tenseText) {
		super();
		this.modeId = modeId;
		this.tenseId = tenseId;
		this.tenseText = tenseText;
	}

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
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
		return "ConjugationTense [modeId=" + modeId + ", tenseId=" + tenseId + ", tenseText=" + tenseText + "]";
	}

}
