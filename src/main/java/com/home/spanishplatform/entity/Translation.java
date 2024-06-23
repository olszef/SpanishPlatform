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
@Table(name="translation")
public class Translation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="translation_id")
	private int translationId;
	
	@Column(name="word_id_from")
	private int wordIdFrom;
	
	@Column(name="language_id_to")
	private int languageIdTo;
	
	@Column(name="word_id_to")
	private int wordIdTo;
	
	@Column(name="relevance")
	private int relevance;	
}
