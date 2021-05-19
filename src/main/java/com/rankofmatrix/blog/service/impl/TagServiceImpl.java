package com.rankofmatrix.blog.service.impl;

import com.google.common.collect.Lists;
import com.rankofmatrix.blog.model.Tag;
import com.rankofmatrix.blog.model.TagAndArticle;
import com.rankofmatrix.blog.repository.TagAndArticleRepository;
import com.rankofmatrix.blog.repository.TagRepository;
import com.rankofmatrix.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;
    private TagAndArticleRepository tagAndArticleRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Autowired
    public void setTagAndArticleRepository(TagAndArticleRepository tagAndArticleRepository) {
        this.tagAndArticleRepository = tagAndArticleRepository;
    }
    @Override
    @Cacheable(value = "allTags")
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
    public List<Tag> getTagsByAid(Integer aid) {
        List<TagAndArticle> resultTagAndArticles = tagAndArticleRepository.findTagAndArticlesByArticleId(aid);
        List<Tag> resultTags = new LinkedList<>();
        for (TagAndArticle tagAndArticle : resultTagAndArticles) {
            Integer tagId = tagAndArticle.getTagId();
            resultTags.add(tagRepository.findTagByTagId(tagId));
        }
        return resultTags;
    }

    @Override
    @CacheEvict(value = "allTags", allEntries = true)
    public Tag createTagByTag(Tag newTag) {
        return tagRepository.save(newTag);
    }

    @Override
    @CacheEvict(value = "allTags", allEntries = true)
    public Tag modifyTagByTag(Tag modifiedTag) {
        return tagRepository.save(modifiedTag);
    }

    @Override
    @CacheEvict(value = "allTags", allEntries = true)
    public Integer deleteTagByTagId(Integer tagId) {
        return tagRepository.deleteByTagId(tagId);
    }

    @Override
    @CacheEvict(value = "tagIdArticles", key = "#tagId")
    public Integer syncArticleAmountByTagId(Integer tagId) {
        Tag tag = tagRepository.findTagByTagId(tagId);
        tag.setArticleAmount(tagAndArticleRepository.countByTagId(tagId));
        tagRepository.save(tag);
        return tag.getArticleAmount();
    }

    @Override
    @CacheEvict(value = "tagIdArticles", allEntries = true)
    public Boolean syncAllArticleAmount() {
        Iterable<Tag> tagList = tagRepository.findAll();
        tagList.forEach(tag -> {
            tag.setArticleAmount(tagAndArticleRepository.countByTagId(tag.getTagId()));
            tagRepository.save(tag);
        });
        return Boolean.TRUE;
    }

}
