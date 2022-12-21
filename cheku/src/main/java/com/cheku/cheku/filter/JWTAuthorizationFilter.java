package com.cheku.cheku.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cheku.cheku.config.AuthenticationConfigConstants;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to authorize requests that contain a valid JWT token in the header.
 * If the request is authorized, the user's role is added to the SecurityContext.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    // Constructor for the JWTAuthorizationFilter class
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        // Call the superclass constructor
        super(authenticationManager);
    }

    // Overridden method from the BasicAuthenticationFilter class
    @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the header from the request
        String header = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

        // If the header is null or does not start with the token prefix, pass the request along the filter chain
        if (header == null || !header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // If the header contains a valid token, get the authentication for the user
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        // Set the authentication in the SecurityContext and pass the request along the filter chain
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    // Private method to get the authentication for a user based on the JWT token in the request header
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // Get the header from the request
        String token = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

        // If the header is not null, parse and verify the token
        if (token != null) {
            // Parse the token and verify its signature using the secret
            DecodedJWT verify = JWT.require(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(AuthenticationConfigConstants.TOKEN_PREFIX, ""));

            // Get the username and role from the token claims
            String username = verify.getSubject();
            String role = verify.getClaim("role").asString();

            // If the username is not null, create a UsernamePasswordAuthenticationToken with the username and authorities for the user
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(username, null, getAuthorities(role));
            }
            // If the username is null, return null
            return null;
        }
        // If the header is null, return null
        return null;
    }

    // Private method to create a Collection of GrantedAuthority objects for the role
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Arrays.asList(new SimpleGrantedAuthority(role));
    }

}