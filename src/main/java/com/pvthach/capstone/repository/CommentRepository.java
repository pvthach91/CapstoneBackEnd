package com.pvthach.capstone.repository;

import com.pvthach.capstone.model.Comment;
import com.pvthach.capstone.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by THACH-PC
 */

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByProductOrderByDateCreatedDesc(Product product);
}