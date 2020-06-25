package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.repository.ArticleRepository;
import com.rankofmatrix.blog.service.ArticleAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import java.util.List;

@Service
public class ArticleAPIServiceImpl implements ArticleAPIService {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticle() {
        return Lists.newArrayList(articleRepository.findAll());
    }

    @Override
    public List<Article> getAllArticleVisible() {
        return articleRepository.getArticlesByStatus(0);
    }

    @Override
    public List<Article> getAllArticleDeleted() {
        return articleRepository.getArticlesByStatus(1);
    }

    @Override
    public Article getArticleByAid(Integer aid) {
        return articleRepository.getArticleByAid(aid);
    }

    @Override
    public List<Article> getArticleWithoutTextByUid(Integer uid) {
        return articleRepository.getArticlesByAuthorIdAndStatus(uid, 0);
    }

//    @Override
//    public List<Article> getArticleWithoutTextByTagID(Integer tagId) {
//        return null;
//    }
//
//    @Override
//    public List<Article> getArticleWithoutTextByArchiveID(Integer archiveId) {
//        return null;
//    }

    @Override
    public List<Article> selectArticleWithoutTextByTitleKey(String titleKey) {
        return articleRepository.getArticlesByTitleContainsAndStatus(titleKey, 0);
    }

    @Override
    public List<Article> selectArticleWithoutTextByKey(String key) {
        return articleRepository.getArticlesByStatusAndTitleContainsOrStatusAndTextContains(0, key, 0, key);
    }

    @Override
    public Article createArticleByArticle(Article newArticle) {
        return articleRepository.save(newArticle);
    }

    @Override
    public Article modifyArticleByArticle(Article modifiedArticle) {
        return articleRepository.save(modifiedArticle);
    }

//    @Override
//    public Boolean addTagToArticleByAidAndTagId(Integer aid, Integer tagId) {
//        return null;
//    }
//
//    @Override
//    public Boolean addArchiveToArticleByAidAndArchiveId(Integer aid, Integer archiveId) {
//        return null;
//    }

    @Override
    public Boolean deleteArticleByAid(Integer aid) {
        Article deleted_article = articleRepository.getArticleByAid(aid);
        if (deleted_article.getStatus() == 0) {
            deleted_article.setStatus(1);
            articleRepository.save(deleted_article);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
