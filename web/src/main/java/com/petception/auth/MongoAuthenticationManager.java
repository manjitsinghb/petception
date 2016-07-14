package com.petception.auth;

import com.petception.dao.UserDao;
import com.petception.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manjtsingh on 7/9/2016.
 */
@Component
public class MongoAuthenticationManager implements AuthenticationProvider {

    @Autowired
    private UserDao userDao;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";
        User user =  userDao.isUserRegistered(username, password);
            if(user == null)
            {
                throw new BadCredentialsException("Wrong credentials. Please try again");
            }
        List<SimpleGrantedAuthority> listOfAuthorities = new ArrayList<>();
        listOfAuthorities.add(new SimpleGrantedAuthority("user"));
        return new UsernamePasswordAuthenticationToken(username, password,listOfAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
