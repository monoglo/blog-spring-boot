package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.service.impl.UserAPIServiceImpl;
import com.rankofmatrix.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private UserAPIServiceImpl userAPIService;

    @Autowired
    public void setUserAPIServiceImpl(UserAPIServiceImpl userAPIService) {
        this.userAPIService = userAPIService;
    }

    @PostMapping(path = "/register")
    public @ResponseBody User registerUser (@RequestBody String nickname, @RequestBody String password, @RequestBody String email, @RequestBody String avatarUrl) {
        return userAPIService.registerUser(nickname, password, email, avatarUrl);
    }

    @GetMapping(path = "/")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userAPIService.getAllUsers();
    }

    @PostMapping(path = "/login")
    public @ResponseBody User loginUser(@RequestBody User login_user) {
        return userAPIService.loginUser(login_user);
    }
}
