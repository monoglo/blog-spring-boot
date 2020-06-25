package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.service.impl.UserAPIServiceImpl;
import com.rankofmatrix.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserAPIServiceImpl userAPIService;

    @Autowired
    public void setUserAPIServiceImpl(UserAPIServiceImpl userAPIService) {
        this.userAPIService = userAPIService;
    }

    @PostMapping(path = "/register")
    public @ResponseBody User registerUser (@RequestBody User registerUser) {
        return userAPIService.registerUser(registerUser);
    }

    @GetMapping(path = "/")
    public @ResponseBody
    List<User> getAllUsers() {
        return userAPIService.getAllUsers();
    }

    @PostMapping(path = "/login")
    public @ResponseBody User loginUser(@RequestBody User loginUser) {
        return userAPIService.loginUser(loginUser);
    }
}
