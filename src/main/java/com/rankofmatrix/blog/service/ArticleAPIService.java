package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.Article;

public interface ArticleAPIService {
    // 获取所有文章
    Iterable<Article> getAllArticle();
    // 获取所有可见的文章
    Iterable<Article> getAllArticleVisible();
    // 获取所有被删除的文章
    Iterable<Article> getAllArticleDeleted();

    // TODO 不带正文的service方法↑

    // 获取某一ID的文章
    Article getArticleByAid(Integer aid);
    // 获取某一作者的所有文章(不带正文)
    Iterable<Article> getArticleWithoutTextByUid(Integer uid);
//    // 获取某一标签的所有文章(不带正文)
//    Iterable<Article> getArticleWithoutTextByTagID(Integer tagId);
//    // 获取某一归档的所有文章(不带正文)
//    Iterable<Article> getArticleWithoutTextByArchiveID(Integer archiveId);

    // 检索标题中包含关键字的所有文章(不带正文)
    Iterable<Article> selectArticleWithoutTextByTitleKey(String titleKey);
    // 检索模糊搜索带有关键字的所有文章(不带正文)
    Iterable<Article> selectArticleWithoutTextByKey(String Key);

    // 创建新的文章
    Article createArticleByArticle(Article newArticle);

    // 修改某一文章
    Article modifyArticleByArticle(Article modifiedArticle);
//    // 添加标签到某一文章
//    Boolean addTagToArticleByAidAndTagId(Integer aid, Integer tagId);
//    // 添加归档到某一文章
//    Boolean addArchiveToArticleByAidAndArchiveId(Integer aid, Integer archiveId);

    // 删除某一ID的文章
    Boolean deleteArticleByAid(Integer aid);

}
