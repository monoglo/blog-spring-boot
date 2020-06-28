package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.model.JsonResponse;
import com.rankofmatrix.blog.service.impl.ArticleAPIServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public JsonResponse getAllArticle() {
        List<Article> resultArticles = articleAPIService.getAllArticle();
        int resultLength = resultArticles.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultLength, resultArticles);
        } else {
            return new JsonResponse(404, "Get no article", 0, null);
        }
    }

    // 获取所有可见的文章
    @GetMapping(path = "/visible")
    @ApiOperation("获取所有可见的文章")
    public JsonResponse getAllArticleVisible() {
        List<Article> resultArticles = articleAPIService.getAllArticleVisible();
        int resultLength = resultArticles.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultLength, resultArticles);
        } else {
            return new JsonResponse(404, "Get no article", 0, null);
        }
    }

    // 获取所有被删除的文章
    @GetMapping(path = "/deleted")
    @ApiOperation("获取所有被删除的文章")
    public JsonResponse getAllArticleDeleted() {
        List<Article> resultArticles = articleAPIService.getAllArticleDeleted();
        int resultLength = resultArticles.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultLength, resultArticles);
        } else {
            return new JsonResponse(404, "Get no article", 0, null);
        }
    }

    // 获取某一ID的文章(包含被删除)
    @GetMapping(path = "/aid/{aid}")
    @ApiOperation("获取某一ID的文章(包含被删除)")
    @ApiImplicitParam(name = "aid", value = "文章ID", required = true, dataType = "Int")
    public JsonResponse getArticleByAid(@PathVariable(value = "aid") Integer aid) {
        Article resultArticle = articleAPIService.getArticleByAid(aid);
        if (resultArticle != null) {
            return new JsonResponse(200, "Get article successfully", 1, resultArticle);
        } else {
            return new JsonResponse(404, "Article not found", 0, null);
        }
    }

    // 获取某一作者的所有文章 TODO (不带正文)
    @GetMapping(path = "/uid/{uid}")
    @ApiOperation("获取某一作者的所有文章(不带正文)")
    @ApiImplicitParam(name = "uid", value = "作者ID", required = true, dataType = "Int")
    public JsonResponse getArticleWithoutTextByUid(@PathVariable(value = "uid") Integer uid) {
        List<Article> resultArticles = articleAPIService.getArticleWithoutTextByUid(uid);
        int resultLength = resultArticles.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get author's all articles successfully", resultLength, resultArticles);
        } else {
            return new JsonResponse(404, "Author has no article", 0, null);
        }
    }

    // 检索标题中包含关键字的所有文章 TODO (不带正文)
    @GetMapping(path = "/titleKey/{titleKey}")
    @ApiOperation("检索标题中包含关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "titleKey", value = "标题检索关键词", required = true, dataType = "String")
    public JsonResponse selectArticleWithoutTextByTitleKey(@PathVariable(value = "titleKey") String titleKey) {
        List<Article> resultArticles = articleAPIService.selectArticleWithoutTextByTitleKey(titleKey);
        int resultLength = resultArticles.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Search articles successfully", resultLength, resultArticles);
        } else {
            return new JsonResponse(404, "Search has not found", 0, null);
        }
    }

    // 检索模糊搜索带有关键字的所有文章 TODO (不带正文)
    @GetMapping(path = "/key/{key}")
    @ApiOperation("检索模糊搜索带有关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "key", value = "全文检索关键词", required = true, dataType = "String")
    public JsonResponse selectArticleWithoutTextByKey(@PathVariable(value = "key") String key) {
        List<Article> resultArticles = articleAPIService.selectArticleWithoutTextByKey(key);
        int resultLength = resultArticles.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Search articles successfully", resultLength, resultArticles);
        } else {
            return new JsonResponse(404, "Search has not found", 0, null);
        }
    }

    // TODO 查看含有某一标签的所有文章

    // TODO 查看某一归档内的所有文章

    // 创建新的文章
    @PostMapping(path = "/")
    @ApiOperation("创建新的文章")
    @ApiImplicitParam(name = "createArticle", value = "将要创建的文章信息(标题必要)", required = true, dataType = "Article")
    public JsonResponse createArticleByArticle(@RequestBody Article createArticle) {
        Article createdArticle = articleAPIService.createArticleByArticle(createArticle);
        if (createdArticle != null) {
            return new JsonResponse(201, "Create article successfully", 1, createdArticle);
        } else {
            return new JsonResponse(403, "Create article Failed", 0, null);
        }
    }

    // 修改某一文章(包含被删除)
    @PutMapping(path = "/")
    @ApiOperation("修改文章(包含被删除)")
    @ApiImplicitParam(name = "updateArticle", value = "将要修改的文章信息", required = true, dataType = "Article")
    public JsonResponse modifyArticleByArticle(@RequestBody Article updateArticle) {
        Article modifiedArticle = articleAPIService.modifyArticleByArticle(updateArticle);
        if (modifiedArticle != null) {
            return new JsonResponse(200, "Update article successfully", 1, modifiedArticle);
        } else {
            return new JsonResponse(403, "Update article Failed", 0, null);
        }
    }

    // 添加标签到某一文章
    @PutMapping(path = "/aid/{aid}/add/tag/{tagId}")
    public JsonResponse addTagToArticleByAidAndTagId(@PathVariable(value = "aid") Integer aid, @PathVariable(value = "tagId") Integer tagId) {
        Boolean result = articleAPIService.addTagToArticleByAidAndTagId(aid, tagId);
        if (result) {
            return new JsonResponse(200, "Add Tag successfully", 0, null);
        } else {
            return new JsonResponse(403, "Add Tag Failed", 0, null);
        }
    }

    // 添加归档到某一文章
    @PutMapping(path = "aid/{aid}/add/archive/{archiveId}")
    public JsonResponse addArchiveToArticleByAidAndArchiveId(@PathVariable(value = "aid")Integer aid, @PathVariable(value = "archiveId") Integer archiveId)  {
        Boolean result = articleAPIService.addArchiveToArticleByAidAndArchiveId(aid, archiveId);
        if (result) {
            return new JsonResponse(200, "Add Archive successfully", 0, null);
        } else {
            return new JsonResponse(403, "Add Archive failed", 0, null);
        }
    }

    // 删除某一ID的文章
    @ApiOperation("删除某一ID的文章")
    @ApiImplicitParam(name = "aid", value = "将要删除的文章ID", required = true, dataType = "Int")
    @DeleteMapping(path = "/aid/{aid}")
    public JsonResponse deleteArticleByAid(@PathVariable(value = "aid") Integer aid) {
        Boolean result = articleAPIService.deleteArticleByAid(aid);
        if (result) {
            return new JsonResponse(200, "Delete article successfully", 0, null);
        } else {
            return new JsonResponse(403, "Delete article Failed, article may not exists", 0, null);
        }
    }

}
