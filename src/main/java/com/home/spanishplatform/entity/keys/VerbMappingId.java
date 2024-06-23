package com.home.spanishplatform.entity.keys;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class VerbMappingId implements Serializable {
	private int wordId;
	private int verbId;
}
