package com.What2Do.What2Do.infoComment.controller;

import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.infoComment.etc.InfoCommentDto;
import com.What2Do.What2Do.infoComment.service.InfoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class InfoCommentController {


    @Autowired
    private InfoCommentService infoCommentService;


    // 댓글 생성
    @PostMapping("/infoComment/new")
    public ResponseEntity<InfoComment> createForm(@RequestBody InfoCommentDto infoCommentDto) throws Exception {
        InfoComment InfoCommentCreate = infoCommentService.create(infoCommentDto);
        return new ResponseEntity<>(InfoCommentCreate, HttpStatus.CREATED);
    }

    // 댓글 목록
    @GetMapping("/infoComment/lists")
    public ResponseEntity<List<InfoComment>> getAllInfoComments() {
        List<InfoComment> infoComments = infoCommentService.findAll();
        return ResponseEntity.ok(infoComments);
    }

    // 댓글 수정
    @PostMapping("/infoComment/{infoCommentId}/update")
    public ResponseEntity<InfoComment> updateInfoComment(
            @PathVariable Long infoCommentId,
            @RequestBody InfoCommentDto updatedInfoCommentDto) throws Exception {
        InfoComment updateInfoComment = infoCommentService.update(infoCommentId, updatedInfoCommentDto);
        return new ResponseEntity<>(updateInfoComment, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/infoComments/{infoCommentId}")
    public ResponseEntity<Void> deleteInfoComment(@PathVariable Long infoCommentId) throws Exception {
        infoCommentService.delete(infoCommentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
