package com.rankofmatrix.blog.service;

import com.rankofmatrix.blog.model.Archive;

import java.util.List;

public interface ArchiveService {

    // 获取所有归档
    List<Archive> getAllArchives();

    // 获取某一ArchiveID的归档
    Archive getArchiveByArchiveId(Integer archiveId);
    // 获取某一ArchiveName的归档
    Archive getArchiveByArchiveName(String archiveName);

    // 模糊搜索ArchiveName中包含关键字的所有归档
    List<Archive> selectArchivesByArchiveNameKey(String archiveNameKey);

    // 创建一个新的归档
    Archive createArchiveByArchive(Archive newArchive);

    // 修改某一归档
    Archive modifyArchiveByArchive(Archive modifiedArchive);

    // 删除指定ArchiveID的归档
    Integer deleteArchiveByArchiveId(Integer archiveId);

}
