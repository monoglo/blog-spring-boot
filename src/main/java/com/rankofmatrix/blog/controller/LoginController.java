package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.dto.JsonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"System API"})
@RequestMapping(path = "/login")
@SwaggerDefinition(tags = {
        @Tag(name = "System API", description = "RESTful System API")
})
public class LoginController {

    @PostMapping(path = "/success")
    @ApiOperation("登陆成功返回")
    public JsonResponse success() {
        return new JsonResponse(200, "Success!", 0, null);
    }

    @PostMapping(path = "/failed")
    @ApiOperation("登陆失败返回")
    public JsonResponse failed() {
        return new JsonResponse(401, "Failed!", 0, null);
    }

}
