function checkConjugationTense(element) {
	var answer = element.nextElementSibling;
	
	if (element.value == answer.value) {
		element.classList.add("correct-conjugation");
		element.classList.remove("wrong-conjugation");
	} else {
		element.classList.remove("correct-conjugation");
		if (element.value == '') {
			element.classList.remove("wrong-conjugation");
		} else {
			element.classList.add("wrong-conjugation");
		}
	}
	element.blur();
}