package com.home.spanishplatform.POJO;

import com.home.spanishplatform.entity.NumberName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainingNumber {
	private NumberName numberName;
	private boolean isNumberChecked;
	private boolean isNumberCorrectlyNamed;

	public TrainingNumber(int numberValue) {
		this.numberName = new NumberName(numberValue, "");
		this.isNumberChecked = false;
		this.isNumberCorrectlyNamed = false;
	}

	public int getNumberId() {
		return this.numberName.getNumberId();
	}

	public void setNumberValue(int numberValue) {
		this.numberName.setNumberId(numberValue);
	}

	public String getNumberText() {
		return this.numberName.getNumberText();
	}

	public void setNumberText(String numberText) {
		this.numberName.setNumberText(numberText);;
	}
}
