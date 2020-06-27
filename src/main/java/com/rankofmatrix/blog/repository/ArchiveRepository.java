package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.Archive;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArchiveRepository extends CrudRepository<Archive, Integer> {

    Archive findArchiveByArchiveId(Integer archiveId);
    Archive findArchiveByArchiveName(String archiveName);
    List<Archive> findArchivesByArchiveNameContains(String archiveNameKey);
    Integer deleteArchiveByArchiveId(Integer archiveId);

}
