package com.petception.controller;

import com.petception.annotation.Authentication;
import com.petception.annotation.Metrics;
import com.petception.constants.WebConstants;
import com.petception.dao.PetInfoDao;
import com.petception.dao.UserDao;
import com.petception.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
            if(!validate(username,password)) {
                userDao.registerNewUser(username, password);
                model.addAttribute("successResponse", "Success!. Please use the login page to login");
                return "login";
            }
            model.addAttribute("errorResponse","Username/password cannot be blank");
            return "newuser";
        } catch (Exception e) {
            model.addAttribute("errorResponse","Please choose another username");
        }
        return "newuser";
    }

    private boolean validate(String username, String password) {
        return StringUtils.isEmpty(username)||StringUtils.isEmpty(password);
    }

    @Authentication
    @RequestMapping(value = "/login")
    @Metrics
    public String login(HttpServletRequest request, HttpServletResponse response, Model model)
    {
        return dashboard(request,response,(String)request.getAttribute("username"),model);
    }

    @Authentication
    @RequestMapping(value = "/dashboard")
    @Metrics
    public String dashboard(HttpServletRequest request,HttpServletResponse response,String username,Model model)
    {
        List<Pet> pets = petInfoDao.getAllPetInfo(username);
        model.addAttribute("pets",pets);
        return "dashboard";
    }

    @Authentication
    @RequestMapping(value = "/addPet")
    @Metrics
    public String addPet(HttpServletRequest request,HttpServletResponse response, Model model)
    {
        return "addPet";
    }

    @RequestMapping(value = "/about")
    @Metrics
    public String about(Model model)
    {
        return "about";
    }

    @Authentication
    @RequestMapping(value = "/logout")
    @Metrics
    public String logout(HttpServletRequest request,HttpServletResponse response, Model model)
    {
        for(Cookie cookie: request.getCookies())
        {
            if("token".equals(cookie.getName()))
            {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                cookie.setValue(null);
                response.addCookie(cookie);
                break;
            }
        }
        return index(model);
    }
}
