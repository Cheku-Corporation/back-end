package com.cheku.cheku.api;

import com.cheku.cheku.model.ApiUser;

import com.cheku.cheku.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
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
        System.out.println("User: " + apiUser.getEmail() + " Role: " + apiUser.getRole());
        return new org.springframework.security.core.userdetails.User(apiUser.getEmail(),
                apiUser.getPassword(), getAuthorities(apiUser.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}