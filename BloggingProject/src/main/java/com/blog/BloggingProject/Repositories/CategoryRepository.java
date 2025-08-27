package com.blog.BloggingProject.Repositories;

import com.blog.BloggingProject.Entities.Concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);

}