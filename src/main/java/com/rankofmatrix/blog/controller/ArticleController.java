package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.service.impl.ArticleAPIServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/articles")
public class ArticleController {

    private ArticleAPIServiceImpl articleAPIService;

    @Autowired
    public void setArticleAPIService(ArticleAPIServiceImpl articleAPIService) {
        this.articleAPIService = articleAPIService;
    }

    // 获取所有文章
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Article> getAllArticle() {
        return articleAPIService.getAllArticle();
    }

    // 获取所有可见的文章
    @GetMapping(path = "/all/visible")
    public @ResponseBody Iterable<Article> getAllArticleVisible() {
        return articleAPIService.getAllArticleVisible();
    }

    // 获取所有被删除的文章
    @GetMapping(path = "/all/deleted")
    public @ResponseBody Iterable<Article> getAllArticleDeleted() {
        return articleAPIService.getAllArticleDeleted();
    }

    // 获取某一ID的文章
    @GetMapping(path = "/aid/{aid}")
    public @ResponseBody Article getArticleByAid(@PathVariable(value = "aid") Integer aid) {
        return articleAPIService.getArticleByAid(aid);
    }

    // 获取某一作者的所有文章(不带正文)
    @GetMapping(path = "/uid/{uid}")
    public Iterable<Article> getArticleWithoutTextByUid(@PathVariable(value = "uid") Integer uid) {
        return articleAPIService.getArticleWithoutTextByUid(uid);
    }

    // 检索标题中包含关键字的所有文章(不带正文)
    @GetMapping(path = "/titleKey/{titleKey}")
    public Iterable<Article> selectArticleWithoutTextByTitleKey(@PathVariable(value = "titleKey") String titleKey) {
        return articleAPIService.selectArticleWithoutTextByTitleKey(titleKey);
    }

    // 检索模糊搜索带有关键字的所有文章(不带正文)
    @GetMapping(path = "/key/{key}")
    public Iterable<Article> selectArticleWithoutTextByKey(@PathVariable(value = "key") String key) {
        return articleAPIService.selectArticleWithoutTextByKey(key);
    }

    // 创建新的文章
    @PostMapping(path = "")
    public Article createArticleByArticle(@RequestBody Article article) {
        return articleAPIService.createArticleByArticle(article);
    }

    // 修改某一文章
    @PutMapping(path = "")
    public Article modifyArticleByArticle(@RequestBody Article article) {
        return articleAPIService.modifyArticleByArticle(article);
    }

    // TODO 添加标签到某一文章 API
    @PutMapping(path = "/add/tag/archiveId")
    public Boolean addTagToArticleByAidAndTagId() {
        return null;
    }

    // TODO 添加归档到某一文章 API
    @PutMapping(path = "/add/archive/archiveId")
    public Boolean addArchiveToArticleByAidAndArchiveId() {
        return null;
    }

    // 删除某一ID的文章
    @DeleteMapping(path = "/aid/{aid}")
    public Boolean deleteArticleByAid(@PathVariable(value = "aid") Integer aid) {
        return articleAPIService.deleteArticleByAid(aid);
    }

}
