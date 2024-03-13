$(document).ready(function() {
	$('form input[type="submit"]').attr('disabled', 'disabled');
	$('form input').on("keyup change", function() {

        var empty = false;
        $('form input').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });
		
		var passwordMatch = false;
		if ($('#newPassword').val() == $('#confirmPassword').val()) {
			passwordMatch = true;
			$('#confirmErrorMessage').attr('hidden', 'hidden');
		} else {			
			$('#confirmErrorMessage').removeAttr('hidden');
		}

        if (empty || !passwordMatch) {
            $('form input[type="submit"]').attr('disabled', 'disabled');
        } else {
            $('form input[type="submit"]').removeAttr('disabled');
        }
    });
});