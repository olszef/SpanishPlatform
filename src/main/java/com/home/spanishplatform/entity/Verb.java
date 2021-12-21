package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="verb")
public class Verb {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="verb_id")
	private int verbId;

	@Column(name="indicativo")
	private String indicativo;
	
	@Column(name="participio")
	private String participio;
	
	@Column(name="gerundio")
	private String gerundio;
	
	public Verb() {}
	
	public Verb(int verbId) {
		super();
		this.verbId = verbId;
		this.indicativo = "";
		this.participio = "";
		this.gerundio = "";
	}

	public Verb(int verbId, String indicativo, String participio, String gerundio) {
		super();
		this.verbId = verbId;
		this.indicativo = indicativo;
		this.participio = participio;
		this.gerundio = gerundio;
	}

	public int getVerbId() {
		return verbId;
	}

	public void setVerbId(int verbId) {
		this.verbId = verbId;
	}

	public String getIndicativo() {
		return indicativo;
	}

	public void setIndicativo(String indicativo) {
		this.indicativo = indicativo;
	}

	public String getParticipio() {
		return participio;
	}

	public void setParticipio(String participio) {
		this.participio = participio;
	}

	public String getGerundio() {
		return gerundio;
	}

	public void setGerundio(String gerundio) {
		this.gerundio = gerundio;
	}
}
