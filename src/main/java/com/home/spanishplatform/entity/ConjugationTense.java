package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.home.spanishplatform.entity.keys.ConjugationTenseId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@Override
	public String toString() {
		return "ConjugationTense [modeId=" + modeId + ", tenseId=" + tenseId + ", tenseText=" + tenseText + "]";
	}

}
