package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.Article;

import java.util.List;

public interface ArticleAPIService {
    // 获取所有文章
    List<Article> getAllArticle();
    // 获取所有可见的文章
    List<Article> getAllArticleVisible();
    // 获取所有被删除的文章
    List<Article> getAllArticleDeleted();

    // TODO 不带正文的service方法↑

    // 获取某一ID的文章
    Article getArticleByAid(Integer aid);
    // 获取某一作者的所有文章(不带正文)
    List<Article> getArticleWithoutTextByUid(Integer uid);
    // 获取某一标签的所有文章(不带正文)
    List<Article> getArticleWithoutTextByTagID(Integer tagId);
    // 获取某一归档的所有文章(不带正文)
    List<Article> getArticleWithoutTextByArchiveID(Integer archiveId);

    // 检索标题中包含关键字的所有文章(不带正文)
    List<Article> selectArticleWithoutTextByTitleKey(String titleKey);
    // 检索模糊搜索带有关键字的所有文章
    List<Article> selectArticleByKey(String Key);

    // 创建新的文章
    Article createArticleByArticle(Article newArticle);

    // 修改某一文章
    Article modifyArticleByArticle(Article modifiedArticle);
    // 添加标签到某一文章
    Boolean addTagToArticleByAidAndTagId(Integer aid, Integer tagId);
    // 添加归档到某一文章
    Boolean addArchiveToArticleByAidAndArchiveId(Integer aid, Integer archiveId);

    // 删除某一ID的文章
    Boolean deleteArticleByAid(Integer aid);

    // 判断输入的Article是否含有必要元素
    Boolean isInputArticleLegal(Article article);

}
