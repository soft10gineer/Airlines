package com.airline.project.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.airline.project.Login.UserLoginRegister;
import com.airline.project.Login.UserLoginRepository;
import com.airline.project.Session.SessionRepository;
import com.airline.project.Session.SsnDtls;

@Service

@Primary
public class UserSessionServiceImpl implements UserDetailsService {

    @Autowired
    private SessionRepository sessionrepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SsnDtls> userSession = sessionrepository.findById(username);
        if (userSession != null) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userSession.get().getSsnRefNo())
                    .password("")
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

