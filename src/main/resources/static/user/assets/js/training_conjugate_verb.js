function switchCustomAndRandom (element) {
	var randomRadio = document.getElementById("conjugation-training-random");
	var searchWord = document.getElementById("conjugation-training-search-pane");
	var searchLanguage = document.getElementById("conjugate-training-language-start");
	if (randomRadio.checked) {		
		searchLanguage.options[0].selected = true;
		searchLanguage.disabled = true;		
		searchWord.value = "";
		searchWord.disabled = true;
	} else {
		searchLanguage.disabled = false;
		searchWord.disabled = false;
	}	
}

$(document).ready(function() {
	switchCustomAndRandom(null);
	
	var trainingForm = document.getElementById("conjugation-training-practice-inputs");
	
	if (trainingForm != null) {
		var trainingInputs = trainingForm.getElementsByTagName("input");
		
		for (i = 0; i < trainingInputs.length; i++) {
		  if (trainingInputs[i].type == "text") {
			trainingInputs[i].addEventListener("change", checkConjugation);
		  }		
		}		
	}
	
	$('#conjugate-training-mode-start').on('change', getTenseListDropdown);
});

function getTenseListDropdown() {
	var $modeDropdown = $('#conjugate-training-mode-start');
	var modeSelectedValue = $modeDropdown.val();
	if (modeSelectedValue > 0) {
		var $tenseDropdown = $('#conjugate-training-tense-start');
		var tenseLastValue = $tenseDropdown.value;
		$.ajax({
			url: '/training/conjugation/tenseDropdownOptions',
			data: {modeDropdownValue: modeSelectedValue},
			success: function (response) {
				$tenseDropdown.empty();
				$.each(response, function (index, listValue) {
					$tenseDropdown.append('<option value="' + listValue.tenseId + '">' + listValue.tenseText + '</option>');
				});
				document.getElementById("conjugate-training-tense-start").disabled = false;
			}
		});
	}
}

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
	var trainingForm = document.getElementById("conjugation-training-practice-inputs");

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