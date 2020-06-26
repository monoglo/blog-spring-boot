package com.rankofmatrix.blog.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "archive_article")
public class ArchiveAndArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_article_id")
    private Integer ArchiveArticleId;
    @Column(name = "archive_id")
    private Integer ArchiveId;
    @Column(name = "article_id")
    private Integer ArticleId;

    public Integer getArchiveArticleId() {
        return ArchiveArticleId;
    }

    public void setArchiveArticleId(Integer archiveArticleId) {
        ArchiveArticleId = archiveArticleId;
    }

    public Integer getArchiveId() {
        return ArchiveId;
    }

    public void setArchiveId(Integer archiveId) {
        ArchiveId = archiveId;
    }

    public Integer getArticleId() {
        return ArticleId;
    }

    public void setArticleId(Integer articleId) {
        ArticleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveAndArticle that = (ArchiveAndArticle) o;
        return Objects.equals(ArchiveArticleId, that.ArchiveArticleId) &&
                Objects.equals(ArchiveId, that.ArchiveId) &&
                Objects.equals(ArticleId, that.ArticleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ArchiveArticleId, ArchiveId, ArticleId);
    }

    @Override
    public String toString() {
        return "ArchiveAndArticle{" +
                "ArchiveArticleId=" + ArchiveArticleId +
                ", ArchiveId=" + ArchiveId +
                ", ArticleId=" + ArticleId +
                '}';
    }
}


