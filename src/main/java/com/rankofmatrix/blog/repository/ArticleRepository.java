package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Integer> {
    // 获取处于某一状态所有文章
    List<Article> findArticlesByStatus(Integer status);
    // 获取某一ID文章
    Article findArticleByAid(Integer aid);
    // 获取某一ID的可见文章
    Article findArticleByAidAndStatus(Integer aid, Integer status);
    // 获取某一作者的处于某一状态的所有文章
    List<Article> findArticlesByAuthorIdAndStatus(Integer authorId, Integer status);
    // 获取标题中包含关键词的处于某一状态的所有文章
    List<Article> findArticlesByTitleContainsAndStatus(String titleKey, Integer status);
    // 获取标题或正文中包含关键词的处于某一状态的所有文章
    List<Article> findArticlesByStatusAndTitleContainsOrStatusAndTextContains(Integer status1, String key1, Integer status2, String key2);
}
