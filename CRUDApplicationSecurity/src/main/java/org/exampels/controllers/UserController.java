package org.exampels.controllers;

import org.exampels.Services.UserServices;
import org.exampels.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping()
    public String showUser(Model model, Principal principal) {
        User user = userServices.getUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
