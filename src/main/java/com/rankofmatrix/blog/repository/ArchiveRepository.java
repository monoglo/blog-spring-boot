package com.rankofmatrix.blog.repository;

import com.rankofmatrix.blog.model.Archive;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchiveRepository extends CrudRepository<Archive, Integer> {

    Archive findArchiveByArchiveId(Integer archiveId);
    Archive findArchiveByArchiveName(String archiveName);
    List<Archive> findArchivesByArchiveNameContains(String archiveNameKey);
    Integer deleteArchiveByArchiveId(Integer archiveId);

}
