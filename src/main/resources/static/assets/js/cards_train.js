$(document).ready(function() {

	$("#card-groupSelect").on("change", function() {
		manageDisableButtonAttribute();
	});
	
	manageDisableButtonAttribute();
});

function manageDisableButtonAttribute() {
	var groupSelectionForm = $("#groupSelectionForm");
	var selectDropdown = groupSelectionForm.find('select');
	var submitBtn = groupSelectionForm.find(':submit');
	submitBtn.attr('disabled', 'disabled');
	
	if (selectDropdown.val() > '') {
		submitBtn.removeAttr('disabled');		
	} else {
		submitBtn.attr('disabled', 'disabled');
	}	
}