package com.petception.cache;

import com.petception.auth.UserRequest;
import com.petception.auth.UserResponse;
import com.petception.dao.UserDao;
import com.petception.enums.ServerStatus;
import com.petception.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Calendar;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

/**
 * Created by manjtsingh on 7/14/2016.
 */
@RestController
public class Authentication {

    @Autowired
    UserDao userDao;

    @Autowired
    UserCache userCache;

    @RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public @ResponseBody UserResponse autheticateUser(@RequestBody UserRequest userRequest)
    {
     return authenticateAndGetToken(userRequest.getUsername(),userRequest.getPassword());
    }

    private UserResponse authenticateAndGetToken(String username, String password) {
        User user = userDao.isUserRegistered(username,password);
        UserResponse userResponse = new UserResponse();
        if(user == null)
        {
            userResponse.setStatus(ServerStatus.FAILED);
            return userResponse;
        }
        userResponse.setStatus(ServerStatus.SUCCESS);
        String token = getToken(username);
        userCache.updateUserInfo(user,token,userResponse);
        return userResponse;
    }



    private String getToken(String username)
    {
        Calendar cal = Calendar.getInstance();
        byte userByte[] = encodeBase64(username.getBytes());
        byte email[] = encodeBase64("petWorks".getBytes());
        StringBuilder builder = new StringBuilder();
        builder.append(Arrays.toString(userByte)).append(Arrays.toString(email)).append(Arrays.toString(encodeBase64(cal.getTime().toString().getBytes())));
        return builder.toString().substring(0,120);

    }

    @RequestMapping(value="/isValidToken")
    public @ResponseBody UserResponse isTokenValid(@RequestBody String token)
    {
        UserResponse user = userCache.isUserValid(token);
        if(user == null)
        {
            UserResponse userResponse = new UserResponse();
            userResponse.setStatus(ServerStatus.FAILED);
            return userResponse;
        }
        return user;
    }
}
