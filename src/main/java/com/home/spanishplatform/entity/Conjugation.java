package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.home.spanishplatform.entity.keys.ConjugationId;

@Entity
@IdClass(ConjugationId.class)
@Table(name="conjugation")
public class Conjugation {
	
	@Id
	@Column(name="verb_id")
	private int verbId;
	
	@Id
	@Column(name="mode_id")
	private int modeId;	
	
	@Id
	@Column(name="tense_id")
	private int tenseId;
	
	@Column(name="single1")
	private String single1;
	
	@Column(name="single2")
	private String single2;
	
	@Column(name="single3")
	private String single3;
	
	@Column(name="plural1")
	private String plural1;
	
	@Column(name="plural2")
	private String plural2;
	
	@Column(name="plural3")
	private String plural3;
	
	public Conjugation() {}

	public Conjugation(int verbId, int modeId, int tenseId) {
		super();
		this.verbId = verbId;
		this.modeId = modeId;
		this.tenseId = tenseId;
	}

	public Conjugation(int verbId, int modeId, int tenseId, String single1, String single2,
			String single3, String plural1, String plural2, String plural3) {
		super();
		this.verbId = verbId;
		this.modeId = modeId;
		this.tenseId = tenseId;
		this.single1 = single1;
		this.single2 = single2;
		this.single3 = single3;
		this.plural1 = plural1;
		this.plural2 = plural2;
		this.plural3 = plural3;
	}

	public int getVerbId() {
		return verbId;
	}

	public void setVerbId(int verbId) {
		this.verbId = verbId;
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

	public String getSingle1() {
		return single1;
	}

	public void setSingle1(String single1) {
		this.single1 = single1;
	}

	public String getSingle2() {
		return single2;
	}

	public void setSingle2(String single2) {
		this.single2 = single2;
	}

	public String getSingle3() {
		return single3;
	}

	public void setSingle3(String single3) {
		this.single3 = single3;
	}

	public String getPlural1() {
		return plural1;
	}

	public void setPlural1(String plural1) {
		this.plural1 = plural1;
	}

	public String getPlural2() {
		return plural2;
	}

	public void setPlural2(String plural2) {
		this.plural2 = plural2;
	}

	public String getPlural3() {
		return plural3;
	}

	public void setPlural3(String plural3) {
		this.plural3 = plural3;
	}

	@Override
	public String toString() {
		return "Conjugation [verbId=" + verbId + ", modeId=" + modeId
				+ ", tenseId=" + tenseId + ", single1=" + single1 + ", single2=" + single2 + ", single3=" + single3
				+ ", plural1=" + plural1 + ", plural2=" + plural2 + ", plural3=" + plural3 + "]";
	}

}
