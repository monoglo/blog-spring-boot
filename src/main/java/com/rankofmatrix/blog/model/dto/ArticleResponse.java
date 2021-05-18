package com.rankofmatrix.blog.model.dto;

import com.rankofmatrix.blog.model.Article;


public class ArticleResponse extends Article {
    private String authorName;

    public ArticleResponse(Article article) {
        this.setTitle(article.getTitle());
        this.setAid(article.getAid());
        this.setTitle(article.getTitle());
        this.setText(article.getText());
        this.setAuthorId(article.getAuthorId());
        this.setCreateTime(article.getCreateTime());
        this.setLastEditTime(article.getLastEditTime());
        this.setClickAmount(article.getClickAmount());
        this.setBackgroundImageUrl(article.getBackgroundImageUrl());
        this.setBackgroundImageCopyright(article.getBackgroundImageCopyright());
        this.setStatus(article.getStatus());
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "aid=" + getAid() +
                ", title='" + getTitle() + '\'' +
                ", text='" + getText() + '\'' +
                ", authorId=" + getAuthorId() +
                ", authorName='" + getAuthorName() + '\'' +
                ", createTime=" + getCreateTime() +
                ", lastEditTime=" + getLastEditTime() +
                ", clickAmount=" + getClickAmount() +
                ", status=" + getStatus() +
                '}';
    }
}
