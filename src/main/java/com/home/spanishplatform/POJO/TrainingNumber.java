package com.home.spanishplatform.POJO;

import com.home.spanishplatform.entity.NumberName;

public class TrainingNumber {
	private NumberName numberName;
	private boolean isNumberChecked;
	private boolean isNumberCorrectlyNamed;

	public TrainingNumber() {
	}

	public TrainingNumber(int numberValue) {
		this.numberName = new NumberName(numberValue, "");
		this.isNumberChecked = false;
		this.isNumberCorrectlyNamed = false;
	}

	public TrainingNumber(NumberName numberName, boolean isNumberChecked, boolean isNumberCorrectlyNamed) {
		this.numberName = numberName;
		this.isNumberChecked = isNumberChecked;
		this.isNumberCorrectlyNamed = isNumberCorrectlyNamed;
	}

	public NumberName getNumberName() {
		return numberName;
	}

	public void setNumberName(NumberName numberName) {
		this.numberName = numberName;
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

	public boolean isNumberChecked() {
		return isNumberChecked;
	}

	public void setNumberChecked(boolean isNumberChecked) {
		this.isNumberChecked = isNumberChecked;
	}

	public boolean isNumberCorrectlyNamed() {
		return isNumberCorrectlyNamed;
	}

	public void setNumberCorrectlyNamed(boolean isNumberCorrectlyNamed) {
		this.isNumberCorrectlyNamed = isNumberCorrectlyNamed;
	}
}
