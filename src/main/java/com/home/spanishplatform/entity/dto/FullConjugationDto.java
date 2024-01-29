package com.home.spanishplatform.entity.dto;

public class FullConjugationDto {

	private String modeText;
	private String tenseText;
	private String single1;
	private String single2;
	private String single3;
	private String plural1;
	private String plural2;
	private String plural3;
	
	public FullConjugationDto(String modeText, String tenseText, String single1, String single2, String single3,
			String plural1, String plural2, String plural3) {
		super();
		this.modeText = modeText;
		this.tenseText = tenseText;
		this.single1 = single1;
		this.single2 = single2;
		this.single3 = single3;
		this.plural1 = plural1;
		this.plural2 = plural2;
		this.plural3 = plural3;
	}
	
	public FullConjugationDto() {
	}

	public String getModeText() {
		return modeText;
	}
	public void setModeText(String modeText) {
		this.modeText = modeText;
	}
	public String getTenseText() {
		return tenseText;
	}
	public void setTenseText(String tenseText) {
		this.tenseText = tenseText;
	}
	public String getSingle1() {
		return single1;
	}
	public void setSingle1(String single1) {
		this.single1 = single1;
	}
	public String getSingle2() {
		return single2;
	}
	public void setSingle2(String single2) {
		this.single2 = single2;
	}
	public String getSingle3() {
		return single3;
	}
	public void setSingle3(String single3) {
		this.single3 = single3;
	}
	public String getPlural1() {
		return plural1;
	}
	public void setPlural1(String plural1) {
		this.plural1 = plural1;
	}
	public String getPlural2() {
		return plural2;
	}
	public void setPlural2(String plural2) {
		this.plural2 = plural2;
	}
	public String getPlural3() {
		return plural3;
	}
	public void setPlural3(String plural3) {
		this.plural3 = plural3;
	}
}
