function checkIfCorrectValue(element) {
	var answer = element.nextElementSibling;
	
	if (element.value == answer.value) {
		element.classList.add("correct-value");
		element.classList.remove("wrong-value");
	} else {
		element.classList.remove("correct-value");
		if (element.value == '') {
			element.classList.remove("wrong-value");
		} else {
			element.classList.add("wrong-value");
		}
	}
}