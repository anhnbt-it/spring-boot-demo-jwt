package com.codegym.blog.controller.admin;

import com.codegym.blog.model.Blog;
import com.codegym.blog.service.BlogService;
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
@RequestMapping("admin/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

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
//    @RequestMapping(params = "create", method = RequestMethod.GET)
//    public String createForm(@ModelAttribute Blog blog) {
//        return "blogs/compose";
//    }
//
//    @RequestMapping(method = RequestMethod.POST)
//    public ModelAndView checkBlogInfo(@Valid Blog blog, BindingResult result,
//                                      RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return new ModelAndView("blogs/compose");
//        }
//        blog = BlogService.save(blog);
//        redirect.addFlashAttribute("globalMessage", "Successfully created a new message");
//        return new ModelAndView("redirect:/{blog.id}", "blog.id", blog.getId());
//    }
}
