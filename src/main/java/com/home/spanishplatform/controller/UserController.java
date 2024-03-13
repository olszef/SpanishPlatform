package com.home.spanishplatform.controller;

import javax.naming.InvalidNameException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.home.spanishplatform.POJO.ContactUsForm;
import com.home.spanishplatform.POJO.PasswordChangeForm;
import com.home.spanishplatform.POJO.UserForm;
import com.home.spanishplatform.security.MyUserDetailsServiceImpl;
import com.home.spanishplatform.security.MyUserPrincipal;
import com.home.spanishplatform.service.EmailServiceImpl;
import com.home.spanishplatform.validation.PasswordChangeValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private MyUserDetailsServiceImpl userDetailsService;
	
    private PasswordChangeValidator passwordChangeValidator;
    
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    
    @Value("${spring.mail.username}")
    private String spanishPlatformEmailBox;

	@Autowired
    public void PasswordChangeController() {
		this.passwordChangeValidator = new PasswordChangeValidator();
    }
	
	@GetMapping("/changepass")
	public String showChangeUserPasswordForm(PasswordChangeForm changePasswordForm) {
		return "user/change_password";
	}
	
	@PostMapping("/changepass")
	public String processChangeUserPasswordFrom(@Valid PasswordChangeForm changePasswordForm, BindingResult result, Model theModel) throws InvalidNameException {
		MyUserPrincipal loadedUser = getAuthenticatedUser();
		if (loadedUser == null) {
			return "redirect:/login";
		}
		
		//Validate password fields
		changePasswordForm.setCurrentEncryptedPassword(loadedUser.getPassword());
		passwordChangeValidator.validate(changePasswordForm, result);
		
		//If error show related messages, else update password and show success on the screen
		if (result.hasErrors()) {
			return "user/change_password";
		}
		
		try {
			userDetailsService.changeUserPassword(loadedUser.getUserId(), changePasswordForm.getNewPassword());
			theModel.addAttribute("returnTitle", "Password successfully updated!");
		} catch (Exception e) {
			theModel.addAttribute("returnTitle", "Technical error- Password could not be changed");
		}
		
		return "user/return_message";		
	}
	
	@GetMapping("/contactus")
	public String showContactUsForm(ContactUsForm contactUsForm) {
		contactUsForm.setContactLanguage("EN");
		return "user/contact_us";
	}
	
	@PostMapping("/contactus")
	public String processContactUsForm(@Valid ContactUsForm contactUsForm, BindingResult result, Model theModel) {
		MyUserPrincipal loadedUser = getAuthenticatedUser();
		if (loadedUser == null) {
			return "redirect:/login";
		}
		
		if (result.hasErrors()) {
			return "user/contact_us";
		}
		
		String simpleSubject = "[" + contactUsForm.getContactLanguage() + "] " + contactUsForm.getContactSubject();
		String originalSubject = "[From: " + contactUsForm.getContactFullName() + "]" + simpleSubject;
		String originalMessage = contactUsForm.getContactMessageText() + "\n\n" + "[Respond to: " + loadedUser.getUserEmail() + "]";
		
		try {
			emailServiceImpl.sendSimpleMessage(spanishPlatformEmailBox, originalSubject, originalMessage);
			theModel.addAttribute("returnTitle", "Email sent successfully!");
			
			if (contactUsForm.getContactEmailCopy()) {
				String copySubject = "(Copy of your message)" + simpleSubject;
				try {
					emailServiceImpl.sendSimpleMessage(loadedUser.getUserEmail(), copySubject, contactUsForm.getContactMessageText());
				} catch (MailException mailCopyException) {
					theModel.addAttribute("returnMessage", "Unfortunately, we failed to deliver your mail copy...");
				}
			}			
		} catch (MailException mailException) {
			theModel.addAttribute("returnTitle", "Error - email could not be sent");
		}
				
		return "user/return_message";
	}
	
	@GetMapping("/editdata")
	public String showEditUserDataForm(UserForm userForm, Model theModel) {
		MyUserPrincipal loadedUser = getAuthenticatedUser();
		if (loadedUser == null) {
			return "redirect:/login";
		}
		
		theModel.addAttribute("userFormTitle", "Edit your data");
		theModel.addAttribute("userFormIsEmpty", false);
		userForm.setUserFormName(loadedUser.getName());
		userForm.setUserFormUsername(loadedUser.getUsername());
		userForm.setUserFormEmail(loadedUser.getUserEmail());
		
		return "user/user_form";
	}
	
	@PostMapping("/editdata")
	public String processEditUserDataForm(@Valid UserForm userForm, BindingResult result, Model theModel) {		
		MyUserPrincipal loadedUser = getAuthenticatedUser();
		if (loadedUser == null) {
			return "redirect:/login";
		}
		
		if (result.hasErrors()) {
			return "user/user_form";
		}
		
		try {
			userDetailsService.updateUserData(loadedUser, userForm.getUserFormName(), userForm.getUserFormUsername());
		} catch (Exception e) {
			theModel.addAttribute("returnTitle", "Error - your data could not be updated");
			return "user/return_message";
		}
		
		MyUserPrincipal changedUser = (MyUserPrincipal)userDetailsService.loadUserByUsername(loadedUser.getUserEmail());
		Authentication newAuth = new UsernamePasswordAuthenticationToken(changedUser, null, changedUser.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(newAuth);
		
		theModel.addAttribute("returnTitle", "Your was data updated successfully!");
		return "user/return_message";
	}
	
	private MyUserPrincipal getAuthenticatedUser() {
		//Check if user logged in
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		if (authentication instanceof AnonymousAuthenticationToken) {
		    return null;
		}
		
		MyUserPrincipal loadedUser;
		try {
			//Get current user data from database
			loadedUser = (MyUserPrincipal)userDetailsService.loadUserByUsername(((MyUserPrincipal)authentication.getPrincipal()).getUserEmail());
		} catch (Exception e) {
			return null;
		}
		
		return loadedUser;		
	}
}
