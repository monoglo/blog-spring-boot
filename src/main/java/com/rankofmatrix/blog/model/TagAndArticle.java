package com.rankofmatrix.blog.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tag_article")
public class TagAndArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_article_id")
    private Integer tagArticleId;
    @Column(name = "tag_id")
    private Integer tagId;
    @Column(name = "article_id")
    private Integer articleId;

    public Integer getTagArticleId() {
        return tagArticleId;
    }

    public void setTagArticleId(Integer tagArticleId) {
        this.tagArticleId = tagArticleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
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
        TagAndArticle that = (TagAndArticle) o;
        return Objects.equals(tagArticleId, that.tagArticleId) &&
                Objects.equals(tagId, that.tagId) &&
                Objects.equals(articleId, that.articleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagArticleId, tagId, articleId);
    }

    @Override
    public String toString() {
        return "TagAndArticle{" +
                "tagArticleId=" + tagArticleId +
                ", tagId=" + tagId +
                ", articleId=" + articleId +
                '}';
    }
}
