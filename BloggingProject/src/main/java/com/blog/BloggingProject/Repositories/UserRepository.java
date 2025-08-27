package com.blog.BloggingProject.Repositories;

import com.blog.BloggingProject.Entities.Concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByMail(String mail);
}