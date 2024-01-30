package com.What2Do.What2Do.comment.controller;

import com.What2Do.What2Do.comment.domain.Comment;
import com.What2Do.What2Do.comment.etc.CommentDto;
import com.What2Do.What2Do.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")  // URL 매핑을 "/comments"로 변경
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 댓글 생성
    @PostMapping("/create")
    public String createComment(CommentDto commentDto, Model model) {
        Comment savedComment = commentService.create(commentDto);
        // 반환 값으로 savedComment를 Model에 추가
        model.addAttribute("savedComment", savedComment);
        // 댓글을 작성한 게시글로 리다이렉트 또는 다른 화면으로 이동
        return "redirect:/post/detail/" + commentDto.getPost().getId();
    }

    // 다른 댓글 관련 API 메서드들...

    // 댓글 수정
    @PostMapping("/{commentId}/update")
    public String updateComment(
            @PathVariable Long commentId,
            @ModelAttribute CommentDto updatedCommentDto) throws Exception {
        Comment updatedComment = commentService.update(commentId, updatedCommentDto);

        // 댓글을 수정한 게시글로 리다이렉트 또는 다른 화면으로 이동
        return "redirect:/post/detail/" + updatedComment.getPost().getId();
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public String deleteComment(@RequestParam(value = "id") Long id) throws Exception {
        commentService.delete(id);
        // 댓글을 삭제한 게시글로 리다이렉트 또는 다른 화면으로 이동
        return "redirect:/post/detail/";
    }
}
