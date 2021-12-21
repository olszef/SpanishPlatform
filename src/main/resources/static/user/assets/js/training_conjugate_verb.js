function switchCustomAndRandom (element) {
	if (element == null || element.id == "conjugation-training-random") {
		document.getElementById("conjugation-training-search-pane").disabled = true;
	} else {
		document.getElementById("conjugation-training-search-pane").disabled = false;
	}	
}

$(document).ready(function() {
	switchCustomAndRandom(null);
	
	var trainingForm = document.getElementById("conjugation-training-form");
	
	if (trainingForm != null) {
		var trainingInputs = trainingForm.getElementsByTagName("input");
		
		for (i = 0; i < trainingInputs.length; i++) {
		  if (trainingInputs[i].type == "text") {
			trainingInputs[i].addEventListener("change", checkConjugation);
		  }		
		}		
	}

});

function checkConjugation() {
	var answer = this.nextElementSibling;
	
	if (this.value == answer.value) {
		this.classList.add("correct-conjugation");
		this.classList.remove("wrong-conjugation");
	} else {
		this.classList.remove("correct-conjugation");
		if (this.value == '') {
			this.classList.remove("wrong-conjugation");
		} else {
			this.classList.add("wrong-conjugation");
		}
	}

	checkAllFields();
}

function checkAllFields() {
	var trainingForm = document.getElementById("conjugation-training-form");

	if (trainingForm != null) {
		var trainingInputs = trainingForm.getElementsByTagName("input");
		var checkedInputs = 0;
		var correctInputs = 0;
		
		for (i = 0; i < trainingInputs.length; i++) {
		  if (trainingInputs[i].type == "text") {
			checkedInputs++;
			if (trainingInputs[i].classList.contains("correct-conjugation")) {
				correctInputs++;
			}
		  }		
		}
		
		if (checkedInputs == correctInputs) {
			document.getElementById("conjugation-all-correct-text").removeAttribute("hidden");
		} else {
			document.getElementById("conjugation-all-correct-text").setAttribute("hidden", true);
		}		
	}
}

function showSpanishVerb(element) {
	var spanishVerb = element.nextElementSibling;
	
	spanishVerb.removeAttribute("hidden");
	element.setAttribute("hidden", true);
}