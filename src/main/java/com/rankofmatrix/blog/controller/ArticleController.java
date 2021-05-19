package com.rankofmatrix.blog.controller;

import com.rankofmatrix.blog.exception.*;
import com.rankofmatrix.blog.model.Article;
import com.rankofmatrix.blog.model.dto.ArticleResponse;
import com.rankofmatrix.blog.model.dto.JsonResponse;
import com.rankofmatrix.blog.service.ArchiveService;
import com.rankofmatrix.blog.service.ArticleAPIService;
import com.rankofmatrix.blog.service.TagService;
import com.rankofmatrix.blog.service.UserAPIService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/articles")
@Api(tags = {"Article API"})
@SwaggerDefinition(tags = {
        @Tag(name = "Article API", description = "RESTful Article API")
})
public class ArticleController {

    private ArticleAPIService articleAPIService;
    private TagService tagService;
    private ArchiveService archiveService;
    private UserAPIService userAPIService;

    @Autowired
    public void setArticleAPIService(ArticleAPIService articleAPIService) {
        this.articleAPIService = articleAPIService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    @Autowired
    public void setArchiveService(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    @Autowired
    public void setUserAPIService(UserAPIService userAPIService) {
        this.userAPIService = userAPIService;
    }

    // 获取所有文章(包含被删除)
    @GetMapping(path = "/")
    @ApiOperation("获取所有文章(包含被删除)")
    public JsonResponse getAllArticle() {
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.getAllArticle());
        int resultLength = articleResponseList.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultLength, articleResponseList);
        } else {
            return new JsonResponse(404, "Get no article", 0, null);
        }
    }

    // 获取所有可见的文章
    @GetMapping(path = "/visible")
    @ApiOperation("获取所有可见的文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "页大小", required = true, dataType = "int")
    })
    public JsonResponse getAllArticleVisible(Integer page, Integer size) {
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.getAllArticleVisible(page, size));
        int resultLength = articleResponseList.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultLength, articleResponseList);
        } else {
            return new JsonResponse(404, "Get no article", 0, null);
        }
    }

    // 获取所有可见的文章总数
    @GetMapping(path = "/visible/count")
    @ApiOperation("获取所有可见的文章总数")
    public JsonResponse getAllArticleVisible() {
        return new JsonResponse(200, "Get all articles successfully", 1, articleAPIService.countAllArticleVisible());
    }

    // 获取所有被删除的文章
    @GetMapping(path = "/deleted")
    @ApiOperation("获取所有被删除的文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "int"),
            @ApiImplicitParam(name = "size", value = "页大小", required = true, dataType = "int")
    })
    public JsonResponse getAllArticleDeleted(Integer page, Integer size) {
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.getAllArticleDeleted(page, size));
        int resultLength = articleResponseList.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get all articles successfully", resultLength, articleResponseList);
        } else {
            return new JsonResponse(404, "Get no article", 0, null);
        }
    }

    // 获取某一ID的可见文章
    @GetMapping(path = "/aid/{aid}")
    @ApiOperation("获取某一ID的可见文章")
    @ApiImplicitParam(name = "aid", value = "文章ID", required = true, dataType = "Int")
    public JsonResponse getArticleByAid(@PathVariable(value = "aid") Integer aid) {
        try {
            Article resultArticle = articleAPIService.getArticleByAid(aid);
            if (resultArticle.getStatus() != 0) {
                throw new ArticleIsHiddenException();
            } else {
                return new JsonResponse(200, "Get article successfully", 1, resultArticle);
            }
        } catch (ArticleDoesNotExistException | ArticleIsHiddenException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }

    }

    // 获取某一作者的所有文章
    @GetMapping(path = "/uid/{uid}")
    @ApiOperation("获取某一作者的所有文章(不带正文)")
    @ApiImplicitParam(name = "uid", value = "作者ID", required = true, dataType = "Int")
    public JsonResponse getArticleWithoutTextByUid(@PathVariable(value = "uid") Integer uid) {
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.getArticleWithoutTextByUid(uid));
        int resultLength = articleResponseList.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Get author's all articles successfully", resultLength, articleResponseList);
        } else {
            return new JsonResponse(404, "Author has no article", 0, null);
        }
    }

    // 检索标题中包含关键字的所有文章
    @GetMapping(path = "/titleKey/{titleKey}")
    @ApiOperation("检索标题中包含关键字的所有文章(不带正文)")
    @ApiImplicitParam(name = "titleKey", value = "标题检索关键词", required = true, dataType = "String")
    public JsonResponse selectArticleWithoutTextByTitleKey(@PathVariable(value = "titleKey") String titleKey) {
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.selectArticleWithoutTextByTitleKey(titleKey));
        int resultLength = articleResponseList.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Search articles successfully", resultLength, articleResponseList);
        } else {
            return new JsonResponse(404, "Search has not found", 0, null);
        }
    }

    // 检索模糊搜索带有关键字的所有文章
    @GetMapping(path = "/key/{key}")
    @ApiOperation("检索模糊搜索带有关键字的所有文章")
    @ApiImplicitParam(name = "key", value = "全文检索关键词", required = true, dataType = "String")
    public JsonResponse selectArticleWithoutTextByKey(@PathVariable(value = "key") String key) {
        List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.selectArticleByKey(key));
        int resultLength = articleResponseList.size();
        if (resultLength > 0) {
            return new JsonResponse(200, "Search articles successfully", resultLength, articleResponseList);
        } else {
            return new JsonResponse(404, "Search has not found", 0, null);
        }
    }

    // 查看含有某一标签名标签的所有文章
    @GetMapping(path = "/tag/{tagName}")
    @ApiOperation("查看含有某一标签名标签的所有文章(不带正文)")
    @ApiImplicitParam(name = "tagName", value = "标签名", required = true, dataType = "String")
    public JsonResponse selectArticlesByTagName(@PathVariable(value = "tagName") String tagName) {
        try {
            Integer tagId = tagService.getTagByTagName(tagName).getTagId();
            List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.getArticleWithoutTextByTagID(tagId));
            return new JsonResponse(200, "Get the tag's all articles successfully", articleResponseList.size(), articleResponseList);
        } catch (NullPointerException nullPointerException) {
            return new JsonResponse(404, "Tag has no article", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 查看某一归档名归档内的所有文章
    @GetMapping(path = "/archive/{archiveName}")
    @ApiOperation("查看某一归档名归档内的所有文章(不带正文)")
    @ApiImplicitParam(name = "archiveName", value = "归档名", required = true, dataType = "String")
    public JsonResponse selectArticlesByArchiveName(@PathVariable(value = "archiveName") String archiveName) {
        try {
            Integer archiveId = archiveService.getArchiveByArchiveName(archiveName).getArchiveId();
            List<ArticleResponse> articleResponseList = articleAPIService.convertToArticleResponseList(articleAPIService.getArticleWithoutTextByArchiveID(archiveId));
            return new JsonResponse(200, "Get the archive's all articles successfully", articleResponseList.size(), articleResponseList);
        } catch (NullPointerException nullPointerException) {
            return new JsonResponse(404, "Archive has no article", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 创建新的文章
    @PostMapping(path = "/")
    @ApiOperation("创建新的文章")
    @ApiImplicitParam(name = "createArticle", value = "将要创建的文章信息(标题必要)", required = true, dataType = "Article")
    public JsonResponse createArticleByArticle(@RequestBody Article createArticle) {
        System.out.println(createArticle);
        try {
            articleAPIService.hasArticleTitleAndAuthorId(createArticle);
            userAPIService.findUserByUid(createArticle.getAuthorId());
            Article createdArticle = articleAPIService.createArticleByArticle(createArticle);
            return new JsonResponse(201, "Create article successfully", 1, createdArticle);
        } catch (UserDoesNotExistException e) {
            return new JsonResponse(403, "Author does not exit", 0, null);
        } catch (IllegalArgumentException e) {
            return new JsonResponse(403, "Title and author id shall be given", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 修改某一文章(包含被删除)
    @PutMapping(path = "/")
    @ApiOperation("修改文章(包含被删除)")
    @ApiImplicitParam(name = "updateArticle", value = "将要修改的文章信息", required = true, dataType = "Article")
    public JsonResponse modifyArticleByArticle(@RequestBody Article updateArticle) {
        try {
            articleAPIService.hasArticleTitleAndTextAndAid(updateArticle);
            Article modifiedArticle = articleAPIService.modifyArticleByArticle(updateArticle);
            return new JsonResponse(200, "Update article successfully", 1, modifiedArticle);
        } catch (IllegalArgumentException e) {
            return new JsonResponse(403, "Aid, title and text shall be given", 0, null);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 添加某一ID标签到某一ID文章
    @PutMapping(path = "/aid/{aid}/add/tag/{tagId}")
    @ApiOperation("添加某一ID标签到某一ID文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "文章的ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "tagId", value = "标签的ID", required = true, dataType = "int")
    })
    public JsonResponse addTagToArticleByAidAndTagId(@PathVariable(value = "aid") Integer aid, @PathVariable(value = "tagId") Integer tagId) {
        try {
            Integer articleAmount = articleAPIService.addTagToArticleByAidAndTagId(aid, tagId);
            tagService.syncArticleAmountByTagId(tagId);
            return new JsonResponse(200, "Add Tag successfully", 1, articleAmount);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (TagDoesNotExistException e) {
            return new JsonResponse(403, "Tag does not exist", 0, null);
        } catch (TagAndArticleAlreadyExistException e) {
            return new JsonResponse(409, "TagAndArticle relation already exist", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 批量添加指定ID数组的标签到某一ID文章
    @PutMapping(path = "/aid/{aid}/add/tags")
    @Transactional
    @ApiOperation("批量添加指定ID数组的标签到某一ID文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "文章的ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "tagIds", value = "标签的ID组", required = true, dataType = "array")
    })
    public JsonResponse addTagsToArticleByAidAndTagIds(@PathVariable(value = "aid") Integer aid, @RequestBody List<Integer> tagIds) {
        try {
            articleAPIService.deleteAllTagsFromArticleByAid(aid);
            for (Integer tagId : tagIds) {
                try {
                    articleAPIService.addTagToArticleByAidAndTagId(aid, tagId);
                } catch (TagAndArticleAlreadyExistException e) {
                    System.out.println(e.toString());
                }
            }
            tagService.syncAllArticleAmount();
            return new JsonResponse(200, "Add Tags successfully", 0, null);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (TagDoesNotExistException e) {
            return new JsonResponse(403, "Tag does not exist", 0, null);
        }
    }

    // TODO 移除归档、标签关系
    // 添加某一ID归档到某一ID文章
    @PutMapping(path = "aid/{aid}/add/archive/{archiveId}")
    @ApiOperation("添加某一ID归档到某一ID文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "文章的ID", required = true, dataType = "int"),
            @ApiImplicitParam(name = "archiveId", value = "归档的ID", required = true, dataType = "int")
    })
    public JsonResponse addArchiveToArticleByAidAndArchiveId(@PathVariable(value = "aid") Integer aid, @PathVariable(value = "archiveId") Integer archiveId) {
        try {
            Integer articleAmount = articleAPIService.addArchiveToArticleByAidAndArchiveId(aid, archiveId);
            return new JsonResponse(200, "Add Archive successfully", 1, articleAmount);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (ArchvieDoesNotExistException e) {
            return new JsonResponse(403, "Archive does not exist", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 删除某一ID的文章
    @ApiOperation("删除某一ID的文章")
    @ApiImplicitParam(name = "aid", value = "将要删除的文章ID", required = true, dataType = "Int")
    @DeleteMapping(path = "/aid/{aid}")
    public JsonResponse deleteArticleByAid(@PathVariable(value = "aid") Integer aid) {
        try {
            articleAPIService.deleteArticleByAid(aid);
            return new JsonResponse(200, "Delete article successfully", 1, aid);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Delete article Failed, article may not exists", 0, null);
        } catch (ArticleIsHiddenException e) {
            return new JsonResponse(403, "Article is hidden", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 增加文章阅读量
    @ApiOperation("增加文章阅读量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "aid", value = "被提升阅读量的文章ID", required = true, dataType = "Int"),
            @ApiImplicitParam(name = "amount", value = "提升阅读量数值", required = true, dataType = "Int")
    })
    @PostMapping(path = "/aid/{aid}/add/clickAmount/value/{amount}")
    public JsonResponse increaseArticleClickAmountByAidAndAmount(@PathVariable Integer aid, @PathVariable Integer amount) {
        try {
            Integer resultClickAmount = articleAPIService.increaseArticleClickAmount(aid, amount);
            return new JsonResponse(200, "Increase click amount successfully", 1, resultClickAmount);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (ArticleIsHiddenException e) {
            return new JsonResponse(403, "Article is hidden", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

    // 在阅读文章后增加文章阅读量
    @ApiOperation("在阅读文章后增加文章阅读量")
    @ApiImplicitParam(name = "aid", value = "被阅读的文章ID", required = true, dataType = "Int")
    @PostMapping(path = "/aid/{aid}/read")
    public JsonResponse increaseArticleClickAmountByAidRead(@PathVariable Integer aid) {
        try {
            Integer resultClickAmount = articleAPIService.increaseArticleClickAmount(aid);
            return new JsonResponse(200, "Read article successfully", 1, resultClickAmount);
        } catch (ArticleDoesNotExistException e) {
            return new JsonResponse(404, "Article does not exist", 0, null);
        } catch (ArticleIsHiddenException e) {
            return new JsonResponse(403, "Article is hidden", 0, null);
        } catch (Exception e) {
            return new JsonResponse(100, e.toString(), 0, null);
        }
    }

}
