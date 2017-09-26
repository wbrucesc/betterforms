package com.will.betterforms.controllers;

import com.will.betterforms.Models.Secret;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class HomeController {

    private ArrayList<Secret> mySecrets = new ArrayList<>();        //to store secrets

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("secret", new Secret());
        model.addAttribute("mySecrets", mySecrets);
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@ModelAttribute Secret secret) {
        System.out.println(secret);
        mySecrets.add(secret);          //adds new secret to mySecrets arraylist
        return "redirect:/";
    }
}
