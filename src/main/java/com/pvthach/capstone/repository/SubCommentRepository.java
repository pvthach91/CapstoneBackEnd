package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.Comment;
import com.pvthach.capstone.model.SubComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface SubCommentRepository extends JpaRepository<SubComment, Long> {

    List<SubComment> findAllByCommentOrderByDateCreatedDesc(Comment comment);
}