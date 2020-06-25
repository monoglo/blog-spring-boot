package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    // 获取处于某一状态所有文章
    Iterable<Article> getArticlesByStatus(Integer status);
    // 获取某一ID文章
    Article getArticleByAid(Integer aid);
    // 获取某一作者的处于某一状态的所有文章
    Iterable<Article> getArticlesByAuthorIdAndStatus(Integer authorId, Integer status);
    // 获取标题中包含关键词的处于某一状态的所有文章
    Iterable<Article> getArticlesByTitleContainsAndStatus(String titleKey, Integer status);
    // 获取标题或正文中包含关键词的处于某一状态的所有文章
    Iterable<Article> getArticlesByStatusAndTitleContainsOrStatusAndTextContains(Integer status1, String key1, Integer status2, String key2);
}
