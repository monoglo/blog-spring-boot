package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.TagAndArticle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagAndArticleRepository extends CrudRepository<TagAndArticle, Integer> {
    // 检索某一指定TagID的Tag的关联文章记录
    List<TagAndArticle> findTagAndArticlesByTagId(Integer tagId);
    // 检索某一指定AID的文章的关联Tag记录
    List<TagAndArticle> findTagAndArticlesByArticleId(Integer aid);
    // 检索某一指定AID和指定TagID的文章的关联Tag记录
    List<TagAndArticle> findTagAndArticlesByArticleIdAndTagId(Integer aid, Integer tagId);
    // 删除某一指定AID的文章的所有关联Tag记录
    void deleteAllByArticleId(Integer aid);
    // 获取某一标签的关联记录条数
    Integer countByTagId(Integer tagId);
}
