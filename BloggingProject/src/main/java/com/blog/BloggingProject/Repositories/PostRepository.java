package com.blog.BloggingProject.Repositories;

import com.blog.BloggingProject.Entities.Concretes.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCategoryId(int categoryId);
}