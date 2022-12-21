package com.cheku.cheku.api;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cheku.cheku.model.ApiUser;
import com.cheku.cheku.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * A service for loading user details for authentication.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationUserDetailService implements UserDetailsService {

    private final UserService userService;

    /**
     * Loads the user details for the given email.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApiUser apiUser = userService.readUserByEmail(email);
        if (apiUser == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(apiUser.getEmail(), apiUser.getPassword(),
                getAuthorities(apiUser.getRole()));
    }

    /**
     * Returns the authorities for the given role.
     */
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }
}
