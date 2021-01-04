package com.codegym.demojwt.controller;

import com.codegym.demojwt.customerror.BlogNotFoundException;
import com.codegym.demojwt.model.Blog;
import com.codegym.demojwt.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public ResponseEntity<Page<Blog>> all(@RequestParam("s") Optional<String> s, Pageable pageable) {
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

    @GetMapping("/search")
    public Page<Blog> findAllByTitleContains(@RequestParam("s") Optional<String> s, Pageable pageable) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        return blogs;
    }

    @PostMapping
    public Blog newBlog(@RequestBody Blog newBlog) {
        return blogService.save(newBlog);
    }

    @GetMapping("/{id}")
    public Blog one(@PathVariable Long id) {
        return blogService.findById(id)
                .orElseThrow(() -> new BlogNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Blog replaceBlog(@RequestBody Blog newBlog, @PathVariable Long id) {
        return blogService.findById(id)
                .map(blog -> {
                    blog.setTitle(newBlog.getTitle());
                    blog.setText(newBlog.getText());
                    blog.setCategory(newBlog.getCategory());
                    blog.setImage(newBlog.getImage());
                    blog.setActive(newBlog.getActive());
                    return blogService.save(blog);
                })
                .orElseGet(() -> {
                    newBlog.setId(id);
                    return blogService.save(newBlog);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBlog(@PathVariable Long id) {
        blogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
