package com.example.forum.dao;

import com.example.forum.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Long> {
    List<Category> findBySlugLike(String slug);
}
