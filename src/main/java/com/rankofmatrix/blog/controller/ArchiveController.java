package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.model.Archive;
import com.rankofmatrix.blog.model.JsonResponse;
import com.rankofmatrix.blog.service.impl.ArchiveServiceImpl;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/archives")
@Api(tags = {"Archives API"})
@SwaggerDefinition(tags = {
        @Tag(name = "Archives API", description = "RESTful Archive API")
})
public class ArchiveController {

    private ArchiveServiceImpl archiveService;

    @Autowired
    public void setArchiveService(ArchiveServiceImpl archiveService) {
        this.archiveService = archiveService;
    }

    // 获取所有归档
    @GetMapping(path = "/")
    @ApiOperation("获取所有归档")
    public JsonResponse getAllArchives() {
        List<Archive> resultArchives = archiveService.getAllArchives();
        int resultLength = resultArchives.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all archives successfully", resultLength, resultArchives);
        } else {
            return new JsonResponse(404, "Get no archive", 0, null);
        }
    }

    // 获取某一ArchiveID的归档
    @GetMapping(path = "/id/{id}")
    @ApiOperation("获取指定ArchiveID的归档")
    @ApiImplicitParam(name = "id", value = "要获取归档的归档ID", required = true, dataType = "Int")
    public JsonResponse getArchiveByArchiveId(@PathVariable(value = "id") Integer archiveId) {
        Archive resultArchive = archiveService.getArchiveByArchiveId(archiveId);
        if (resultArchive != null) {
            return new JsonResponse(200, "Get archive successfully", 1, resultArchive);
        } else {
            return new JsonResponse(404, "Get archive failed", 0, null);
        }
    }

    // 获取某一ArchiveName的归档
    @GetMapping(path = "/name/{name}")
    @ApiOperation("获取指定ArchiveName的归档")
    @ApiImplicitParam(name = "name", value = "要获取归档的归档Name", required = true, dataType = "String")
    public JsonResponse getArchiveByArchiveName(@PathVariable(value = "name") String archiveName) {
        Archive resultArchive = archiveService.getArchiveByArchiveName(archiveName);
        if (resultArchive != null) {
            return new JsonResponse(200, "Get archive successfully", 1, resultArchive);
        } else {
            return new JsonResponse(404, "Get archive failed", 0, null);
        }
    }

    // 模糊搜索ArchiveName中包含关键字的所有归档
    @GetMapping(path = "/nameKey/{nameKey}")
    @ApiOperation("模糊搜索ArchiveName中包含关键字的所有归档")
    @ApiImplicitParam(name = "nameKey", value = "要检索归档的检索Name关键字", required = true, dataType = "String")
    public JsonResponse selectArchivesByArchiveNameKey(@PathVariable(value = "nameKey") String nameKey) {
        List<Archive> resultArchives = archiveService.selectArchivesByArchiveNameKey(nameKey);
        int resultLength = resultArchives.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Search archives successfully", resultLength, resultArchives);
        } else {
            return new JsonResponse(404, "Search has no found", 0, null);
        }
    }

    // 获取某一AID文章的所有归档
    @GetMapping(path = "/aid/{aid}")
    @ApiOperation("获取某一AID文章的所有归档")
    @ApiImplicitParam(name = "aid", value = "将要获取的归档的所属文章的ID", required = true, dataType = "Int")
    public JsonResponse selectArchiveByArticleId(@PathVariable(value = "aid") Integer aid) {
        List<Archive> resultArchives = archiveService.getArchivesByAid(aid);
        int resultLength = resultArchives.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get archives by aid successfully", resultLength, resultArchives);
        } else {
            return new JsonResponse(404, "Get no archive", 0, null);
        }
    }

    // 创建新的归档
    @PostMapping(path = "/")
    @ApiOperation("创建新的归档")
    @ApiImplicitParam(name = "newArchive", value = "将要创建归档的信息", required = true, dataType = "Archive")
    public JsonResponse createArchiveByArchive(@RequestBody Archive newArchive) {
        Archive resultArchive = archiveService.createArchiveByArchive(newArchive);
        if (resultArchive != null) {
            return new JsonResponse(200, "Create archive successfully", 1, resultArchive);
        } else {
            return new JsonResponse(403, "Create archive failed", 0, null);
        }
    }

    // 修改某一归档
    @PutMapping(path = "/")
    @ApiOperation("修改某一归档")
    @ApiImplicitParam(name = "modifiedArchive", value = "将要修改归档的信息", required = true, dataType = "Archive")
    public JsonResponse updateArchiveByArchive(@RequestBody Archive modifiedArchive) {
        Archive resultArchive = archiveService.modifyArchiveByArchive(modifiedArchive);
        if (resultArchive != null) {
            return new JsonResponse(200, "Update archive successfully", 1, resultArchive);
        } else {
            return new JsonResponse(403, "Update archive failed", 0, null);
        }
    }

    // 删除某一ArchiveID的归档
    @DeleteMapping(path = "/id/{id}")
    @Transactional
    @ApiOperation("删除某一ArchiveID的归档")
    @ApiImplicitParam(name = "id", value = "将要删除归档的ID", required = true, dataType = "Int")
    public JsonResponse deleteArchiveByArchiveId(@PathVariable(value = "id") Integer archiveId) {
        Integer result = archiveService.deleteArchiveByArchiveId(archiveId);
        if (result == 1) {
            return new JsonResponse(200, "Delete archive successfully", 0, null);
        } else {
            return new JsonResponse(403, "Delete archive Failed, archive may not exists", 0, null);
        }
    }

}
