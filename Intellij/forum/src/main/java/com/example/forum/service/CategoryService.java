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

    public void save(Category c) {
        categoryDao.save(c);
    }
}
