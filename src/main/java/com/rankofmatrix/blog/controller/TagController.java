package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.dto.JsonResponse;
import com.rankofmatrix.blog.model.Tag;
import com.rankofmatrix.blog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/tags")
@Api(tags = {"Tags API"})
@SwaggerDefinition(tags = {
        @io.swagger.annotations.Tag(name = "Tag API", description = "RESTful Tag API")
})
public class TagController {

    private TagService tagService;

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    // 获取所有标签
    @GetMapping(path = "/")
    @ApiOperation("获取所有标签")
    public JsonResponse getAllTags() {
        List<Tag> resultTags = tagService.getAllTags();
        int resultLength = resultTags.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all tags successfully", resultLength, resultTags);
        } else {
            return new JsonResponse(404, "Get no tag", 0, null);
        }
    }

    // 获取某一TagID的标签
    @GetMapping(path = "/id/{id}")
    @ApiOperation("获取指定TagID的标签")
    @ApiImplicitParam(name = "id", value = "要获取标签的标签ID", required = true, dataType = "Int")
    public JsonResponse getTagByTagId(@PathVariable(value = "id") Integer tagId) {
        Tag resultTag = tagService.getTagByTagId(tagId);
        if (resultTag != null) {
            return new JsonResponse(200, "Get tag successfully", 1, resultTag);
        } else {
            return new JsonResponse(404, "Get tag failed", 0, null);
        }
    }

    // 获取某一TagName的标签
    @GetMapping(path = "/name/{name}")
    @ApiOperation("获取指定TagName的标签")
    @ApiImplicitParam(name = "name", value = "要获取标签的标签Name", required = true, dataType = "String")
    public JsonResponse getTagByTagName(@PathVariable(value = "name") String tagName) {
        Tag resultTag = tagService.getTagByTagName(tagName);
        if (resultTag != null) {
            return new JsonResponse(200, "Get tag successfully", 1, resultTag);
        } else {
            return new JsonResponse(404, "Get tag failed", 0, null);
        }
    }

    // 模糊搜索TagName中包含关键字的所有标签
    @GetMapping(path = "/nameKey/{nameKey}")
    @ApiOperation("模糊搜索TagName中包含关键字的所有标签")
    @ApiImplicitParam(name = "nameKey", value = "要检索标签的检索Name关键字", required = true, dataType = "String")
    public JsonResponse selectTagsByTagNameKey(@PathVariable(value = "nameKey") String nameKey) {
        List<Tag> resultTags = tagService.selectTagsByTagNameKey(nameKey);
        int resultLength = resultTags.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Search tags successfully", resultLength, resultTags);
        } else {
            return new JsonResponse(404, "Search has no found", 0, null);
        }
    }

    // 获取某一AID的文章的所有标签
    @GetMapping(path = "/aid/{aid}")
    @ApiOperation("获取某一AID的文章的所有标签")
    @ApiImplicitParam(name = "aid", value = "文章的ID", required = true, dataType = "int")
    public JsonResponse selectTagsByAid(@PathVariable(value = "aid")Integer aid) {
        List<Tag> resultTags = tagService.getTagsByAid(aid);
        int resultLength = resultTags.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get tags by aid successfully", resultLength, resultTags);
        } else {
            return new JsonResponse(404, "Get no tags", 0, null);
        }
    }


    // 创建新的标签
    @PostMapping(path = "/")
    @ApiOperation("创建新的标签")
    @ApiImplicitParam(name = "newTag", value = "将要创建标签的信息", required = true, dataType = "Tag")
    @RequiresAuthentication
    public JsonResponse createTagByTag(@RequestBody Tag newTag) {
        Tag resultTag = tagService.createTagByTag(newTag);
        if (resultTag != null) {
            return new JsonResponse(200, "Create tag successfully", 1, resultTag);
        } else {
            return new JsonResponse(403, "Create tag failed", 0, null);
        }
    }

    // 修改某一标签
    @PutMapping(path = "/")
    @ApiOperation("修改某一标签")
    @ApiImplicitParam(name = "modifiedTag", value = "将要修改标签的信息", required = true, dataType = "Tag")
    @RequiresAuthentication
    public JsonResponse updateTagByTag(@RequestBody Tag modifiedTag) {
        Tag resultTag = tagService.modifyTagByTag(modifiedTag);
        if (resultTag != null) {
            return new JsonResponse(200, "Update tag successfully", 1, resultTag);
        } else {
            return new JsonResponse(403, "Update tag failed", 0, null);
        }
    }

    // 删除某一TagID的标签
    @DeleteMapping(path = "/id/{id}")
    @Transactional
    @ApiOperation("删除某一TagID的标签")
    @ApiImplicitParam(name = "id", value = "将要删除标签的ID", required = true, dataType = "Int")
    @RequiresAuthentication
    public JsonResponse deleteTagByTagId(@PathVariable(value = "id") Integer tagId) {
        Integer result = tagService.deleteTagByTagId(tagId);
        if (result == 1) {
            return new JsonResponse(200, "Delete tag successfully", 0, null);
        } else {
            return new JsonResponse(403, "Delete tag Failed, tag may not exists", 0, null);
        }
    }

}
