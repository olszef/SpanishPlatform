package com.home.spanishplatform.entity;

import java.util.Objects;

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
@Table(name="number_name")
public class NumberName {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="number_id")
	private int numberId;

	@Column(name="number_text")
	private String numberText;

	@Override
	public String toString() {
		return "NumberName [numberId=" + numberId + ", numberText=" + numberText + "]";
	}	

	@Override
	public int hashCode() {
		return Objects.hash(numberId, numberText);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}

		if (!(obj instanceof NumberName)) {
			return false;
		}

		NumberName other = (NumberName) obj;

		return this.numberId == other.numberId && this.numberText.equals(other.numberText);
	}	
}
