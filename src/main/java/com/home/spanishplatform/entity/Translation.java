package com.home.spanishplatform.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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

	public Translation() {}

	public Translation(int translationId, int wordIdFrom, int languageIdTo, int wordIdTo, int relevance) {
		super();
		this.translationId = translationId;
		this.wordIdFrom = wordIdFrom;
		this.languageIdTo = languageIdTo;
		this.wordIdTo = wordIdTo;
		this.relevance = relevance;
	}

	public int getTranslationId() {
		return translationId;
	}

	public void setTranslationId(int translationId) {
		this.translationId = translationId;
	}

	public int getWordIdFrom() {
		return wordIdFrom;
	}

	public void setWordIdFrom(int wordIdFrom) {
		this.wordIdFrom = wordIdFrom;
	}

	public int getLanguageIdTo() {
		return languageIdTo;
	}

	public void setLanguageIdTo(int languageIdTo) {
		this.languageIdTo = languageIdTo;
	}

	public int getWordIdTo() {
		return wordIdTo;
	}

	public void setWordIdTo(int wordIdTo) {
		this.wordIdTo = wordIdTo;
	}

	public int getRelevance() {
		return relevance;
	}

	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}	
}
