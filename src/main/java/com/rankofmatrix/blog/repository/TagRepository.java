package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {

    Tag findTagByTagId(Integer tagId);
    Tag findTagByTagName(String tagName);
    List<Tag> findTagsByTagNameContains(String tagNameKey);
    Integer deleteByTagId(Integer tagId);

}
