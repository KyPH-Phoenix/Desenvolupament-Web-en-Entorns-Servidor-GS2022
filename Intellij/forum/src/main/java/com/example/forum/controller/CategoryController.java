package com.example.forum.controller;

import com.example.forum.model.Category;
import com.example.forum.service.CategoryService;

import com.example.forum.utilities.Util;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Category> categoriesGet() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/categories")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> createCategory(@RequestBody Category category) {
        String slug = category.getTitle().replace(" ", "-");
        String color = Util.generateRandomColor();

        while (categoryAlreadyExists(slug)) {
            int size = categoryService.findBySlugLike(slug + "%").size();
            slug += size;
        }

        Category c = new Category();
        BeanUtils.copyProperties(category, c);
        c.setSlug(slug);
        c.setColor(color);

        categoryService.save(c);

        Category result = categoryService.findBySlugLike(slug).get(0);

        return Util.buildCategoryMap(result);
    }

    private boolean categoryAlreadyExists(String slug) {
        List<Category> categories = categoryService.findBySlugLike(slug);

        return !categories.isEmpty();
    }

    @GetMapping("/categories/{slug}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> categoryGet(@PathVariable String slug, HttpServletResponse res) {
        Category category = categoryService.findBySlugLike(slug).stream().findFirst().orElse(null);

        if (category == null) {
            res.setStatus(400);
            return Map.of("message", "error");
        }

        return Util.buildCategoryMap(category);
    }
}
