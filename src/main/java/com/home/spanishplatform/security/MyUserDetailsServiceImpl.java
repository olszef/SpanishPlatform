package com.home.spanishplatform.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.home.spanishplatform.dao.UserRepository;
import com.home.spanishplatform.entity.User;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new MyUserPrincipal(user);
    }
    
    public boolean checkIfOldPasswordValid(final String formOldPassword, final String dbEncodedOldPassword) {
        return passwordEncoder.matches(formOldPassword, dbEncodedOldPassword);
    }
    
    public void changeUserPassword(final int userId, final String newPassword) {
        userRepository.changeUserPassword(userId, passwordEncoder.encode(newPassword));
    }
    
    public void updateUserData(final MyUserPrincipal myUserPrincipal, final String newName, final String newUsername) {
    	User newUser = myUserPrincipal.getUser();
    	newUser.setName(newName);
    	newUser.setUsername(newUsername);
    	userRepository.save(newUser);
    }
}
