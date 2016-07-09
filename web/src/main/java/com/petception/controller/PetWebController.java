package com.petception.controller;

import com.petception.annotation.Metrics;
import com.petception.constants.WebConstants;
import com.petception.dao.PetInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by manjtsingh on 6/30/2016.
 */
@Controller
public class PetWebController {

    @Autowired
    WebConstants webConstants;

    @Autowired
    PetInfoDao petInfoDao;

    @RequestMapping(value = "/")
    @Metrics
    public String index(Model model)
    {
        model.addAttribute("header",webConstants.getIndex_header());
        model.addAttribute("headerText",webConstants.getIndex_header_text());
        model.addAttribute("pets",petInfoDao.getAllPetInfo());
        return "index";
    }

    @RequestMapping(value = "/dashboard")
    public String dashboard(Model model)
    {
        return "dashboard";
    }


    @RequestMapping(value = "/addPet")
    public String addPet(Model model)
    {
        return "addPet";
    }

    @RequestMapping(value = "/about")
    public String about(Model model)
    {
        return "about";
    }
}
