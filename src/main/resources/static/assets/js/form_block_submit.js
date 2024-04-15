$(document).ready(function() {
	$('form input[type="submit"].primary').attr('disabled', 'disabled');
	$('form input').on("keyup change", function() {

        var empty = false;
        $('form input[required]').each(function() {
            if ($(this).val() == '') {
                empty = true;
            }
        });

        if (empty) {
            $('form input[type="submit"].primary').attr('disabled', 'disabled');
        } else {
            $('form input[type="submit"].primary').removeAttr('disabled');
        }
    });
});