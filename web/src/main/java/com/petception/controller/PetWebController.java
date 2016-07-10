package com.petception.controller;

import com.petception.annotation.Metrics;
import com.petception.constants.WebConstants;
import com.petception.dao.PetInfoDao;
import com.petception.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by manjtsingh on 6/30/2016.
 */
@Controller
public class PetWebController {

    @Autowired
    WebConstants webConstants;

    @Autowired
    PetInfoDao petInfoDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/")
    @Metrics
    public String index(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
    /* The user is logged in :) */
            return "dashboard";
        }
        model.addAttribute("header",webConstants.getIndex_header());
        model.addAttribute("headerText",webConstants.getIndex_header_text());
        model.addAttribute("pets",petInfoDao.getAllPetInfo());
        return "index";
    }

    @RequestMapping(value = "/register")
    @Metrics
    public String register(Model model)
    {
        return "newuser";
    }

    @RequestMapping("/completeRegistration")
    @Metrics
    public String completeRegistration(@RequestParam String username,@RequestParam String password,Model model)
    {
        try {
            userDao.registerNewUser(username,password);
            model.addAttribute("successResponse","Success!. Please use the login page to login");
            return "login";
        } catch (Exception e) {
            model.addAttribute("errorResponse","Please choose another username");
        }
        return "newuser";
    }


    @RequestMapping(value = "/login")
    @Metrics
    public String login(Model model)
    {
        return "login";
    }


    @RequestMapping(value = "/dashboard")
    @Metrics
    public String dashboard(Model model)
    {
        return "dashboard";
    }


    @RequestMapping(value = "/addPet")
    @Metrics
    public String addPet(Model model)
    {
        return "addPet";
    }

    @RequestMapping(value = "/about")
    @Metrics
    public String about(Model model)
    {
        return "about";
    }
}
