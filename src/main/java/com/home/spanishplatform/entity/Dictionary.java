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
}
