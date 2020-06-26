package com.rankofmatrix.blog.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Integer tagId;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "article_amount")
    private Integer articleAmount;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getArticleAmount() {
        return articleAmount;
    }

    public void setArticleAmount(Integer articleAmount) {
        this.articleAmount = articleAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(tagId, tag.tagId) &&
                Objects.equals(tagName, tag.tagName) &&
                Objects.equals(articleAmount, tag.articleAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagName, articleAmount);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", articleAmount=" + articleAmount +
                '}';
    }
}