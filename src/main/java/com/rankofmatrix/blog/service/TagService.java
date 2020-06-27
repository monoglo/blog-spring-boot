package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.Tag;

import java.util.List;

public interface TagService {

    // 获取所有标签
    List<Tag> getAllTags();

    // 获取某一TagID的标签
    Tag getTagByTagId(Integer tagId);
    // 获取某一TagName的标签
    Tag getTagByTagName(String tagName);

    // 模糊搜索TagName中包含关键字的所有标签
    List<Tag> selectTagsByTagNameKey(String tagNameKey);

    // 创建新的标签
    Tag createTagByTag(Tag newTag);

    // 修改某一标签
    Tag modifyTagByTag(Tag modifiedTag);

    // 删除某一TagID的标签
    Integer deleteTagByTagId(Integer tagId);

}
