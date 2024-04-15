function toggleInputs() {
	var selectValue = document.getElementById('card-groupSelect').value;
    var isNewValue = selectValue === 'newGroup';
	
	// Toggle visibility of new group name input and language card inputs based on selection
    document.getElementById('wordInputSpanish').style.display = selectValue ? 'block' : 'none';
	document.getElementById('wordInputTranslation').style.display = selectValue ? 'block' : 'none';
	document.getElementById('existing-cards-table').style.display = !isNewValue ? 'block' : 'none';
	
	if (selectValue) {
		document.getElementById('groupFrom-buttons').style.display = 'block';
		if (isNewValue) {
			document.getElementById('newGroupSection').style.display = 'block';
			var newGroupNameInput = document.getElementById('card-newGroupName');
			newGroupNameInput.required = true;
			newGroupNameInput.name = 'cardsGroup.groupName';
			
			var groupIdInput = document.getElementById('cardsGroup-group-id');
			if (groupIdInput != null) {
				groupIdInput.remove();
			}
			
			var groupRemoveButton = document.getElementById('group-remove-button');
			if (groupRemoveButton != null) {
				groupRemoveButton.remove();
			}
		} else {
			// Submit form to fetch words for the selected group if not "new group"
			document.getElementById('groupSelectionForm').submit();
		}		
	}
}

function submitChangeForm(action, formNo) {
			var formId = 'cardform' + formNo;
            document.getElementById(formId + '-action').value = action;
            document.getElementById(formId).submit();	
}

function submitFormWithoutValidation(element) {
	var form = element.form;

	// Disable required attributes
	var requiredInputs = form.querySelectorAll('[required]');
	requiredInputs.forEach(function(input) {
		input.removeAttribute('required');
	});

	form.submit();
}

$(document).ready(function() {
	var groupDropdownValue = document.getElementById('card-groupSelect').value;
	if (groupDropdownValue != null && groupDropdownValue != '') {
		if (groupDropdownValue == 'newGroup') {
			document.getElementById('newGroupSection').style.display = 'block';
		} else {
			document.getElementById('existing-cards-table').style.display = 'block';
		}
		document.getElementById('wordInputSpanish').style.display = 'block';
		document.getElementById('wordInputTranslation').style.display = 'block';
		document.getElementById('groupFrom-buttons').style.display = 'block';
	}
});