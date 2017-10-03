package com.will.betterforms.controllers;

import com.will.betterforms.Models.Secret;
import com.will.betterforms.Models.User;
import com.will.betterforms.repositories.SecretRepository;
import com.will.betterforms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class SecretController {

    @Autowired
    private SecretRepository secretRepo;

    @Autowired
    private UserRepository userRepo;

    //detail view
    @RequestMapping(value = "/secret/{id}")
    public String secretDetail(Model model,
                               @PathVariable("id") Long id,
                               Principal principal) {
//        Secret mySecret = secretRepo.findOne(id);
        User user = userRepo.findByUsername(principal.getName());
        Secret mySecret = secretRepo.findByIdAndOwner(id, user);
        model.addAttribute("secret", mySecret);
        System.out.println(id);
        return "secret";
    }

    //update
    @RequestMapping(value = "/secret/{id}", method = RequestMethod.POST)
    public String secretDetailForm(@ModelAttribute Secret secret,
                                   Principal principal) {

        User me = userRepo.findByUsername(principal.getName());
        if (secret.getOwner() == me) {
            secret.setOwner(me);
            secretRepo.save(secret);
        }
        return "redirect:/";
    }

    //modal "are you sure you want to delete this secret?"
    @RequestMapping(value = "/secret/{id}/delete")
    public String secreteDeleteConfirm(Model model,
                                       @PathVariable("id") Long id) {
        Secret secret = secretRepo.findOne(id);
        model.addAttribute("secret", secret);
        return "secretDeleteConfirm";
    }

    //delete secret
    @RequestMapping(value = "/secret/{id}/delete", method = RequestMethod.POST)
    public String secretDelete(@PathVariable("id") Long id) {
        secretRepo.delete(id);
        return "redirect:/";
    }
}
