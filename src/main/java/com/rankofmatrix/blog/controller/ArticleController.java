package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.model.JsonResponse;
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

    // 获取所有文章(包含被删除)
    @GetMapping(path = "/")
    @ApiOperation("获取所有文章(包含被删除)")
    public @ResponseBody JsonResponse getAllArticle() {
        List<Article> resultArticles = articleAPIService.getAllArticle();
        if (resultArticles.size() > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultArticles);
        } else {
            return new JsonResponse(404, "Get no article", null);
        }
    }

    // 获取所有可见的文章
    @GetMapping(path = "/visible")
    @ApiOperation("获取所有可见的文章")
    public @ResponseBody JsonResponse getAllArticleVisible() {
        List<Article> resultArticles = articleAPIService.getAllArticleVisible();
        if (resultArticles.size() > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultArticles);
        } else {
            return new JsonResponse(404, "Get no article", null);
        }
    }

    // 获取所有被删除的文章
    @GetMapping(path = "/deleted")
    @ApiOperation("获取所有被删除的文章")
    public @ResponseBody JsonResponse getAllArticleDeleted() {
        List<Article> resultArticles = articleAPIService.getAllArticleDeleted();
        if (resultArticles.size() > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultArticles);
        } else {
            return new JsonResponse(404, "Get no article", null);
        }
    }

    // 获取某一ID的文章(包含被删除)
    @GetMapping(path = "/aid/{aid}")
    @ApiOperation("获取某一ID的文章(包含被删除)")
    @ApiImplicitParam(name = "aid", value = "文章ID", required = true, dataType = "Int")
    public @ResponseBody JsonResponse getArticleByAid(@PathVariable(value = "aid") Integer aid) {
        Article resultArticle = articleAPIService.getArticleByAid(aid);
        if (resultArticle != null) {
            return new JsonResponse(200, "Get article successfully", resultArticle);
        } else {
            return new JsonResponse(404, "Article not found", null);
        }
    }

    // 获取某一作者的所有文章 TODO (不带正文)
    @GetMapping(path = "/uid/{uid}")
    @ApiOperation("获取某一作者的所有文章(不带正文)")
    @ApiImplicitParam(name = "uid", value = "作者ID", required = true, dataType = "Int")
    public @ResponseBody JsonResponse getArticleWithoutTextByUid(@PathVariable(value = "uid") Integer uid) {
        List<Article> resultArticles = articleAPIService.getArticleWithoutTextByUid(uid);
        if (resultArticles.size() > 0) {
            return new JsonResponse(200, "Get author's all articles successfully", resultArticles);
        } else {
            return new JsonResponse(404, "Author has no article", null);
        }
    }

    // 检索标题中包含关键字的所有文章 TODO (不带正文)
    @GetMapping(path = "/titleKey/{titleKey}")
    @ApiOperation("检索标题中包含关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "titleKey", value = "标题检索关键词", required = true, dataType = "String")
    public @ResponseBody JsonResponse selectArticleWithoutTextByTitleKey(@PathVariable(value = "titleKey") String titleKey) {
        List<Article> resultArticles = articleAPIService.selectArticleWithoutTextByTitleKey(titleKey);
        if (resultArticles.size() > 0) {
            return new JsonResponse(200, "Search articles successfully", resultArticles);
        } else {
            return new JsonResponse(404, "Search has not found", null);
        }
    }

    // 检索模糊搜索带有关键字的所有文章 TODO (不带正文)
    @GetMapping(path = "/key/{key}")
    @ApiOperation("检索模糊搜索带有关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "key", value = "全文检索关键词", required = true, dataType = "String")
    public @ResponseBody JsonResponse selectArticleWithoutTextByKey(@PathVariable(value = "key") String key) {
        List<Article> resultArticles = articleAPIService.selectArticleWithoutTextByKey(key);
        if (resultArticles.size() > 0) {
            return new JsonResponse(200, "Search articles successfully", resultArticles);
        } else {
            return new JsonResponse(404, "Search has not found", null);
        }
    }

    // 创建新的文章
    @PostMapping(path = "/")
    @ApiOperation("创建新的文章")
    @ApiImplicitParam(name = "createArticle", value = "将要创建的文章信息(标题必要)", required = true, dataType = "Article")
    public @ResponseBody JsonResponse createArticleByArticle(@RequestBody Article createArticle) {
        Article createdArticle = articleAPIService.createArticleByArticle(createArticle);
        if (createdArticle != null) {
            return new JsonResponse(201, "Create article successfully", createdArticle);
        } else {
            return new JsonResponse(403, "Create article Failed", null);
        }
    }

    // 修改某一ID的文章(包含被删除)
    @PutMapping(path = "/")
    @ApiOperation("修改某一ID的文章(包含被删除)")
    @ApiImplicitParam(name = "updateArticle", value = "将要修改的文章信息", required = true, dataType = "Article")
    public @ResponseBody JsonResponse modifyArticleByArticle(@RequestBody Article updateArticle) {
        Article modifiedArticle = articleAPIService.modifyArticleByArticle(updateArticle);
        if (modifiedArticle != null) {
            return new JsonResponse(200, "Update article successfully", modifiedArticle);
        } else {
            return new JsonResponse(403, "Update article Failed", null);
        }
    }

    // TODO 添加标签到某一文章 API
    @PutMapping(path = "/add/tag/archiveId")
    public @ResponseBody JsonResponse addTagToArticleByAidAndTagId() {
        return null;
    }

    // TODO 添加归档到某一文章 API
    @PutMapping(path = "/add/archive/archiveId")
    public @ResponseBody JsonResponse addArchiveToArticleByAidAndArchiveId() {
        return null;
    }

    // 删除某一ID的文章
    @ApiOperation("删除某一ID的文章")
    @ApiImplicitParam(name = "aid", value = "将要删除的文章ID", required = true, dataType = "Int")
    @DeleteMapping(path = "/aid/{aid}")
    public @ResponseBody JsonResponse deleteArticleByAid(@PathVariable(value = "aid") Integer aid) {
        Boolean result = articleAPIService.deleteArticleByAid(aid);
        if (result) {
            return new JsonResponse(200, "Delete article successfully", null);
        } else {
            return new JsonResponse(403, "Delete article Failed, article may not exists", null);
        }
    }

}
