package com.example.blogpost.repository;

import com.example.blogpost.model.BlogModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogModel, Long> {
}