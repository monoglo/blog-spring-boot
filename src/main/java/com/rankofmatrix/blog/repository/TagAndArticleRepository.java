package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.TagAndArticle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagAndArticleRepository extends CrudRepository<TagAndArticle, Integer> {
    // 检索某一指定TagID的Tag的关联文章记录
    List<TagAndArticle> findTagAndArticlesByTagId(Integer tagId);
    // 检索某一指定AID的文章的关联Tag记录
    List<TagAndArticle> findTagAndArticlesByArticleId(Integer aid);
}
