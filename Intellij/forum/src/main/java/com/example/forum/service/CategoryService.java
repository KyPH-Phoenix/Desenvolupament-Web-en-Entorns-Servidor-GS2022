package com.example.forum.service;

import com.example.forum.dao.CategoryDao;
import com.example.forum.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDao categoryDao;

    public List<Category> getAllCategories() {
         return categoryDao.findAll();
    }

    public List<Category> findBySlugLike(String slug) {
        return categoryDao.findBySlugLike(slug);
    }

    public synchronized void save(Category c) {
        categoryDao.save(c);
    }

    public synchronized void updateCategory(String slug, String title, String description) {
        categoryDao.updateCategory(slug, title, description);
    }

    public synchronized void deleteCategory(String slug) {
        categoryDao.deleteBySlug(slug);
    }
}
