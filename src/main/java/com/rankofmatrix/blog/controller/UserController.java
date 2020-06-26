package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.service.impl.UserAPIServiceImpl;
import com.rankofmatrix.blog.model.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/users")
@Api(tags = {"User API"})
@SwaggerDefinition(tags = {
        @Tag(name = "User API", description = "RESTful User API")
})
public class UserController {

    private UserAPIServiceImpl userAPIService;

    @Autowired
    public void setUserAPIServiceImpl(UserAPIServiceImpl userAPIService) {
        this.userAPIService = userAPIService;
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParam(name = "registerUser", value = "将要注册的用户信息（邮箱、密码必要）", required = true, dataType = "User")
    @PostMapping(path = "/register")
    public @ResponseBody User registerUser (@RequestBody User registerUser) {
        return userAPIService.registerUser(registerUser);
    }

    @ApiOperation("获取所有用户")
    @GetMapping(path = "/")
    public @ResponseBody
    List<User> getAllUsers() {
        return userAPIService.getAllUsers();
    }

    @ApiOperation("用户登陆接口")
    @PostMapping(path = "/login")
    @ApiImplicitParam(name = "loginUser", value = "将要登陆的用户信息（邮箱、密码必要）", required = true, dataType = "User")
    public @ResponseBody User loginUser(@RequestBody User loginUser) {
        return userAPIService.loginUser(loginUser);
    }
}
