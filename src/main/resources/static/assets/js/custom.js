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
	
	var menuTabsAll = $('#menu li span');
	var menuItems = $("#menu > ul > li > ul > li > a");
	
	menuItems.on("click", function() {
		
		menuTab = $(this).parent().parent().prev();
		
		menuTabsAll.each(function() {
			localStorage.removeItem(menuTab.attr("id"));
		})
		
		if (menuTab.hasClass("active")) {
			localStorage.setItem(menuTab.attr("id"), "active");
		}
    });
	
	menuTabsAll.each(function() {
		if (localStorage.getItem(this.id) != null) {
			$(this).addClass("active");
			localStorage.removeItem(this.id);
		}
	});
});