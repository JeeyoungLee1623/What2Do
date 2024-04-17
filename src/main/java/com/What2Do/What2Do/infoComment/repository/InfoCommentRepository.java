package com.What2Do.What2Do.infoComment.repository;

import com.What2Do.What2Do.comment.domain.Comments;
import com.What2Do.What2Do.infoComment.domain.InfoComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InfoCommentRepository extends JpaRepository<InfoComment, Long> {

    List<InfoComment> findAllByInformationId (Long InformationId);


    Long findInformationIdById (Long id);

}

