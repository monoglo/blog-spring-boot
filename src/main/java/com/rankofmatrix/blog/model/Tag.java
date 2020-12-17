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
    @Column(name = "tag_color")
    private String tagColor;
    @Column(name = "tag_icon")
    private String tagIcon;
    @Column(name = "article_amount")
    private Integer articleAmount = 0;

    public String getTagColor() {
        return tagColor;
    }

    public void setTagColor(String tagColor) {
        this.tagColor = tagColor;
    }

    public String getTagIcon() {
        return tagIcon;
    }

    public void setTagIcon(String tagIcon) {
        this.tagIcon = tagIcon;
    }

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
    public String toString() {
        return "Tag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", tagColor='" + tagColor + '\'' +
                ", tagIcon='" + tagIcon + '\'' +
                ", articleAmount=" + articleAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagId.equals(tag.tagId) && tagName.equals(tag.tagName) && Objects.equals(tagColor, tag.tagColor) && Objects.equals(tagIcon, tag.tagIcon) && articleAmount.equals(tag.articleAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, tagName, tagColor, tagIcon, articleAmount);
    }

}
