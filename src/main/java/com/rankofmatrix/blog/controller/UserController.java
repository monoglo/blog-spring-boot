package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.exception.RepeatLoginException;
import com.rankofmatrix.blog.service.UserAPIService;
import com.rankofmatrix.blog.model.User;
import com.rankofmatrix.blog.model.dto.JsonResponse;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(path = "/users")
@Api(tags = {"User API"})
@SwaggerDefinition(tags = {
        @Tag(name = "User API", description = "RESTful User API")
})
public class UserController {

    private UserAPIService userAPIService;

    @Autowired
    public void setUserAPIServiceImpl(UserAPIService userAPIService) {
        this.userAPIService = userAPIService;
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParam(name = "registerUser", value = "将要注册的用户信息（邮箱、密码必要）", required = true, dataType = "User")
    @PostMapping(path = "/register")
    @RequiresAuthentication
    public JsonResponse registerUser (@RequestBody User registerUser) {
        if (userAPIService.isLoginUserLegal(registerUser)) {
            User resultUser = userAPIService.registerUser(registerUser);
            if (resultUser != null) {
                return new JsonResponse(201, "Register successfully", 1, resultUser);
            } else {
                return new JsonResponse(409, "Error: Email exists", 0, null);
            }
        } else {
            return new JsonResponse(403, "Error: Must give email and password", 0, null);
        }
    }

    @ApiOperation("获取所有用户")
    @GetMapping(path = "/")
    @RequiresAuthentication
    public JsonResponse getAllUsers() {
        List<User> resultUsers = userAPIService.getAllUsers();
        int resultLength = resultUsers.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all users successfully", resultLength, resultUsers);
        } else {
            return new JsonResponse(404, "Get no user", 0, null);
        }
    }

    @ApiOperation("用户登陆接口")
    @PostMapping(path = "/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "将要登陆的用户邮箱必要", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "将要登陆的用户密码必要", required = true, dataType = "String")
    })
    public JsonResponse loginUser(String username, String password) {
        try {
            User loginUser = new User();
            loginUser.setEmail(username);
            loginUser.setPassword(password);
            User resultUser = userAPIService.loginUser(loginUser);
            resultUser.setPassword("");
            return new JsonResponse(200, "Login successfully", 1, resultUser);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            return new JsonResponse(401, "Error: The email or password was not correct", 0, null);
        } catch (AuthenticationException e) {
            return new JsonResponse(401, "Error: Login failed", 0, null);
        } catch (RepeatLoginException e) {
            return new JsonResponse(401, "Error: You have login in", 0, null);
        }

    }

    @ApiOperation("用户登出接口")
    @GetMapping(path = "/logout")
    public JsonResponse logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityUtils.getSubject().logout();
//        HttpSession session = request.getSession(false);
//        if (request.isRequestedSessionIdValid() && session != null) {
//            session.invalidate();
//        }
//        Cookie[] cookies = request.getCookies();
//        for (Cookie cookie : cookies) {
//            cookie.setMaxAge(0);
//            cookie.setValue(null);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//        }
        return new JsonResponse(200, "Logout successfully", 0, null);
    }

    @ApiOperation("快速登陆接口")
    @GetMapping(path = "/login/fast")
    public JsonResponse loginUser() {
        User resultUser = (User) SecurityUtils.getSubject().getPrincipal();
        System.out.println(SecurityUtils.getSubject().isAuthenticated());
        if (resultUser != null) {
            return new JsonResponse(200, "Login successfully", 1, resultUser);
        } else {
            return new JsonResponse(401, "Error: The email or password was not correct", 0, null);
        }
    }
}
