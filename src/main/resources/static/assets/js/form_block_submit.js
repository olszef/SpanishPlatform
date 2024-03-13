$(document).ready(function() {
	$('form input[type="submit"]').attr('disabled', 'disabled');
	$('form input').on("keyup change", function() {

        var empty = false;
        $('form input').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });

        if (empty) {
            $('form input[type="submit"]').attr('disabled', 'disabled');
        } else {
            $('form input[type="submit"]').removeAttr('disabled');
        }
    });
});