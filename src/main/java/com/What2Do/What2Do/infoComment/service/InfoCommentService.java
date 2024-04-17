package com.What2Do.What2Do.infoComment.service;

import com.What2Do.What2Do.infoComment.domain.InfoComment;
import com.What2Do.What2Do.infoComment.etc.InfoCommentDto;
import com.What2Do.What2Do.infoComment.repository.InfoCommentRepository;
import com.What2Do.What2Do.information.domain.Information;
import com.What2Do.What2Do.information.repository.InformationRepository;
import com.What2Do.What2Do.information.service.InformationService;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.repository.MemberRepository;
import com.What2Do.What2Do.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private InformationRepository informationRepository;

    @Autowired
    private MemberRepository memberRepository;

    public void create (InfoCommentDto infoCommentDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Member member1 = memberRepository.findByUserName(currentUsername);
        Information information = informationRepository.findById(infoCommentDto.getInformationId())
                .orElseThrow(() -> new IllegalArgumentException("Information not found with id: " + infoCommentDto.getInformationId()));

        InfoComment infoComment1 = InfoComment.builder()
                .information(information)
                .comment(infoCommentDto.getComment())
                .member(member1)
                .build();
        infoCommentRepository.save(infoComment1);
    }


    public List<InfoComment> findAll(){
        List<InfoComment> infoComments = infoCommentRepository.findAll();
        return infoComments;
    }

    public InfoComment findById(Long myId) throws Exception {
        return infoCommentRepository.findById(myId)
                .orElseThrow(() -> new Exception("InfoComment not found with id: " + myId));
    }



    public InfoComment update (InfoCommentDto infoCommentDto1) throws Exception {
        InfoComment infoComment3 = infoCommentRepository.findById(Long.parseLong(infoCommentDto1.getId())).orElseThrow(Exception::new);
        infoComment3.setComment(infoComment3.getComment());
        infoCommentRepository.save(infoComment3);
        return infoComment3;
    }

    public void delete(Long id) throws Exception {
        infoCommentRepository.delete(this.findById(id));
    }

    public List<InfoComment> findAllByInformationId(Long informationId){
        List<InfoComment> infoComments = infoCommentRepository.findAllByInformationId(informationId);
        return infoComments;
    }

    public Long findInformationIdById (Long infoCommentId) {
        InfoComment infoComment = infoCommentRepository.findById(infoCommentId).orElse(null);
        if (infoComment != null) {
            return infoComment.getInformation().getId();
        }
        return null;
    }








}
