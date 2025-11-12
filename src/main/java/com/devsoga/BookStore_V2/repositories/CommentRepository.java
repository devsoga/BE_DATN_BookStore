package com.devsoga.BookStore_V2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoga.BookStore_V2.enties.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
	 Optional<CommentEntity> findByCommentCode(String commentCode);
}
