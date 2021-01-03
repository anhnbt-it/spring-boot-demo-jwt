package com.codegym.demojwt.controller;

import com.codegym.demojwt.model.AppUser;
import com.codegym.demojwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("module", "home");
        return "index";
    }

    @PostMapping(value="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String addNewUser(@ModelAttribute AppUser user) {
        System.out.println(user.toString());
        userService.save(user);
        return "Saved";
    }

    @GetMapping("/create")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user", new AppUser());
        return modelAndView;
    }

    @PostMapping("/save")
    public String checkUserInfo(@Valid AppUser user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        return "redirect:/users/create";
    }

    @GetMapping("/hello")
    public ModelAndView hello(Principal principal) {
//        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("module", "home");
        return modelAndView;
    }

}
