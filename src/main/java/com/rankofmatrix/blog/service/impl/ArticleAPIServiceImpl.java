package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.exception.ArticleDoesNotExistException;
import com.rankofmatrix.blog.exception.ArticleIsHiddenException;
import com.rankofmatrix.blog.model.ArchiveAndArticle;
import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.model.TagAndArticle;
import com.rankofmatrix.blog.repository.ArchiveAndArticleRepository;
import com.rankofmatrix.blog.repository.ArticleRepository;
import com.rankofmatrix.blog.repository.TagAndArticleRepository;
import com.rankofmatrix.blog.service.ArticleAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleAPIServiceImpl implements ArticleAPIService {

    private ArticleRepository articleRepository;
    private TagAndArticleRepository tagAndArticleRepository;
    private ArchiveAndArticleRepository archiveAndArticleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setTagAndArticleRepository(TagAndArticleRepository tagAndArticleRepository) {
        this.tagAndArticleRepository = tagAndArticleRepository;
    }

    @Autowired
    public void setArchiveAndArticle(ArchiveAndArticleRepository archiveAndArticleRepository) {
        this.archiveAndArticleRepository = archiveAndArticleRepository;
    }

    @Override
    public List<Article> getAllArticle() {
        return Lists.newArrayList(articleRepository.findAll());
    }

    @Override
    public List<Article> getAllArticleVisible() {
        return articleRepository.findArticlesByStatus(0);
    }

    @Override
    public List<Article> getAllArticleDeleted() {
        return articleRepository.findArticlesByStatus(1);
    }

    @Override
    public Article getArticleByAid(Integer aid) throws ArticleDoesNotExistException {
        Article article = articleRepository.findArticleByAid(aid);
        if (article != null) {
            return articleRepository.findArticleByAid(aid);
        } else {
            throw new ArticleDoesNotExistException();
        }

    }

    @Override
    public List<Article> getArticleWithoutTextByUid(Integer uid) {
        List<Article> resultArticles = articleRepository.findArticlesByAuthorIdAndStatus(uid, 0);
        for (Article article : resultArticles) {
            article.setText(null);
        }
        return resultArticles;
    }

    @Override
    public List<Article> getArticleWithoutTextByTagID(Integer tagId) {
        // 得到Tag和Article的关联记录
        List<TagAndArticle> resultTagAndArticles = tagAndArticleRepository.findTagAndArticlesByTagId(tagId);
        // 初始化List
        List<Article> resultArticles = new LinkedList<>();
        // 遍历resultTagAndArticles生成resultArticles
        for (TagAndArticle tagAndArticle : resultTagAndArticles) {
            Integer articleId = tagAndArticle.getArticleId();
            resultArticles.add(articleRepository.findArticleByAid(articleId));
        }
        for (Article article : resultArticles) {
            article.setText(null);
        }
        return resultArticles;
    }

    @Override
    public List<Article> getArticleWithoutTextByArchiveID(Integer archiveId) {
        List<ArchiveAndArticle> resultArchiveAndArticles = archiveAndArticleRepository.findArchiveAndArticlesByArchiveId(archiveId);
        List<Article> resultArticles = new LinkedList<>();
        for (ArchiveAndArticle archiveAndArticle : resultArchiveAndArticles) {
            Integer articleId = archiveAndArticle.getArticleId();
            resultArticles.add(articleRepository.findArticleByAid(articleId));
        }
        for (Article article : resultArticles) {
            article.setText(null);
        }
        return resultArticles;
    }

    @Override
    public List<Article> selectArticleWithoutTextByTitleKey(String titleKey) {
        List<Article> resultArticles = articleRepository.findArticlesByTitleContainsAndStatus(titleKey, 0);
        for (Article article : resultArticles) {
            article.setText(null);
        }
        return resultArticles;
    }

    @Override
    public List<Article> selectArticleByKey(String key) {
        return articleRepository.findArticlesByStatusAndTitleContainsOrStatusAndTextContains(0, key, 0, key);
    }

    @Override
    public Article createArticleByArticle(Article newArticle) {
        // 过滤
        Article checkedArticle = new Article();
        checkedArticle.setTitle(newArticle.getTitle());
        checkedArticle.setText(newArticle.getText());
        checkedArticle.setAuthorId(newArticle.getAuthorId());
        return articleRepository.save(checkedArticle);
    }

    @Override
    public Article modifyArticleByArticle(Article modifiedArticle) {
        Article checkedArticle = articleRepository.findArticleByAid(modifiedArticle.getAid());
        checkedArticle.setText(modifiedArticle.getText());
        checkedArticle.setLastEditTime(new Timestamp(System.currentTimeMillis()));
        return articleRepository.save(checkedArticle);
    }

    @Override
    public Boolean addTagToArticleByAidAndTagId(Integer aid, Integer tagId) {
        TagAndArticle newTagAndArticle = new TagAndArticle();
        newTagAndArticle.setArticleId(aid);
        newTagAndArticle.setTagId(tagId);
        tagAndArticleRepository.save(newTagAndArticle);
        return newTagAndArticle.getTagArticleId() != null;
    }

    @Override
    public Boolean addArchiveToArticleByAidAndArchiveId(Integer aid, Integer archiveId) {
        ArchiveAndArticle newArchiveAndArticle = new ArchiveAndArticle();
        newArchiveAndArticle.setArticleId(aid);
        newArchiveAndArticle.setArchiveId(archiveId);
        archiveAndArticleRepository.save(newArchiveAndArticle);
        return newArchiveAndArticle.getArchiveArticleId() != null;
    }


    @Override
    public Boolean deleteArticleByAid(Integer aid) {
        Article deleted_article = articleRepository.findArticleByAid(aid);
        if (deleted_article.getStatus() == 0) {
            deleted_article.setStatus(1);
            articleRepository.save(deleted_article);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean hasArticleTitleAndAuthorId(Article article) throws IllegalArgumentException {
        if (article.getTitle() != null && article.getAuthorId() != null) {
            return Boolean.TRUE;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Boolean hasArticleTitleAndTextAndAid(Article article) throws IllegalArgumentException {
        if (article.getText() != null && article.getAid() != null && article.getTitle() != null) {
            return Boolean.TRUE;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer increaseArticleClickAmount(Integer aid, Integer increaseAmount) throws ArticleDoesNotExistException, ArticleIsHiddenException {
        Article article = articleRepository.findArticleByAid(aid);
        if (article != null) {
            if (article.getStatus() == 0) {
                article.setClickAmount(article.getClickAmount() + increaseAmount);
                articleRepository.save(article);
                return article.getClickAmount();
            } else {
                throw new ArticleIsHiddenException();
            }
        } else {
            throw new ArticleDoesNotExistException();
        }
    }

    @Override
    public Integer increaseArticleClickAmount(Integer aid) throws ArticleDoesNotExistException, ArticleIsHiddenException {
        Article article = articleRepository.findArticleByAid(aid);
        if (article != null) {
            if (article.getStatus() == 0) {
                article.setClickAmount(article.getClickAmount() + 1);
                articleRepository.save(article);
                return article.getClickAmount();
            } else {
                throw new ArticleIsHiddenException();
            }
        } else {
            throw new ArticleDoesNotExistException();
        }
    }

}
