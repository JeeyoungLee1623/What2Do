package com.What2Do.What2Do.gpt.service;

import com.What2Do.What2Do.gpt.domain.ChatRecord;
import com.What2Do.What2Do.gpt.repository.ChatRecordRepository;
import com.What2Do.What2Do.member.domain.Member;
import com.What2Do.What2Do.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatRecordService {


    @Autowired
    private ChatRecordRepository chatRecordRepository;
    @Autowired
    private MemberService memberService;

    @Autowired
    public ChatRecordService(ChatRecordRepository chatRecordRepository, MemberService memberService) {
        this.chatRecordRepository = chatRecordRepository;
        this.memberService = memberService;
    }

    public void saveChatRecord(Long memberId, String message) {
        try {
            Member member = memberService.findById(memberId);
            if (member != null) {
                ChatRecord chatRecord = new ChatRecord();
                chatRecord.setMember(member);
                chatRecord.setMessage(message);
                chatRecord.setTimestamp(LocalDateTime.now());
                chatRecordRepository.save(chatRecord);
            } else {
                throw new RuntimeException("User not found with Id: " + memberId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save chat record");
        }
    }

    public List<ChatRecord> getChatRecordsByMember(String memberId) {
        return chatRecordRepository.findByMemberId(memberId);
    }
}