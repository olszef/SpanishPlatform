package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.home.spanishplatform.entity.keys.ConjugationId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	public Conjugation(int verbId, int modeId, int tenseId) {
		super();
		this.verbId = verbId;
		this.modeId = modeId;
		this.tenseId = tenseId;
	}
}
