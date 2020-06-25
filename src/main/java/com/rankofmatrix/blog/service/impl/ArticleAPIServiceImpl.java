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
        return articleRepository.getArticlesByStatus(0);
    }

    @Override
    public Iterable<Article> getAllArticleDeleted() {
        return articleRepository.getArticlesByStatus(1);
    }

    @Override
    public Article getArticleByAid(Integer aid) {
        return articleRepository.getArticleByAid(aid);
    }

    @Override
    public Iterable<Article> getArticleWithoutTextByUid(Integer uid) {
        return articleRepository.getArticlesByAuthorIdAndStatus(uid, 0);
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
        return articleRepository.getArticlesByTitleContainsAndStatus(titleKey, 0);
    }

    @Override
    public Iterable<Article> selectArticleWithoutTextByKey(String key) {
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
