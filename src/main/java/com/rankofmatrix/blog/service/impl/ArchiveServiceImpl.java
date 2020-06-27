package com.rankofmatrix.blog.service.impl;

import com.google.common.collect.Lists;
import com.rankofmatrix.blog.model.Archive;
import com.rankofmatrix.blog.repository.ArchiveRepository;
import com.rankofmatrix.blog.service.ArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl implements ArchiveService {

    private ArchiveRepository archiveRepository;

    @Autowired
    public void setArchiveRepository(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public List<Archive> getAllArchives() {
        return Lists.newArrayList(archiveRepository.findAll());
    }

    @Override
    public Archive getArchiveByArchiveId(Integer archiveId) {
        return archiveRepository.findArchiveByArchiveId(archiveId);
    }

    @Override
    public Archive getArchiveByArchiveName(String archiveName) {
        return archiveRepository.findArchiveByArchiveName(archiveName);
    }

    @Override
    public List<Archive> selectArchivesByArchiveNameKey(String archiveNameKey) {
        return archiveRepository.findArchivesByArchiveNameContains(archiveNameKey);
    }

    @Override
    public Archive createArchiveByArchive(Archive newArchive) {
        return archiveRepository.save(newArchive);
    }

    @Override
    public Archive modifyArchiveByArchive(Archive modifiedArchive) {
        return archiveRepository.save(modifiedArchive);
    }

    @Override
    public Integer deleteArchiveByArchiveId(Integer archiveId) {
        return archiveRepository.deleteArchiveByArchiveId(archiveId);
    }

}
