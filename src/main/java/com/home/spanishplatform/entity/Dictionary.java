package com.home.spanishplatform.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dictionary")
public class Dictionary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="word_id")
	private int wordId;
	
	@Column(name="language_id")
	private int languageId;
	
	@Column(name="word_text")
	private String wordText;
	
	@Column(name="speech_part_id")
	private int speechPartId;
	
	public Dictionary() {}

	public Dictionary(int wordId, int languageId, String wordText, int speechPartId) {
		super();
		this.wordId = wordId;
		this.languageId = languageId;
		this.wordText = wordText;
		this.speechPartId = speechPartId;
	}

	public int getWordId() {
		return wordId;
	}

	public void setWordId(int wordId) {
		this.wordId = wordId;
	}

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getWordText() {
		return wordText;
	}

	public void setWordText(String wordText) {
		this.wordText = wordText;
	}

	public int getSpeechPartId() {
		return speechPartId;
	}

	public void setSpeechPartId(int speechPartId) {
		this.speechPartId = speechPartId;
	}	
}
