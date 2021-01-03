package com.codegym.demojwt.controller;

import com.codegym.demojwt.model.LoginForm;
import com.codegym.demojwt.model.LoginResponse;
import com.codegym.demojwt.security.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RESTfulController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(RESTfulController.class);

    @PostMapping("/login")
    public LoginResponse createAuthenticationToken(@RequestBody LoginForm loginForm) throws Exception {
        logger.info("ANHNBT-LOGGER: " + loginForm.toString());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        // Trả về jwt cho người dùng.
        String jwt = jwtUtil.generateToken(loginForm.getUsername());
        return new LoginResponse(jwt);
    }

    @GetMapping("/login")
    public String randomStuff() {
        return "JWT Hợp lệ mới có thể thấy được message này";
    }

}
