package edu.blogapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import edu.blogapp.entity.User;
import edu.blogapp.helper.EmailHelper;
import edu.blogapp.service.UserService;

@ControllerAdvice
public class RootController {

    @Autowired
    UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {

        if (authentication == null) {
            return;
        }

        System.out.println("Adding logged in user information to the model");
        ;
        String username = EmailHelper.getEmailOfLoggedInUser(authentication);

        System.out.println(username);

        Optional<User> user = userService.getUserByEmail(username);

        // User user = userService.getUserByEmail(username);
        System.out.println(user);

        model.addAttribute("loggedInUser", user);

    }

}
