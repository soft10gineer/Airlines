package com.airline.project.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.airline.project.Login.UserLoginRegister;
import com.airline.project.Login.UserLoginRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserLoginRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserLoginRegister> user = userRepository.findById(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getUserEmail())
                    .password(user.get().getUserPasswrd())
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
