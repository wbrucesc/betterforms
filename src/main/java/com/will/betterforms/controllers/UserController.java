package com.will.betterforms.controllers;

import com.will.betterforms.Models.Role;
import com.will.betterforms.Models.User;
import com.will.betterforms.repositories.RoleRepository;
import com.will.betterforms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    //bring in bcrypt p/w encoder
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //displays signup page
    @RequestMapping(name = "/signup", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    //receives post from signup form
    @RequestMapping(name = "/signup", method = RequestMethod.POST)
    public String signupForm(@ModelAttribute User user) {
        //Role
        Role userRole = roleRepo.findByName("ROLE_USER");
        //set the p/w to the encrypted version of what the p/w already is
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        //overwrites plain text p/w prior to being sent to database
        user.setPassword(encryptedPassword);
        user.setRole(userRole);
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/login";
    }
}
