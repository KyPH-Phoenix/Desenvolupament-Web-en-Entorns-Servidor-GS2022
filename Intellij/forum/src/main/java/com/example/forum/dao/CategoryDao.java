package com.example.forum.dao;

import com.example.forum.model.Category;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface CategoryDao extends JpaRepository<Category, Long> {
    List<Category> findBySlugLike(String slug);
    void deleteBySlug(String slug);

    @Modifying
    @Query("update Category c set c.title = :title, c.description = :description where c.slug = :slug")
    void updateCategory(@Param("slug") String slug, @Param("title") String title, @Param("description") String description);
}
