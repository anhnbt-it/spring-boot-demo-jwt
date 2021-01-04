package com.codegym.demojwt.controller;

import com.codegym.demojwt.model.AppUser;
import com.codegym.demojwt.model.LoginForm;
import com.codegym.demojwt.model.Response;
import com.codegym.demojwt.security.JwtUtil;
import com.codegym.demojwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public Response createAuthenticationToken(@RequestBody LoginForm loginForm) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        } catch (BadCredentialsException e) {
            logger.info("ANHNBT-LOGGER: " + e.getMessage());
            return new Response("", "Wrong user or password", HttpStatus.FORBIDDEN);
        }
        // Trả về jwt cho người dùng.
        String jwt = jwtUtil.generateToken(loginForm.getUsername());
        return new Response(jwt, "Login Success", HttpStatus.OK);
    }

    @PostMapping("/sign-up")
    public Response signUp(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("ANHNBT-LOGGER: " + user.toString());
        return new Response(userService.save(user), "Created", HttpStatus.CREATED);
    }

}
