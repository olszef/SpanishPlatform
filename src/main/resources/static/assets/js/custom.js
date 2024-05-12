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

$(document).ready(function() {
	
	var menuItems = $('#menu li span');
	
	menuItems.on("click", function() {
		
		menuItems.each(function() {
			localStorage.removeItem(this.id);
		})
		
		if ($(this).hasClass("active")) {
			localStorage.setItem(this.id, "active");
		}
    });
	
	menuItems.each(function() {
		if (localStorage.getItem(this.id) != null) {
			$(this).addClass("active");
			localStorage.removeItem(this.id);
		}
	});
});