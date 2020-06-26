package com.rankofmatrix.blog.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Archive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_id")
    private Integer archiveId;
    @Column(name = "archive_name")
    private String archiveName;
    @Column(name = "article_amount")
    private Integer articleAmount;

    public Integer getArchiveId() {
        return archiveId;
    }

    public void setArchiveId(Integer archiveId) {
        this.archiveId = archiveId;
    }

    public String getArchiveName() {
        return archiveName;
    }

    public void setArchiveName(String archiveName) {
        this.archiveName = archiveName;
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
        Archive archive = (Archive) o;
        return Objects.equals(archiveId, archive.archiveId) &&
                Objects.equals(archiveName, archive.archiveName) &&
                Objects.equals(articleAmount, archive.articleAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archiveId, archiveName, articleAmount);
    }

    @Override
    public String toString() {
        return "Archive{" +
                "archiveId=" + archiveId +
                ", archiveName='" + archiveName + '\'' +
                ", articleAmount=" + articleAmount +
                '}';
    }
}
