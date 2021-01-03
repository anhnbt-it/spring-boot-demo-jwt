package com.codegym.demojwt.controller;

import com.codegym.demojwt.model.Blog;
import com.codegym.demojwt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("/blogs")
    public ResponseEntity<Page<Blog>> index(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        SecurityContext context = SecurityContextHolder.getContext();
        if (context.getAuthentication().isAuthenticated()) {
            System.out.println(context.getAuthentication().getAuthorities());
            return new ResponseEntity<>(blogs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/blogs/search")
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
