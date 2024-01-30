package com.What2Do.What2Do.infoComment.service;

import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.infoComment.etc.InfoCommentDto;
import com.What2Do.What2Do.infoComment.repository.InfoCommentRepository;
import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.information.service.InformationService;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoCommentService {

    @Autowired
    private InfoCommentRepository infoCommentRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private InformationService informationService;

    public InfoComment create (InfoCommentDto infoCommentDto) throws Exception {
        Member member1 = memberService.findById(infoCommentDto.getMember().getId());
        Information information1 = informationService.findById(infoCommentDto.getInformation().getId());
        InfoComment infoComment1 = InfoComment.builder()
                .information(information1)
                .comment(infoCommentDto.getComment())
                .member(member1)
                .build();
        infoCommentRepository.save(infoComment1);
        return infoComment1;
    }


    public List<InfoComment> findAll(){
        List<InfoComment> infoComments = infoCommentRepository.findAll();
        return infoComments;
    }

    public InfoComment findById(Long myId) throws Exception {
        InfoComment infoComment2 = infoCommentRepository.findById(myId).orElseThrow(Exception::new);
        return infoComment2;
    }



    public InfoComment update (Long infoCommentId, InfoCommentDto infoCommentDto1) throws Exception {
        InfoComment infoComment3 = infoCommentRepository.findById(Long.parseLong(infoCommentDto1.getId())).orElseThrow(Exception::new);
        infoComment3.setComment(infoComment3.getComment());
        infoCommentRepository.save(infoComment3);
        return infoComment3;
    }

    public void delete(Long id) throws Exception {
        infoCommentRepository.delete(this.findById(id));
    }











}
