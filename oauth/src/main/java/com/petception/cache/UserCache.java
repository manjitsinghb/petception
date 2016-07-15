package com.petception.cache;

import com.petception.auth.UserResponse;
import com.petception.user.User;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by manjtsingh on 7/14/2016.
 */

@Component
public class UserCache {

    @CachePut(value = "userAuth" , key = "#token")
    public UserResponse updateUserInfo(User user, String token, UserResponse userResponse) {
        userResponse.setUserInfo(user);
        userResponse.setToken(token);
        return userResponse;
    }

    @Cacheable(value = "userAuth" , key = "#token")
    public UserResponse isUserValid(String token) {
        return null;
    }


}
