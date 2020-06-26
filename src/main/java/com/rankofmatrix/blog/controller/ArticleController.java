package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.service.impl.ArticleAPIServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/articles")
@Api(tags = {"Article API"})
@SwaggerDefinition(tags = {
        @Tag(name = "Article API", description = "RESTful Article API")
})
public class ArticleController {

    private ArticleAPIServiceImpl articleAPIService;

    @Autowired
    public void setArticleAPIService(ArticleAPIServiceImpl articleAPIService) {
        this.articleAPIService = articleAPIService;
    }

    // 获取所有文章
    @GetMapping(path = "/")
    @ApiOperation("获取所有文章")
    public @ResponseBody
    List<Article> getAllArticle() {
        return articleAPIService.getAllArticle();
    }

    // 获取所有可见的文章
    @GetMapping(path = "/visible")
    @ApiOperation("获取所有可见的文章")
    public @ResponseBody List<Article> getAllArticleVisible() {
        return articleAPIService.getAllArticleVisible();
    }

    // 获取所有被删除的文章
    @GetMapping(path = "/deleted")
    @ApiOperation("获取所有被删除的文章")
    public @ResponseBody List<Article> getAllArticleDeleted() {
        return articleAPIService.getAllArticleDeleted();
    }

    // 获取某一ID的文章
    @GetMapping(path = "/aid/{aid}")
    @ApiOperation("获取某一ID的文章")
    @ApiImplicitParam(name = "aid", value = "文章ID", required = true, dataType = "Integer")
    public @ResponseBody Article getArticleByAid(@PathVariable(value = "aid") Integer aid) {
        return articleAPIService.getArticleByAid(aid);
    }

    // 获取某一作者的所有文章 TODO (不带正文)
    @GetMapping(path = "/uid/{uid}")
    @ApiOperation("获取某一作者的所有文章(不带正文)")
    @ApiImplicitParam(name = "uid", value = "作者ID", required = true, dataType = "Integer")
    public @ResponseBody List<Article> getArticleWithoutTextByUid(@PathVariable(value = "uid") Integer uid) {
        return articleAPIService.getArticleWithoutTextByUid(uid);
    }

    // 检索标题中包含关键字的所有文章 TODO (不带正文)
    @GetMapping(path = "/titleKey/{titleKey}")
    @ApiOperation("检索标题中包含关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "titleKey", value = "标题检索关键词", required = true, dataType = "String")
    public @ResponseBody List<Article> selectArticleWithoutTextByTitleKey(@PathVariable(value = "titleKey") String titleKey) {
        return articleAPIService.selectArticleWithoutTextByTitleKey(titleKey);
    }

    // 检索模糊搜索带有关键字的所有文章 TODO (不带正文)
    @GetMapping(path = "/key/{key}")
    @ApiOperation("检索模糊搜索带有关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "key", value = "全文检索关键词", required = true, dataType = "String")
    public @ResponseBody List<Article> selectArticleWithoutTextByKey(@PathVariable(value = "key") String key) {
        return articleAPIService.selectArticleWithoutTextByKey(key);
    }

    // 创建新的文章
    @PostMapping(path = "/")
    @ApiOperation("创建新的文章")
    @ApiImplicitParam(name = "createArticle", value = "将要创建的文章信息(标题必要)", required = true, dataType = "Article")
    public @ResponseBody Article createArticleByArticle(@RequestBody Article createArticle) {
        return articleAPIService.createArticleByArticle(createArticle);
    }

    // 修改某一ID的文章
    @PutMapping(path = "/")
    @ApiOperation("修改某一ID的文章")
    @ApiImplicitParam(name = "updateArticle", value = "将要修改的文章信息", required = true, dataType = "Article")
    public @ResponseBody Article modifyArticleByArticle(@RequestBody Article updateArticle) {
        return articleAPIService.modifyArticleByArticle(updateArticle);
    }

    // TODO 添加标签到某一文章 API
    @PutMapping(path = "/add/tag/archiveId")
    public @ResponseBody Boolean addTagToArticleByAidAndTagId() {
        return null;
    }

    // TODO 添加归档到某一文章 API
    @PutMapping(path = "/add/archive/archiveId")
    public @ResponseBody Boolean addArchiveToArticleByAidAndArchiveId() {
        return null;
    }

    // 删除某一ID的文章
    @ApiOperation("删除某一ID的文章")
    @ApiImplicitParam(name = "aid", value = "将要删除的文章ID", required = true, dataType = "Integer")
    @DeleteMapping(path = "/aid/{aid}")
    public @ResponseBody Boolean deleteArticleByAid(@PathVariable(value = "aid") Integer aid) {
        return articleAPIService.deleteArticleByAid(aid);
    }

}
