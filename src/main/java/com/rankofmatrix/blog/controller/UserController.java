package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.service.impl.UserAPIServiceImpl;
import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.model.JsonResponse;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public JsonResponse registerUser (@RequestBody User registerUser) {
        User resultUser = userAPIService.registerUser(registerUser);
        if (resultUser != null) {
            return new JsonResponse(201, "Register successfully", resultUser);
        } else {
            return new JsonResponse(409, "Error: Email exists", null);
        }
    }

    @ApiOperation("获取所有用户")
    @GetMapping(path = "/")
    public JsonResponse getAllUsers() {
        List<User> resultUsers = userAPIService.getAllUsers();
        if (resultUsers.size() > 0) {
            return new JsonResponse(200, "Get all users successfully", resultUsers);
        } else {
            return new JsonResponse(404, "Get no user", resultUsers);
        }
    }

    @ApiOperation("用户登陆接口")
    @PostMapping(path = "/login")
    @ApiImplicitParam(name = "loginUser", value = "将要登陆的用户信息（邮箱、密码必要）", required = true, dataType = "User")
    public JsonResponse loginUser(@RequestBody User loginUser) {
        User resultUser = userAPIService.loginUser(loginUser);
        if (resultUser != null) {
            return new JsonResponse(200, "Login successfully", resultUser);
        } else {
            return new JsonResponse(401, "Error: The email or password was not correct", null);
        }
    }
}
