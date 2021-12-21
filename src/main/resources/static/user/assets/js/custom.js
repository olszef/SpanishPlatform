function swapTranslationLanguages() {

	var languageFromElement = document.getElementById("languageFrom");
	var languageToElement = document.getElementById("languageTo");
	
	var languageFromId = languageFromElement.value;
	var languageToId = languageToElement.value;
	
	languageFromElement.value = languageToId;
	languageToElement.value = languageFromId;
}

function forceSpanishLanguage(element) {

	var otherOptionElement;
	
	if (element.id == "languageFrom") {
		otherOptionElement = document.getElementById("languageTo");
	} else {
		otherOptionElement = document.getElementById("languageFrom");
	}
	
	if (element.value == 1) {
		otherOptionElement.value = 2;
	} else {
		otherOptionElement.value = 1;
	}
}