package com.home.spanishplatform.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.home.spanishplatform.POJO.UserForm;
import com.home.spanishplatform.entity.dto.UserDto;
import com.home.spanishplatform.security.MyUserPrincipal;
import com.home.spanishplatform.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/adduser")
	public String showAddUserForm(UserForm userForm, Model theModel) {
		theModel.addAttribute("userFormTitle", "Add new user");
		theModel.addAttribute("userFormNewUser", true);
		userForm.setEmailChangeable(true);
		
		return "user/user_form";
	}
	
	@PostMapping("/adduser")
	public String processEditUserDataForm(@Valid UserForm userForm, BindingResult result, Model theModel) {				
		if (result.hasErrors()) {
			return "user/user_form";
		}

		try {
			userService.createNewUser(userForm.getUserFormName(), userForm.getUserFormUsername(), userForm.getUserFormEmail(), userForm.getUserFormPassword(), userForm.getUserFormIsAdmin());
			theModel.addAttribute("returnTitle", "New user created!");
		} catch (Exception e) {
			theModel.addAttribute("returnTitle", "Error while creating new user");
			theModel.addAttribute("returnMessage", "Please try again");
		}
				
		return "shared/return_message";
	}
	
	@GetMapping("/userlist")
	public String showUserList(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size, Model theModel) {		
		Page<UserDto> users = userService.findAllUsers(PageRequest.of(page, size));
		theModel.addAttribute("userList", users);
		
		return "admin/user_list";
	}
	
	@PostMapping("/removeuser")
	public String processRemoveUser(@RequestParam("userId") int userId, Model theModel) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		if (authentication instanceof AnonymousAuthenticationToken) {
			return "redirect:/login";
		}
		
		if (checkIfRemovalAuthorized(authentication, userId)) {
	        try {
	            userService.removeUser(userId);
	            theModel.addAttribute("returnTitle", "User successfuly removed!");
	        } catch (Exception e) {
				theModel.addAttribute("returnTitle", "Error while removing user");
				theModel.addAttribute("returnMessage", "Please try again");
	        }
		} else {
			theModel.addAttribute("returnTitle", "You are not authorized for that operation!");
		}
        
		return "shared/return_message";
	}
	
	private boolean checkIfRemovalAuthorized(Authentication authentication, int userIdToRemove) {
		MyUserPrincipal loggedUser = (MyUserPrincipal)authentication.getPrincipal();
		if (!loggedUser.getRoles().stream().anyMatch(role -> "ROLE_ADMIN".equals(role.getRoleName()))) {
			return false;
		}
		
		if (loggedUser.getUserId() == userIdToRemove) {
			return false;
		}
		
		return true;
	}
}
