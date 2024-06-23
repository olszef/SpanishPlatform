package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
	
	public Verb(int verbId) {
		super();
		this.verbId = verbId;
		this.indicativo = "";
		this.participio = "";
		this.gerundio = "";
	}
}
