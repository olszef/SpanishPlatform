package com.home.spanishplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.home.spanishplatform.entity.keys.VerbMappingId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(VerbMappingId.class)
@Table(name="verb_mapping")
public class VerbMapping {
	
	@Id
	@Column(name="word_id")
	private int wordId;
	
	@Id
	@Column(name="verb_id")
	private int verbId;
}
