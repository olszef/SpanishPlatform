package com.home.spanishplatform.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.home.spanishplatform.dao.RoleRepository;
import com.home.spanishplatform.dao.UserRepository;
import com.home.spanishplatform.entity.Role;
import com.home.spanishplatform.entity.User;
import com.home.spanishplatform.entity.dto.UserDto;
import com.home.spanishplatform.exceptions.CustomDatabaseException;
import com.home.spanishplatform.exceptions.CustomNotFoundException;
import com.home.spanishplatform.security.MyUserPrincipal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public boolean checkIfOldPasswordValid(final String formOldPassword, final String dbEncodedOldPassword) {
        return passwordEncoder.matches(formOldPassword, dbEncodedOldPassword);
    }
    
    @Override
    public void changeUserPassword(final int userId, final String newPassword) {
    	try {
    		userRepository.changeUserPassword(userId, passwordEncoder.encode(newPassword));
    	} catch (Exception e) {
    		throw new CustomDatabaseException("Failed to update password for user with id: " + userId, e);
    	}
    }
    
    @Override
    public void updateUserData(final MyUserPrincipal myUserPrincipal, final String newName, final String newUsername) {
    	User newUser = myUserPrincipal.getUser();
    	newUser.setName(newName);
    	newUser.setUsername(newUsername);
    	try {
    		userRepository.save(newUser);
    	} catch (Exception e) {
    		throw new CustomDatabaseException("Failed to save user data with id: " + newUser.getUserId(), e);
    	}
    }
    
    @Override
    public void createNewUser(final String newName, final String newUsername, final String newEmail, final String initialPassword, final boolean isAdmin) {
    	User user = userRepository.findByEmail(newEmail);
    	
        if (user != null) {
        	throw new UsernameNotFoundException(newEmail);
        }
        
        List<Role> allRoles;       
   		if (isAdmin) {
			allRoles = roleRepository.findAll();
		} else {
			allRoles = new ArrayList<Role>();
			allRoles.add(roleRepository.findByRoleName("ROLE_USER"));
		}
        
    	user = new User();
    	user.setName(newName);
    	user.setUsername(newUsername);
    	user.setPassword(passwordEncoder.encode(initialPassword));
    	user.setEmail(newEmail);
    	user.setLastLogin(LocalDateTime.parse("0001-01-01 01:01:01", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    	user.setRoles(allRoles);
    	
    	try {
    		userRepository.save(user);
    	} catch (Exception e) {
    		throw new CustomDatabaseException("Failed to save user with id: " + user.getUserId(), e);
    	}
    }

	@Override
	public Page<UserDto> findAllUsers(Pageable pageable) {
		Page<User> userPage = userRepository.findAll(pageable);
		
		List<UserDto> userDtoList = userPage.getContent().stream()
	            .map(user -> new UserDto(user))
	            .collect(Collectors.toList());
		
		return new PageImpl<>(userDtoList, userPage.getPageable(), userPage.getTotalElements());
	}

	@Override
	@Transactional
	public void removeUser(int userId) {		
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new CustomNotFoundException("User not found with id: " + userId));
            user.clearRoles();

            // Save the user to remove user roles and then remove the user itself
            userRepository.save(user);            
            userRepository.delete(user);
        } catch (CustomNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomDatabaseException("Failed to remove user with id: " + userId, e);
        }
	}
}
