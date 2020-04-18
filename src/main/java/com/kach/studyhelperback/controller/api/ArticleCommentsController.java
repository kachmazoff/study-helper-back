package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.ArticleComments;
import com.kach.studyhelperback.service.ArticleCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class ArticleCommentsController {

    @Autowired
    ArticleCommentsService articleCommentsService;

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody ArticleComments articleComment){
        articleCommentsService.addArticleComment(articleComment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/addReply")
    public ResponseEntity addReplyComment(@RequestBody ArticleComments articleComment,
                                          @RequestBody ArticleComments replyArticleComment){
        articleCommentsService.addReplyArticleComment(articleComment, replyArticleComment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public List<ArticleComments> getArticleComment(@PathVariable("id") Long articleId){
        return articleCommentsService.getArticleComment(articleId);
    }
}
