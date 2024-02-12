package com.NewProject.NewPage.repository;

import com.NewProject.NewPage.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}