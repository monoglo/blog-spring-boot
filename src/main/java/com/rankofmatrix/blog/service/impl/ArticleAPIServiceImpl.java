package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.repository.ArticleRepository;
import com.rankofmatrix.blog.service.ArticleAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleAPIServiceImpl implements ArticleAPIService {

    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Iterable<Article> getAllArticle() {
        return articleRepository.findAll();
    }

    @Override
    public Iterable<Article> getAllArticleVisible() {
        return null;
    }

    @Override
    public Iterable<Article> getAllArticleDeleted() {
        return null;
    }

    @Override
    public Article getArticleByAid(Integer aid) {
        return null;
    }

    @Override
    public Iterable<Article> getArticleWithoutTextByUid(Integer uid) {
        return null;
    }

//    @Override
//    public Iterable<Article> getArticleWithoutTextByTagID(Integer tagId) {
//        return null;
//    }
//
//    @Override
//    public Iterable<Article> getArticleWithoutTextByArchiveID(Integer archiveId) {
//        return null;
//    }

    @Override
    public Iterable<Article> selectArticleWithoutTextByTitleKey(String titleKey) {
        return null;
    }

    @Override
    public Iterable<Article> selectArticleWithoutTextByKey(String Key) {
        return null;
    }

    @Override
    public Article createArticleByArticle(Article newArticle) {
        return null;
    }

    @Override
    public Article modifyArticleByArticle(Article modifiedArticle) {
        return null;
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
        return null;
    }
}
