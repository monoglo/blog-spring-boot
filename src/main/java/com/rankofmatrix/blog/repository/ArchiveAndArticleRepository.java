package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.ArchiveAndArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveAndArticleRepository extends CrudRepository<ArchiveAndArticle, Integer> {
    List<ArchiveAndArticle> findArchiveAndArticlesByArchiveId(Integer archiveId);
    List<ArchiveAndArticle> findArchiveAndArticlesByArticleId(Integer articleId);
}
