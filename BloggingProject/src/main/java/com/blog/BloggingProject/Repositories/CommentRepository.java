package com.blog.BloggingProject.Repositories;

import com.blog.BloggingProject.Entities.Concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}