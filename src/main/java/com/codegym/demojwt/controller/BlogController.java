package com.codegym.demojwt.controller;

import com.codegym.demojwt.model.Blog;
import com.codegym.demojwt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping({"/admin/blogs", "/user/blogs"})
    public String index(@RequestParam("s") Optional<String> s, Pageable pageable, Model model) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        model.addAttribute("blogs", blogs);
        model.addAttribute("txtSearch", s);
        model.addAttribute("title", "List of Blogs");

        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication().isAuthenticated()) {
            System.out.println(context.getAuthentication().getAuthorities());
            return "blogs/index";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping(value = "/admin/blogs/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Page<Blog> findAllByTitleContains(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        return blogs;
    }
}
