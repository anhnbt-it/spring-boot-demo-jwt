package com.codegym.blog.controller.admin;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.BlogService;
import com.codegym.blog.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("admin/blogs")
public class BlogController {

    Logger log = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        Iterable<Category> categories = categoryService.findAll();
        return categories;
    }

    @GetMapping
    public String index(@RequestParam("s") Optional<String> s, Pageable pageable, Model model) {
        Page<Blog> blogs;
        if (s.isPresent()) {
            blogs = blogService.findAllByTitleContains(s.get(), pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }
        model.addAttribute("blogs", blogs);
        model.addAttribute("txtSearch", s);
        return "admin/blogs/index";
    }

    @GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
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

    //    @RequestMapping("{id}")
//    public ModelAndView view(@PathVariable("id") Blog blog) {
//        return new ModelAndView("messages/show", "message", blog);
//    }
//
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public String delete(@PathVariable("id") Message message, RedirectAttributes redirect) {
//        messageRepository.delete(message);
//        redirect.addFlashAttribute("globalMessage", "Message removed successfully");
//        return "redirect:/";
//    }
//
    @GetMapping("create")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("admin/blogs/create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }

    @PostMapping
    public String saveBlog(@Valid @ModelAttribute("blog") Blog blog, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "admin/blogs/create";
        }
        blog = blogService.save(blog);
        log.info("Create Blog: " + blog.toString());
        redirect.addFlashAttribute("globalMessage", "Successfully created a new blog: " + blog.getId());
        return "redirect:/admin/blogs";
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirect) {
        blogService.deleteById(id);
        redirect.addFlashAttribute("globalMessage", "Successfully deleted a blog");
        return "redirect:/admin/blogs";
    }

    @GetMapping("edit/{id}")
    public String findById(@PathVariable("id") Long id, Model model, RedirectAttributes redirect) {
        Optional<Blog> blog = blogService.findById(id);
        if (blog.isPresent()) {
            model.addAttribute("blog", blog);
            return "admin/blogs/edit";
        } else {
            redirect.addFlashAttribute("Blog with ID " + id + " not found.");
            return "redirect:/admin/blogs";
        }
    }
}
