package com.What2Do.What2Do.gpt.repository;

import com.What2Do.What2Do.gpt.domain.ChatRecord;
import com.What2Do.What2Do.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRecordRepository extends JpaRepository<ChatRecord, Long> {
    List<ChatRecord> findByMemberId(String member);
}
