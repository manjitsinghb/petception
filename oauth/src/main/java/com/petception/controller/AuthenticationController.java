package com.petception.controller;

import com.petception.dao.UserDao;
import com.petception.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by manjtsingh on 8/22/2016.
 */
@RestController
public class AuthenticationController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST,consumes = MediaType.APPLICATION_XML_VALUE,produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody  String autheticateUser(@RequestBody User user)
    {
        User userFromDB = userDao.isUserRegistered(user.getUsername(),user.getPassword());
        if(userFromDB!=null)
        {
            String token = new String(Base64Utils.encode((user.getUsername()+ UUID.randomUUID().toString()).getBytes()));
            userDao.updateTokenForUser(user.getUsername(),token);
            return token;
        }
        return null;
    }

    @RequestMapping(value = "/isValidUser",method = RequestMethod.POST,consumes = MediaType.TEXT_PLAIN_VALUE,produces = MediaType.APPLICATION_XML_VALUE)
    public @ResponseBody User validateUser(@RequestBody String token)
    {
        User userFromDB = userDao.isUserValid(token);
        if(userFromDB!=null)
        {
           return userFromDB;
        }
        return null;
    }

}
