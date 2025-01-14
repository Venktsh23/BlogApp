package edu.blogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.blogapp.config.SecurityConfiguration;
import edu.blogapp.entity.User;
import edu.blogapp.form.UserForm;
import edu.blogapp.service.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityConfiguration securityConfiguration;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/do-register")
    public String processRegister(@ModelAttribute UserForm form) {
        System.out.println("Processing registration");

        System.out.println(form);
        User user = new User();
        user.setUsername(form.getUsername());
        user.setEmail(form.getEmail());
        user.setPassword(securityConfiguration.encoder().encode(form.getPassword()));
        user.setPhone(form.getPhone());
        System.out.println(form.getPhone());

        userService.saveUser(user);

        System.out.println("user saved :");

        return "redirect:/login";
    }

}
