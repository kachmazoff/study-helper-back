package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.model.ArticleComments;
import com.kach.studyhelperback.service.ArticleCommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class ArticleCommentsController {

    @Autowired
    ArticleCommentsService articleCommentsService;

    @PostMapping("")
    public ResponseEntity addComment(@RequestBody ArticleComments articleComment){
        articleCommentsService.addArticleComment(articleComment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity addReplyComment(@PathVariable("id") Long articleId,
                                          @RequestBody ArticleComments replyArticleComment){
        articleCommentsService.addReplyArticleComment(articleId, replyArticleComment);
        return ResponseEntity.ok(HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public List<ArticleComments> getArticleComments(@PathVariable("id") Long articleId){
//        return articleCommentsService.getArticleComments(articleId);
//    }
}
