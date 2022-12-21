package com.cheku.cheku.filter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// This class is used to handle unauthorized requests.
// It sets the response status to 401 (Unauthorized) and adds an error message to the response body.
 @Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        // Set the response content type to json and the status to 401
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // Add the error message to the response body
        response.getOutputStream().println("{ \"error\": \"" + authException.getMessage() + "\" }");

    }

}
