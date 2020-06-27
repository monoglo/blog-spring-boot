package com.rankofmatrix.blog.service.impl;

import com.google.common.collect.Lists;
import com.rankofmatrix.blog.model.Tag;
import com.rankofmatrix.blog.repository.TagRepository;
import com.rankofmatrix.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return Lists.newArrayList(tagRepository.findAll());
    }

    @Override
    public Tag getTagByTagId(Integer tagId) {
        return tagRepository.findTagByTagId(tagId);
    }

    @Override
    public Tag getTagByTagName(String tagName) {
        return tagRepository.findTagByTagName(tagName);
    }

    @Override
    public List<Tag> selectTagsByTagNameKey(String tagNameKey) {
        return tagRepository.findTagsByTagNameContains(tagNameKey);
    }

    @Override
    public Tag createTagByTag(Tag newTag) {
        return tagRepository.save(newTag);
    }

    @Override
    public Tag modifyTagByTag(Tag modifiedTag) {
        return tagRepository.save(modifiedTag);
    }

    @Override
    public Integer deleteTagByTagId(Integer tagId) {
        return tagRepository.deleteByTagId(tagId);
    }

}
