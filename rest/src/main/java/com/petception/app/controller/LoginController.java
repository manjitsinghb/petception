package com.petception.app.controller;

import com.petception.dao.UserDao;
import com.petception.enums.ServerStatus;
import com.petception.request.LoginRequest;
import com.petception.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * Created by manjtsingh on 6/5/2016.
 */
@RestController
public class LoginController {

    @Autowired
    private UserDao userDao;


   @RequestMapping(path = "/login",consumes = MediaType.APPLICATION_JSON_VALUE,
           produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
   public @ResponseBody LoginResponse processRequest(@RequestBody LoginRequest request)
   {
        LoginResponse loginResponse = new LoginResponse();
       if(userDao.isUserRegistered(request.getUsername(),request.getPassword())==null)
        {
            loginResponse.setErrorMessage("Failed to find user");
            loginResponse.setStatus(ServerStatus.FAILED.name());
        }
       loginResponse.setStatus(ServerStatus.SUCCESS.name());
       return loginResponse;
   }

}
