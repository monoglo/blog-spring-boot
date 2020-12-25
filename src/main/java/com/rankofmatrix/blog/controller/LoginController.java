package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"System API"})
@SwaggerDefinition(tags = {
        @Tag(name = "System API", description = "RESTful System API")
})
public class LoginController {

    @PostMapping(path = "/login/success")
    @ApiOperation("登陆成功返回")
    public JsonResponse success() {
        return new JsonResponse(200, "Login Success!", 0, null);
    }

    @PostMapping(path = "/login/failed")
    @ApiOperation("登陆失败返回")
    public JsonResponse failed() {
        return new JsonResponse(401, "Login Failed!", 0, null);
    }

    @GetMapping(path = "/logout/success")
    @ApiOperation("登出成功返回")
    public JsonResponse logoutSuccess() {
        return new JsonResponse(200, "Logout Success!", 0, null);
    }

}
