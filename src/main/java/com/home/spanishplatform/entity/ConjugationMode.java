package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="conjugation_mode")
public class ConjugationMode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mode_id")
	private int modeId;

	@Column(name="mode_text")
	private String modeText;
	
	public ConjugationMode() {}

	public ConjugationMode(int modeId, String modeText) {
		super();
		this.modeId = modeId;
		this.modeText = modeText;
	}

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public String getModeText() {
		return modeText;
	}

	public void setModeText(String modeText) {
		this.modeText = modeText;
	}

	@Override
	public String toString() {
		return "ConjugationMode [modeId=" + modeId + ", modeText=" + modeText + "]";
	}

}
