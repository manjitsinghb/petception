package com.petception.auth;

import com.petception.enums.ServerStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by manjtsingh on 7/14/2016.
 */
@Component
public class OAuthAuthenticationManager implements AuthenticationProvider  {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserRequest userRequest = new UserRequest();
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";
        userRequest.setUsername(username);
        userRequest.setPassword(password);
        UserResponse response = restTemplate.postForObject("http://localhost:8082/authenticate",userRequest,UserResponse.class);
        if(response==null || ServerStatus.FAILED.name().equals(response.getStatus()))
        {
            throw new BadCredentialsException("Wrong credentials. Please try again");
        }
        return new UsernamePasswordAuthenticationToken(username, password);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
