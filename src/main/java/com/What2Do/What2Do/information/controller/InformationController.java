package com.What2Do.What2Do.information.controller;

import com.What2Do.What2Do.comment.domain.Comments;
import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.infoComment.repository.InfoCommentRepository;
import com.What2Do.What2Do.infoComment.service.InfoCommentService;
import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.information.etc.InformationDto;
import com.What2Do.What2Do.information.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class InformationController {

    @Autowired
    private InformationService informationService;

    @Autowired
    private InfoCommentService infoCommentService;

    @Autowired
    private InfoCommentRepository infoCommentRepository;

    //게시글 생성
    @GetMapping("/information/new")
    public String createInfoForm (){
        return "information/information-form";
    }

    @PostMapping("/information/new")
    public String createInfo (InformationDto informationDto){
        informationService.create(informationDto);
        return "redirect:/information/lists";
    }

    // 게시글 전체 조회

    @GetMapping("/information/lists")
    public String findAll(Model model) throws SQLException {
        List<Information> informationList = informationService.findAll();
        model.addAttribute("informationList", informationList);
        return "information/information-list";
    }

    // 게시글 상세 조회
    @GetMapping("/information/detail/{id}")
    public String infoFindById(@PathVariable(value = "id")Long myId, Model model) throws Exception {
        Information information1 = informationService.findById(myId);
        List<InfoComment> infoComments = infoCommentRepository.findAllByInformationId(myId);
        model.addAttribute("infoComments" , infoComments);
        model.addAttribute("infoDetail", information1);
        return "information/information-detail" ;
    }

    // 게시글 수정
    @PostMapping("/information/update")
    public String updateInfo (InformationDto informationDto) throws Exception {
        informationService.update(informationDto);
        return "redirect:/information/lists";
    }

    // 게시글 삭제

    @GetMapping("/information/delete")
    public String deleteInfo(@RequestParam(value = "id")String myId) throws Exception {
        informationService.delete(Long.valueOf(myId));
        return"redirect:/information/lists";
    }
















}

