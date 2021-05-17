package com.rankofmatrix.blog.service.impl;

import com.rankofmatrix.blog.exception.*;
import com.rankofmatrix.blog.model.*;
import com.rankofmatrix.blog.model.dto.ArticleResponse;
import com.rankofmatrix.blog.repository.*;
import com.rankofmatrix.blog.service.ArticleAPIService;
import com.rankofmatrix.blog.service.TagService;
import com.rankofmatrix.blog.service.UserAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleAPIServiceImpl implements ArticleAPIService {

    private ArticleRepository articleRepository;
    private ArchiveRepository archiveRepository;
    private TagRepository tagRepository;
    private TagAndArticleRepository tagAndArticleRepository;
    private ArchiveAndArticleRepository archiveAndArticleRepository;

    private UserAPIService userAPIService;
    @Autowired
    private TagService tagService;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setArchiveRepository(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Autowired
    public void setTagAndArticleRepository(TagAndArticleRepository tagAndArticleRepository) {
        this.tagAndArticleRepository = tagAndArticleRepository;
    }

    @Autowired
    public void setArchiveAndArticleRepository(ArchiveAndArticleRepository archiveAndArticleRepository) {
        this.archiveAndArticleRepository = archiveAndArticleRepository;
    }

    @Autowired
    public void setUserAPIService(UserAPIServiceImpl userAPIService) {
        this.userAPIService = userAPIService;
    }

    @Override
    public List<Article> getAllArticle() {
        return Lists.newArrayList(articleRepository.findAll());
    }

    @Override
    @Cacheable(value = "visibleArticles")
    public List<Article> getAllArticleVisible(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Article> resultArticles = articleRepository.findArticlesByStatusOrderByAidDesc(0, pageable);
        for (Article article : resultArticles) {
            article.setText(null);
        }
        return resultArticles;
    }

    @Override
    @Cacheable(value = "visibleArticlesCount")
    public Integer countAllArticleVisible() {
        return articleRepository.countByStatus(0);
    }

    @Override
    public List<Article> getAllArticleDeleted(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findArticlesByStatusOrderByAidDesc(1, pageable);
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
    @Cacheable(value = "tagIdArticles", key = "#tagId")
    public List<Article> getArticleWithoutTextByTagID(Integer tagId) {
        // 得到Tag和Article的关联记录
        List<TagAndArticle> resultTagAndArticles = tagAndArticleRepository.findTagAndArticlesByTagId(tagId);
        // 初始化List
        List<Article> resultArticles = new LinkedList<>();
        // 遍历resultTagAndArticles生成resultArticles
        for (TagAndArticle tagAndArticle : resultTagAndArticles) {
            Integer articleId = tagAndArticle.getArticleId();
            Article article = articleRepository.findArticleByAidAndStatus(articleId, 0);
            if (article != null) {
                resultArticles.add(article);
            }
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
    @Cacheable(value = "titleKeyArticles", key = "#titleKey", unless = "#result.size() < 2")
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
        checkedArticle.setBackgroundImageUrl(newArticle.getBackgroundImageUrl());
        return articleRepository.save(checkedArticle);
    }

    @Override
    public Article modifyArticleByArticle(Article modifiedArticle) throws ArticleDoesNotExistException {
        Article checkedArticle = articleRepository.findArticleByAid(modifiedArticle.getAid());
        if (checkedArticle != null) {
            checkedArticle.setTitle(modifiedArticle.getTitle());
            checkedArticle.setText(modifiedArticle.getText());
            checkedArticle.setLastEditTime(new Timestamp(System.currentTimeMillis()));
            checkedArticle.setBackgroundImageUrl(modifiedArticle.getBackgroundImageUrl());
            return articleRepository.save(checkedArticle);
        } else {
            throw new ArticleDoesNotExistException();
        }
    }

    @Override
    public Integer addTagToArticleByAidAndTagId(Integer aid, Integer tagId) {
        if (articleRepository.findArticleByAid(aid) != null) {
            Tag tag = tagRepository.findTagByTagId(tagId);
            if (tag != null) {
                if (tagAndArticleRepository.findTagAndArticlesByArticleIdAndTagId(aid, tagId).size() != 0) {
                    throw new TagAndArticleAlreadyExistException();
                }
                TagAndArticle newTagAndArticle = new TagAndArticle();
                newTagAndArticle.setArticleId(aid);
                newTagAndArticle.setTagId(tagId);
                tag.setArticleAmount(tag.getArticleAmount() + 1);
                tagAndArticleRepository.save(newTagAndArticle);
                tagRepository.save(tag);
                return tag.getArticleAmount();
            } else {
                throw new TagDoesNotExistException();
            }
        } else {
            throw new ArticleDoesNotExistException();
        }
    }

    @Override
    public Boolean deleteAllTagsFromArticleByAid(Integer aid) {
        if (articleRepository.findArticleByAid(aid) != null) {
            tagAndArticleRepository.deleteAllByArticleId(aid);
            return Boolean.TRUE;
        } else {
            throw new ArticleDoesNotExistException();
        }
    }

    @Override
    public Integer addArchiveToArticleByAidAndArchiveId(Integer aid, Integer archiveId) throws ArticleDoesNotExistException, ArchvieDoesNotExistException {
        if (articleRepository.findArticleByAid(aid) != null) {
            Archive archive = archiveRepository.findArchiveByArchiveId(archiveId);
            if (archive != null) {
                ArchiveAndArticle newArchiveAndArticle = new ArchiveAndArticle();
                newArchiveAndArticle.setArticleId(aid);
                newArchiveAndArticle.setArchiveId(archiveId);
                archive.setArticleAmount(archive.getArticleAmount() + 1);
                archiveAndArticleRepository.save(newArchiveAndArticle);
                archiveRepository.save(archive);
                return archive.getArticleAmount();
            } else {
                throw new ArchvieDoesNotExistException();
            }
        } else {
            throw new ArticleDoesNotExistException();
        }
    }


    @Override
    @Transactional
    public Boolean deleteArticleByAid(Integer aid) throws ArticleDoesNotExistException, ArticleIsHiddenException {
        Article deletedArticle = articleRepository.findArticleByAid(aid);
        List<TagAndArticle> tagAndArticleList = tagAndArticleRepository.findTagAndArticlesByArticleId(aid);
        if (deletedArticle != null) {
            if (deletedArticle.getStatus() == 0) {
                deletedArticle.setStatus(1);
                articleRepository.save(deletedArticle);
                tagAndArticleRepository.deleteAllByArticleId(aid);
                for (TagAndArticle tagAndArticle: tagAndArticleList) {
                    tagService.syncArticleAmountByTagId(tagAndArticle.getTagId());
                }
                return Boolean.TRUE;
            } else {
                throw new ArticleIsHiddenException();
            }
        } else {
            throw new ArticleDoesNotExistException();
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
            article.setClickAmount(article.getClickAmount() + 1);
            articleRepository.save(article);
            return article.getClickAmount();
        } else {
            throw new ArticleDoesNotExistException();
        }
    }

    @Override
    public ArticleResponse convertToArticleResponse(Article article) {
        ArticleResponse articleResponse = new ArticleResponse(article);
        articleResponse.setAuthorName(userAPIService.findUserByUid(article.getAuthorId()).getNickname());
        return articleResponse;
    }

    @Override
    public List<ArticleResponse> convertToArticleResponseList(List<Article> articleList) {
        List<ArticleResponse> resultArticleResponseList = new LinkedList<>();
        for (Article article : articleList) {
            ArticleResponse articleResponse = new ArticleResponse(article);
            articleResponse.setAuthorName(userAPIService.findUserByUid(article.getAuthorId()).getNickname());
            resultArticleResponseList.add(articleResponse);
        }
        return resultArticleResponseList;
    }

}
