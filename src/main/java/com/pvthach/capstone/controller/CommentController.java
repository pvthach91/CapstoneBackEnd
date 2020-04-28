package com.pvthach.capstone.controller;

import com.pvthach.capstone.dto.CommentDTO;
import com.pvthach.capstone.dto.SubCommentDTO;
import com.pvthach.capstone.model.Comment;
import com.pvthach.capstone.model.Product;
import com.pvthach.capstone.model.SubComment;
import com.pvthach.capstone.model.User;
import com.pvthach.capstone.repository.CommentRepository;
import com.pvthach.capstone.repository.SubCommentRepository;
import com.pvthach.capstone.repository.product.ProductRepository;
import com.pvthach.capstone.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CommentController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CommentRepository commentRepository;

	@Autowired
	SubCommentRepository subCommentRepository;

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/api/guest/comments/{id}")
	public List<CommentDTO> getComments(@PathVariable Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Product not found"));

		List<Comment> comments = commentRepository.findAllByProductOrderByDateCreatedDesc(product);
		return Comment.convertToDTOs(comments);
	}
	@GetMapping("/api/guest/subcomments/{id}")
	public List<SubCommentDTO> getSubComments(@PathVariable Long id) {
		Comment comment = commentRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Comment not found"));

		List<SubComment> subComments = subCommentRepository.findAllByCommentOrderByDateCreatedDesc(comment);
		return SubComment.convertToDTOs(subComments);
	}


	@PostMapping("/api/comment/addComment/{productId}")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER')")
	public List<CommentDTO> addComment(@RequestBody CommentDTO dto, @PathVariable Long productId) {
		Comment comment = new Comment();

		if (dto.getId() != null) {
			comment = commentRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("Comment is invalid"));
		}
		Product product = productRepository.findById(productId).orElseThrow(
				() -> new UsernameNotFoundException("Product not found"));

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		comment.setContent(dto.getContent());
		comment.setCommentedBy(user);
		comment.setProduct(product);
		Comment savedComment = commentRepository.save(comment);

		List<Comment> comments = commentRepository.findAllByProductOrderByDateCreatedDesc(product);
		return Comment.convertToDTOs(comments);
	}

	@PostMapping("/api/comment/addSubComment/{commentId}")
	@PreAuthorize("hasRole('FARMER') or hasRole('BUYER')")
	public Long addSubComment(@RequestBody SubComment dto, @PathVariable Long commentId) {
		SubComment subComment = new SubComment();

		if (dto.getId() != null) {
			subComment = subCommentRepository.findById(dto.getId()).orElseThrow(
					() -> new UsernameNotFoundException("SubComment is invalid"));
		}
		Comment comment = commentRepository.findById(commentId).orElseThrow(
				() -> new UsernameNotFoundException("Comment not found"));

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found"));

		subComment.setContent(dto.getContent());
		subComment.setCommentedBy(user);
		subComment.setComment(comment);
		SubComment savedComment = subCommentRepository.save(subComment);
		return savedComment.getId();
	}
}
