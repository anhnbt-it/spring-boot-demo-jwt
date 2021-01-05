package com.codegym.demojwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("module", "home");
        return "web/index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("module", "home");
        return "admin/index";
    }

    @GetMapping("/403")
    public String accessDenied(Model model) {
        model.addAttribute("module", "home");
        return "errors/403";
    }

}
