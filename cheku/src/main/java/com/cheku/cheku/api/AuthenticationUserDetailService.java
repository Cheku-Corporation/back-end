package com.cheku.cheku.api;

import com.cheku.cheku.model.ApiUser;

import com.cheku.cheku.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApiUser apiUser = userService.readUserByEmail(email);
        if (apiUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(apiUser.getEmail(), apiUser.getPassword(), Collections.emptyList());
    }
}