package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {

    Tag findTagByTagId(Integer tagId);
    Tag findTagByTagName(String tagName);
    List<Tag> findTagsByTagNameContains(String tagNameKey);
    Integer deleteByTagId(Integer tagId);

}
