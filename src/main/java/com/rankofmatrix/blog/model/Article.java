package com.rankofmatrix.blog.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    private String title;
    private String text;
    @Column(name = "author_id")
    private Integer authorId;
    @Column(name = "create_time")
    private Timestamp createTime = new Timestamp(System.currentTimeMillis());
    @Column(name = "last_edit_time")
    private Timestamp lastEditTime = new Timestamp(System.currentTimeMillis());
    @Column(name = "click_amount")
    private Integer clickAmount = 0;
    private Integer status = 0;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Timestamp lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Integer getClickAmount() {
        return clickAmount;
    }

    public void setClickAmount(Integer clickAmount) {
        this.clickAmount = clickAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(aid, article.aid) &&
                Objects.equals(title, article.title) &&
                Objects.equals(text, article.text) &&
                Objects.equals(authorId, article.authorId) &&
                Objects.equals(createTime, article.createTime) &&
                Objects.equals(lastEditTime, article.lastEditTime) &&
                Objects.equals(clickAmount, article.clickAmount) &&
                Objects.equals(status, article.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aid, title, text, authorId, createTime, lastEditTime, clickAmount, status);
    }

    @Override
    public String toString() {
        return "Article{" +
                "aid=" + aid +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", authorId=" + authorId +
                ", createTime=" + createTime +
                ", lastEditTime=" + lastEditTime +
                ", clickAmount=" + clickAmount +
                ", status=" + status +
                '}';
    }
}
