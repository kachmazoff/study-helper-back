package com.kach.studyhelperback.controller.api;

import com.kach.studyhelperback.dto.RelationsRequestBody;
import com.kach.studyhelperback.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relations")
public class ArticlesRelationsController {

    @Autowired
    GraphService graphService;

    @PostMapping("")
    public void addRelations(@RequestBody List<RelationsRequestBody> bodies){
        for (RelationsRequestBody relationsRequestBody : bodies) {
            graphService.addRelation(relationsRequestBody.getCurId(), relationsRequestBody.getFromId());
        }
    }//
}