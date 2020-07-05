package com.rankofmatrix.blog.service.impl;

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
    public Article getArticleByAid(Integer aid) {
        return articleRepository.findArticleByAid(aid);
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
    public List<Article> selectArticleWithoutTextByKey(String key) {
        return articleRepository.findArticlesByStatusAndTitleContainsOrStatusAndTextContains(0, key, 0, key);
    }

    @Override
    public Article createArticleByArticle(Article newArticle) {
        return articleRepository.save(newArticle);
    }

    @Override
    public Article modifyArticleByArticle(Article modifiedArticle) {
        return articleRepository.save(modifiedArticle);
    }

    @Override
    public Boolean addTagToArticleByAidAndTagId(Integer aid, Integer tagId) {
        TagAndArticle newTagAndArticle = new TagAndArticle();
        newTagAndArticle.setArticleId(aid);
        newTagAndArticle.setTagId(tagId);
        tagAndArticleRepository.save(newTagAndArticle);
        if (newTagAndArticle.getTagArticleId() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean addArchiveToArticleByAidAndArchiveId(Integer aid, Integer archiveId) {
        ArchiveAndArticle newArchiveAndArticle = new ArchiveAndArticle();
        newArchiveAndArticle.setArticleId(aid);
        newArchiveAndArticle.setArchiveId(archiveId);
        archiveAndArticleRepository.save(newArchiveAndArticle);
        if (newArchiveAndArticle.getArchiveArticleId() != null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
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
}
