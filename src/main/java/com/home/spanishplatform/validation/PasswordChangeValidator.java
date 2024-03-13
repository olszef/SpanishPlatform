package com.home.spanishplatform.validation;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.home.spanishplatform.POJO.PasswordChangeForm;

public class PasswordChangeValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
        return PasswordChangeForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PasswordChangeForm passwordChangeForm = (PasswordChangeForm) target;

		//check user old password
		if (!checkIfOldPasswordValid(passwordChangeForm.getOldPassword(), passwordChangeForm.getCurrentEncryptedPassword())) {
			errors.rejectValue("oldPassword", "passwords.incorrect_password", "The entered password is incorrect");
		}

		//check that new pass and confirmation match
		if (!passwordChangeForm.getNewPassword().equals(passwordChangeForm.getConfirmPassword()))
			errors.rejectValue("confirmPassword", "passwords.not_match", "The new password and the confirmation do not match");
	}
	
    private boolean checkIfOldPasswordValid(final String formOldPassword, final String dbEncodedOldPassword) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(formOldPassword, dbEncodedOldPassword);
    }

}
