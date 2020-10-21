package com.rankofmatrix.blog.dto;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.model.dto.ArticleResponse;
import com.rankofmatrix.blog.service.impl.ArticleAPIServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleResponseTest {
    @Autowired
    private ArticleAPIServiceImpl articleAPIService;
    @Test
    public void articleExtendsTest() {
        Article article = articleAPIService.getArticleByAid(3);
        ArticleResponse articleResponse = articleAPIService.convertToArticleResponse(article);
        System.out.println(articleResponse.toString());
    }
    @Test
    public void articleResponseListTest() {
        List<Article> articleList = articleAPIService.getAllArticle();
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleList);
        System.out.println(articleResponseList.toString());
    }
}
