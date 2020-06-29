package com.rankofmatrix.blog.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "archive_article")
public class ArchiveAndArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_article_id")
    private Integer archiveArticleId;
    @Column(name = "archive_id")
    private Integer archiveId;
    @Column(name = "article_id")
    private Integer articleId;

    public Integer getArchiveArticleId() {
        return archiveArticleId;
    }

    public void setArchiveArticleId(Integer archiveArticleId) {
        this.archiveArticleId = archiveArticleId;
    }

    public Integer getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiveAndArticle that = (ArchiveAndArticle) o;
        return Objects.equals(archiveArticleId, that.archiveArticleId) &&
                Objects.equals(archiveId, that.archiveId) &&
                Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archiveArticleId, archiveId, articleId);
    }

    @Override
    public String toString() {
        return "ArchiveAndArticle{" +
                "ArchiveArticleId=" + archiveArticleId +
                ", archiveId=" + archiveId +
                ", articleId=" + articleId +
                '}';
    }
}


